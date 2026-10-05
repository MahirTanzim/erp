package com.example.erp.service;

import com.example.erp.dto.SalesOrderItemRequestDTO;
import com.example.erp.dto.SalesOrderItemResponseDTO;
import com.example.erp.dto.SalesOrderRequestDTO;
import com.example.erp.dto.SalesOrderResponseDTO;
import com.example.erp.entity.Customer;
import com.example.erp.entity.Product;
import com.example.erp.entity.SalesOrder;
import com.example.erp.entity.SalesOrderItem;
import com.example.erp.entity.Warehouse;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.SalesOrderRepository;
import com.example.erp.repository.WarehouseRepository;
import com.example.erp.entity.MovementType;
import com.example.erp.entity.SalesOrderStatus;
import com.example.erp.dto.StockMovementRequestDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    private final StockMovementService stockMovementService;

    public SalesOrderService(
            SalesOrderRepository salesOrderRepository,
            CustomerRepository customerRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository,
            StockMovementService stockMovementService) {

        this.salesOrderRepository = salesOrderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
        this.stockMovementService = stockMovementService;

    }

    @Transactional
    public SalesOrder createSalesOrder(
            SalesOrderRequestDTO dto) {

        if (salesOrderRepository.existsByOrderNumber(
                dto.getOrderNumber())) {

            throw new IllegalArgumentException(
                    "Order number already exists");
        }

        Customer customer = customerRepository
                .findById(dto.getCustomerId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found"));

        Warehouse warehouse = warehouseRepository
                .findById(dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found"));

        SalesOrder salesOrder = new SalesOrder();

        salesOrder.setOrderNumber(dto.getOrderNumber());
        salesOrder.setCustomer(customer);
        salesOrder.setWarehouse(warehouse);
        salesOrder.setOrderDate(dto.getOrderDate());
        salesOrder.setStatus(dto.getStatus());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (SalesOrderItemRequestDTO itemDTO : dto.getItems()) {

            Product product = productRepository
                    .findById(itemDTO.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found: "
                                            + itemDTO.getProductId()));

            BigDecimal totalPrice =
                    itemDTO.getQuantity()
                            .multiply(itemDTO.getUnitPrice());

            SalesOrderItem item = new SalesOrderItem();

            item.setSalesOrder(salesOrder);
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());
            item.setUnitPrice(itemDTO.getUnitPrice());
            item.setTotalPrice(totalPrice);

            salesOrder.getItems().add(item);

            totalAmount = totalAmount.add(totalPrice);
        }

        salesOrder.setTotalAmount(totalAmount);

        return salesOrderRepository.save(salesOrder);
    }

    public List<SalesOrder> getAllSalesOrders() {
        return salesOrderRepository.findAll();
    }

    public Optional<SalesOrder> getSalesOrderById(Long id) {
        return salesOrderRepository.findById(id);
    }

    public Optional<SalesOrder> getSalesOrderByOrderNumber(
            String orderNumber) {

        return salesOrderRepository
                .findByOrderNumber(orderNumber);
    }

    public SalesOrderResponseDTO toResponseDTO(
            SalesOrder salesOrder) {

        List<SalesOrderItemResponseDTO> itemDTOs =
                salesOrder.getItems()
                        .stream()
                        .map(item -> new SalesOrderItemResponseDTO(
                                item.getId(),
                                item.getProduct().getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getUnitPrice(),
                                item.getTotalPrice()
                        ))
                        .toList();

        return new SalesOrderResponseDTO(
                salesOrder.getId(),
                salesOrder.getOrderNumber(),
                salesOrder.getCustomer().getId(),
                salesOrder.getCustomer().getName(),
                salesOrder.getWarehouse().getId(),
                salesOrder.getWarehouse().getName(),
                salesOrder.getOrderDate(),
                salesOrder.getStatus(),
                salesOrder.getTotalAmount(),
                itemDTOs
        );
    }

    public List<SalesOrderResponseDTO> getAllSalesOrderDTOs() {

        return salesOrderRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<SalesOrderResponseDTO> getSalesOrderDTOById(
            Long id) {

        return salesOrderRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional
public SalesOrder updateStatus(
        Long id,
        SalesOrderStatus status) {

    SalesOrder salesOrder =
            salesOrderRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Sales order not found"));

    if (salesOrder.getStatus() == SalesOrderStatus.DELIVERED) {
        throw new IllegalArgumentException(
                "A delivered sales order cannot change status");
    }

    if (salesOrder.getStatus() == SalesOrderStatus.CANCELLED) {
        throw new IllegalArgumentException(
                "A cancelled sales order cannot change status");
    }

    // Deduct stock only when changing to CONFIRMED
    if (status == SalesOrderStatus.CONFIRMED
            && salesOrder.getStatus() != SalesOrderStatus.CONFIRMED) {

        for (SalesOrderItem item : salesOrder.getItems()) {

            StockMovementRequestDTO movementDTO =
                    new StockMovementRequestDTO();

            movementDTO.setProductId(
                    item.getProduct().getId());

            movementDTO.setWarehouseId(
                    salesOrder.getWarehouse().getId());

            movementDTO.setQuantity(
                    item.getQuantity());

            movementDTO.setType(MovementType.OUT);

            movementDTO.setReference(
                    "Sales Order: "
                            + salesOrder.getOrderNumber());

            stockMovementService.createMovement(movementDTO);
        }
    }

    salesOrder.setStatus(status);

    return salesOrderRepository.save(salesOrder);
}
}