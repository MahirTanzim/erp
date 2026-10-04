package com.example.erp.dto;

import com.example.erp.entity.PurchaseOrderStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderRequestDTO {

    @NotBlank(message = "Order number is required")
    private String orderNumber;

    @NotNull(message = "Supplier ID is required")
    private Long supplierId;

    @NotNull(message = "Order date is required")
    private LocalDate orderDate;

    @NotNull(message = "Status is required")
    private PurchaseOrderStatus status;

    @NotEmpty(message = "Purchase order must contain at least one item")
    @Valid
    private List<PurchaseOrderItemRequestDTO> items;

    @NotNull(message = "Warehouse ID is required")
    private Long warehouseId;

    public PurchaseOrderRequestDTO() {
    }

    public PurchaseOrderRequestDTO(
            String orderNumber,
            Long supplierId,
            Long warehouseId,
            LocalDate orderDate,
            PurchaseOrderStatus status,
            List<PurchaseOrderItemRequestDTO> items) {

        this.orderNumber = orderNumber;
        this.supplierId = supplierId;
        this.warehouseId = warehouseId;
        this.orderDate = orderDate;
        this.status = status;
        this.items = items;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public List<PurchaseOrderItemRequestDTO> getItems() {
        return items;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }
}