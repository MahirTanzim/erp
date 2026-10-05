package com.example.erp.dto;

import java.math.BigDecimal;

public class SalesOrderItemResponseDTO {

    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    public SalesOrderItemResponseDTO() {}

    public SalesOrderItemResponseDTO(
            Long id,
            Long productId,
            String productName,
            BigDecimal quantity,
            BigDecimal unitPrice,
            BigDecimal totalPrice) {

        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
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

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
}