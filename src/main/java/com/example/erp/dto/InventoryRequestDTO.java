package com.example.erp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class InventoryRequestDTO {

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

    public InventoryRequestDTO() {
    }

    public InventoryRequestDTO(
            Long productId,
            Long warehouseId,
            BigDecimal quantity) {

        this.productId = productId;
        this.warehouseId = warehouseId;
        this.quantity = quantity;
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
}