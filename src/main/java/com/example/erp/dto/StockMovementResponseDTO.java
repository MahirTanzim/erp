package com.example.erp.dto;

import com.example.erp.entity.MovementType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StockMovementResponseDTO {

    private Long id;

    private Long productId;
    private String productName;

    private Long warehouseId;
    private String warehouseName;

    private BigDecimal quantity;

    private MovementType type;

    private String reference;

    private LocalDateTime movementDate;

    public StockMovementResponseDTO() {
    }

    public StockMovementResponseDTO(
            Long id,
            Long productId,
            String productName,
            Long warehouseId,
            String warehouseName,
            BigDecimal quantity,
            MovementType type,
            String reference,
            LocalDateTime movementDate) {

        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.quantity = quantity;
        this.type = type;
        this.reference = reference;
        this.movementDate = movementDate;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public MovementType getType() {
        return type;
    }

    public String getReference() {
        return reference;
    }

    public LocalDateTime getMovementDate() {
        return movementDate;
    }
}