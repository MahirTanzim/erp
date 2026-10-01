package com.example.erp.dto;

public class WarehouseResponseDTO {

    private Long id;
    private String warehouseCode;
    private String name;
    private String location;
    private String description;

    public WarehouseResponseDTO() {
    }

    public WarehouseResponseDTO(
            Long id,
            String warehouseCode,
            String name,
            String location,
            String description) {

        this.id = id;
        this.warehouseCode = warehouseCode;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    public Long getId() {
        return id;
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