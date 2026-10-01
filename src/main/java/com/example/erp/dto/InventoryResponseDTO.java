package com.example.erp.dto;

import java.math.BigDecimal;

public class InventoryResponseDTO {

    private Long id;
    private Long productId;
    private String productName;
    private Long warehouseId;
    private String warehouseName;
    private BigDecimal quantity;

    public InventoryResponseDTO() {
    }

    public InventoryResponseDTO(
            Long id,
            Long productId,
            String productName,
            Long warehouseId,
            String warehouseName,
            BigDecimal quantity) {

        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.quantity = quantity;
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
}