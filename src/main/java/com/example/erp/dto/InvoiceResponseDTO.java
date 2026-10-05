package com.example.erp.dto;

import com.example.erp.entity.InvoiceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

public class InvoiceResponseDTO {

    private Long id;
    private String invoiceNumber;

    private Long salesOrderId;
    private String salesOrderNumber;

    private Long customerId;
    private String customerName;

    private LocalDate invoiceDate;
    private LocalDate dueDate;

    private InvoiceStatus status;
    private BigDecimal totalAmount;

    public InvoiceResponseDTO() {}

    public InvoiceResponseDTO(
            Long id,
            String invoiceNumber,
            Long salesOrderId,
            String salesOrderNumber,
            Long customerId,
            String customerName,
            LocalDate invoiceDate,
            LocalDate dueDate,
            InvoiceStatus status,
            BigDecimal totalAmount) {

        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.salesOrderId = salesOrderId;
        this.salesOrderNumber = salesOrderNumber;
        this.customerId = customerId;
        this.customerName = customerName;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
        this.status = status;
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public String getSalesOrderNumber() {
        return salesOrderNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
}