package com.example.erp.dto;

import com.example.erp.entity.SalesOrderStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class SalesOrderResponseDTO {

    private Long id;
    private String orderNumber;

    private Long customerId;
    private String customerName;

    private Long warehouseId;
    private String warehouseName;

    private LocalDate orderDate;
    private SalesOrderStatus status;
    private BigDecimal totalAmount;

    private List<SalesOrderItemResponseDTO> items;

    public SalesOrderResponseDTO() {}

    public SalesOrderResponseDTO(
            Long id,
            String orderNumber,
            Long customerId,
            String customerName,
            Long warehouseId,
            String warehouseName,
            LocalDate orderDate,
            SalesOrderStatus status,
            BigDecimal totalAmount,
            List<SalesOrderItemResponseDTO> items) {

        this.id = id;
        this.orderNumber = orderNumber;
        this.customerId = customerId;
        this.customerName = customerName;
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

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
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

    public SalesOrderStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public List<SalesOrderItemResponseDTO> getItems() {
        return items;
    }
}