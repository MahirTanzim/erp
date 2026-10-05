package com.example.erp.dto;

import com.example.erp.entity.PaymentMethod;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentRequestDTO {

    @NotNull(message = "Invoice ID is required")
    private Long invoiceId;

    @NotNull(message = "Payment amount is required")
    @DecimalMin(
            value = "0.0",
            inclusive = false,
            message = "Payment amount must be greater than 0"
    )
    private BigDecimal amount;

    @NotNull(message = "Payment method is required")
    private PaymentMethod method;

    @NotNull(message = "Payment date is required")
    private LocalDate paymentDate;

    private String reference;

    public PaymentRequestDTO() {}

    public PaymentRequestDTO(
            Long invoiceId,
            BigDecimal amount,
            PaymentMethod method,
            LocalDate paymentDate,
            String reference) {

        this.invoiceId = invoiceId;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
        this.reference = reference;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getReference() {
        return reference;
    }
}