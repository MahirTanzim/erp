package com.example.erp.service;

import com.example.erp.dto.PurchaseOrderItemRequestDTO;
import com.example.erp.dto.PurchaseOrderItemResponseDTO;
import com.example.erp.dto.PurchaseOrderRequestDTO;
import com.example.erp.dto.PurchaseOrderResponseDTO;
import com.example.erp.entity.Product;
import com.example.erp.entity.PurchaseOrder;
import com.example.erp.entity.PurchaseOrderItem;
import com.example.erp.entity.PurchaseOrderStatus;
import com.example.erp.entity.Supplier;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.PurchaseOrderRepository;
import com.example.erp.repository.SupplierRepository;
import com.example.erp.entity.Warehouse;
import com.example.erp.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseOrderService {

        private final PurchaseOrderRepository purchaseOrderRepository;
        private final SupplierRepository supplierRepository;
        private final ProductRepository productRepository;
        private final WarehouseRepository warehouseRepository;

        public PurchaseOrderService(
                        PurchaseOrderRepository purchaseOrderRepository,
                        SupplierRepository supplierRepository,
                        ProductRepository productRepository, WarehouseRepository warehouseRepository) {

                this.purchaseOrderRepository = purchaseOrderRepository;
                this.supplierRepository = supplierRepository;
                this.productRepository = productRepository;
                this.warehouseRepository = warehouseRepository;
        }

        @Transactional
        public PurchaseOrder createPurchaseOrder(
                        PurchaseOrderRequestDTO dto) {

                if (purchaseOrderRepository
                                .existsByOrderNumber(dto.getOrderNumber())) {

                        throw new IllegalArgumentException(
                                        "Order number already exists");
                }

                Supplier supplier = supplierRepository
                                .findById(dto.getSupplierId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Supplier not found"));

                Warehouse warehouse = warehouseRepository
                                .findById(dto.getWarehouseId())
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Warehouse not found"));

                PurchaseOrder purchaseOrder = new PurchaseOrder();

                purchaseOrder.setOrderNumber(dto.getOrderNumber());
                purchaseOrder.setSupplier(supplier);
                purchaseOrder.setWarehouse(warehouse);
                purchaseOrder.setOrderDate(dto.getOrderDate());
                purchaseOrder.setStatus(dto.getStatus());

                BigDecimal totalAmount = BigDecimal.ZERO;

                for (PurchaseOrderItemRequestDTO itemDTO : dto.getItems()) {

                        Product product = productRepository
                                        .findById(itemDTO.getProductId())
                                        .orElseThrow(() -> new ResourceNotFoundException(
                                                        "Product not found: "
                                                                        + itemDTO.getProductId()));

                        BigDecimal totalPrice = itemDTO.getQuantity()
                                        .multiply(itemDTO.getUnitPrice());

                        PurchaseOrderItem item = new PurchaseOrderItem();

                        item.setPurchaseOrder(purchaseOrder);
                        item.setProduct(product);
                        item.setQuantity(itemDTO.getQuantity());
                        item.setUnitPrice(itemDTO.getUnitPrice());
                        item.setTotalPrice(totalPrice);

                        purchaseOrder.getItems().add(item);

                        totalAmount = totalAmount.add(totalPrice);
                }

                purchaseOrder.setTotalAmount(totalAmount);

                return purchaseOrderRepository.save(purchaseOrder);
        }

        public List<PurchaseOrder> getAllPurchaseOrders() {
                return purchaseOrderRepository.findAll();
        }

        public Optional<PurchaseOrder> getPurchaseOrderById(Long id) {
                return purchaseOrderRepository.findById(id);
        }

        public Optional<PurchaseOrder> getPurchaseOrderByOrderNumber(
                        String orderNumber) {

                return purchaseOrderRepository
                                .findByOrderNumber(orderNumber);
        }

        public PurchaseOrderResponseDTO toResponseDTO(
                        PurchaseOrder purchaseOrder) {

                List<PurchaseOrderItemResponseDTO> itemDTOs = purchaseOrder.getItems()
                                .stream()
                                .map(item -> new PurchaseOrderItemResponseDTO(
                                                item.getId(),
                                                item.getProduct().getId(),
                                                item.getProduct().getName(),
                                                item.getQuantity(),
                                                item.getUnitPrice(),
                                                item.getTotalPrice()))
                                .toList();

                return new PurchaseOrderResponseDTO(
                                purchaseOrder.getId(),
                                purchaseOrder.getOrderNumber(),
                                purchaseOrder.getSupplier().getId(),
                                purchaseOrder.getSupplier().getName(),
                                purchaseOrder.getWarehouse().getId(),
                                purchaseOrder.getWarehouse().getName(),
                                purchaseOrder.getOrderDate(),
                                purchaseOrder.getStatus(),
                                purchaseOrder.getTotalAmount(),
                                itemDTOs);
        }

        public List<PurchaseOrderResponseDTO> getAllPurchaseOrderDTOs() {

                return purchaseOrderRepository.findAll()
                                .stream()
                                .map(this::toResponseDTO)
                                .toList();
        }

        public Optional<PurchaseOrderResponseDTO> getPurchaseOrderDTOById(
                        Long id) {

                return purchaseOrderRepository.findById(id)
                                .map(this::toResponseDTO);
        }

        public PurchaseOrder updateStatus(Long id, PurchaseOrderStatus status) {

                PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException(
                                                "Purchase order not found"));

                purchaseOrder.setStatus(status);

                return purchaseOrderRepository.save(purchaseOrder);
        }

}