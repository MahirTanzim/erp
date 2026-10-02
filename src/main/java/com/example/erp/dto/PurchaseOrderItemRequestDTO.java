package com.example.erp.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PurchaseOrderItemRequestDTO {

    @NotNull(message = "Product ID is required")
    private Long productId;

    @NotNull(message = "Quantity is required")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Quantity must be greater than 0"
    )
    private BigDecimal quantity;

    @NotNull(message = "Unit price is required")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Unit price must be greater than 0"
    )
    private BigDecimal unitPrice;

    public PurchaseOrderItemRequestDTO() {
    }

    public PurchaseOrderItemRequestDTO(
            Long productId,
            BigDecimal quantity,
            BigDecimal unitPrice) {

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public Long getProductId() {
        return productId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }
}