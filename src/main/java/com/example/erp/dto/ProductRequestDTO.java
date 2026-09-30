package com.example.erp.dto;

import java.math.BigDecimal;

public class ProductRequestDTO {

    private String productCode;
    private String name;
    private String description;
    private BigDecimal price;
    private String unit;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(
            String productCode,
            String name,
            String description,
            BigDecimal price,
            String unit) {

        this.productCode = productCode;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit = unit;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getUnit() {
        return unit;
    }
}