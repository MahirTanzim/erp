package com.example.erp.dto;

import jakarta.validation.constraints.NotBlank;

public class WarehouseRequestDTO {

    @NotBlank(message = "Warehouse code is required")
    private String warehouseCode;

    @NotBlank(message = "Warehouse name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;

    public WarehouseRequestDTO() {
    }

    public WarehouseRequestDTO(
            String warehouseCode,
            String name,
            String location,
            String description) {

        this.warehouseCode = warehouseCode;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public String getWarehouseCode() {
        return warehouseCode;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }
}