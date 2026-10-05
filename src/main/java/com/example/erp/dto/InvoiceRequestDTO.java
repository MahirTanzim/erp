package com.example.erp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class InvoiceRequestDTO {

    @NotBlank(message = "Invoice number is required")
    private String invoiceNumber;

    @NotNull(message = "Sales order ID is required")
    private Long salesOrderId;

    @NotNull(message = "Invoice date is required")
    private LocalDate invoiceDate;

    private LocalDate dueDate;

    public InvoiceRequestDTO() {}

    public InvoiceRequestDTO(
            String invoiceNumber,
            Long salesOrderId,
            LocalDate invoiceDate,
            LocalDate dueDate) {

        this.invoiceNumber = invoiceNumber;
        this.salesOrderId = salesOrderId;
        this.invoiceDate = invoiceDate;
        this.dueDate = dueDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
}