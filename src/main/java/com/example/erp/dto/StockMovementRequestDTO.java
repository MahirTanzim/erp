package com.example.erp.dto;

import com.example.erp.entity.MovementType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class StockMovementRequestDTO {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;

    @NotNull(message = "Quantity is required")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Quantity must be greater than 0"
    )           
    private BigDecimal quantity;

    @NotNull(message = "Movement type is required")
    private MovementType type;

    private String reference;

    public StockMovementRequestDTO() {
    }

    public StockMovementRequestDTO(
            Long productId,
            Long warehouseId,
            BigDecimal quantity,
            MovementType type,
            String reference) {

        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
        this.type = type;
        this.reference = reference;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getWarehouseId() {
        return warehouseId;
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
}