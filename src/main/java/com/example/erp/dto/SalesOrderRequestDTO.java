package com.example.erp.dto;

import com.example.erp.entity.SalesOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class SalesOrderRequestDTO {

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Status is required")
    private SalesOrderStatus status;

    @NotEmpty(message = "Sales order must contain at least one item")
    @Valid
    private List<SalesOrderItemRequestDTO> items;

    public SalesOrderRequestDTO() {}

    public SalesOrderRequestDTO(
            String orderNumber,
            Long customerId,
            Long warehouseId,
            LocalDate orderDate,
            SalesOrderStatus status,
            List<SalesOrderItemRequestDTO> items) {

        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.warehouseId = warehouseId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public List<SalesOrderItemRequestDTO> getItems() {
        return items;
    }
}