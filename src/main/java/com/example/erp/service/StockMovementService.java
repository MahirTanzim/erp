package com.example.erp.service;

import com.example.erp.dto.StockMovementRequestDTO;
import com.example.erp.dto.StockMovementResponseDTO;
import com.example.erp.entity.Inventory;
import com.example.erp.entity.MovementType;
import com.example.erp.entity.Product;
import com.example.erp.entity.StockMovement;
import com.example.erp.entity.Warehouse;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.InventoryRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.StockMovementRepository;
import com.example.erp.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public StockMovementService(
            StockMovementRepository stockMovementRepository,
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.stockMovementRepository = stockMovementRepository;
        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    @Transactional
    public StockMovement createMovement(
            StockMovementRequestDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));

        Warehouse warehouse = warehouseRepository
                .findById(dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found"));

        Inventory inventory = inventoryRepository
                .findByProductIdAndWarehouseId(
                        dto.getProductId(),
                        dto.getWarehouseId())
                .orElse(null);

        // If inventory doesn't exist, create it
        if (inventory == null) {
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setWarehouse(warehouse);
            inventory.setQuantity(BigDecimal.ZERO);
        }

        BigDecimal currentQuantity = inventory.getQuantity();
        BigDecimal movementQuantity = dto.getQuantity();

        if (dto.getType() == MovementType.IN) {

            inventory.setQuantity(
                    currentQuantity.add(movementQuantity)
            );

        } else if (dto.getType() == MovementType.OUT) {

            if (currentQuantity.compareTo(movementQuantity) < 0) {
                throw new IllegalArgumentException(
                        "Insufficient stock");
            }

            inventory.setQuantity(
                    currentQuantity.subtract(movementQuantity)
            );
        }

        inventoryRepository.save(inventory);

        StockMovement movement = new StockMovement();

        movement.setProduct(product);
        movement.setWarehouse(warehouse);
        movement.setQuantity(movementQuantity);
        movement.setType(dto.getType());
        movement.setReference(dto.getReference());
        movement.setMovementDate(LocalDateTime.now());

        return stockMovementRepository.save(movement);
    }

    public List<StockMovementResponseDTO> getAllMovements() {

        return stockMovementRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public StockMovementResponseDTO toResponseDTO(
            StockMovement movement) {

        return new StockMovementResponseDTO(
                movement.getId(),
                movement.getProduct().getId(),
                movement.getProduct().getName(),
                movement.getWarehouse().getId(),
                movement.getWarehouse().getName(),
                movement.getQuantity(),
                movement.getType(),
                movement.getReference(),
                movement.getMovementDate()
        );
    }
}