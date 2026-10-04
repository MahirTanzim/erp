package com.example.erp.dto;

import com.example.erp.entity.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderResponseDTO {

    private Long id;
    private String orderNumber;

    private Long supplierId;
    private String supplierName;

    private Long warehouseId;
    private String warehouseName;

    private LocalDate orderDate;
    private PurchaseOrderStatus status;
    private BigDecimal totalAmount;

    private List<PurchaseOrderItemResponseDTO> items;

    public PurchaseOrderResponseDTO() {
    }

    public PurchaseOrderResponseDTO(
            Long id,
            String orderNumber,
            Long supplierId,
            String supplierName,
            Long warehouseId,
            String warehouseName,
            LocalDate orderDate,
            PurchaseOrderStatus status,
            BigDecimal totalAmount,
            List<PurchaseOrderItemResponseDTO> items) {

        this.id = id;
        this.orderNumber = orderNumber;
        this.supplierId = supplierId;
        this.supplierName = supplierName;
        this.warehouseId = warehouseId;
        this.warehouseName = warehouseName;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Long getWarehouseId() {
        return warehouseId;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<PurchaseOrderItemResponseDTO> getItems() {
        return items;
    }
}