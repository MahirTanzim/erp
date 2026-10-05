package com.example.erp.dto;

import com.example.erp.entity.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaymentResponseDTO {

    private Long id;

    private Long invoiceId;
    private String invoiceNumber;

    private BigDecimal amount;
    private PaymentMethod method;
    private LocalDate paymentDate;
    private String reference;

    public PaymentResponseDTO() {}

    public PaymentResponseDTO(
            Long id,
            Long invoiceId,
            String invoiceNumber,
            BigDecimal amount,
            PaymentMethod method,
            LocalDate paymentDate,
            String reference) {

        this.id = id;
        this.invoiceId = invoiceId;
        this.invoiceNumber = invoiceNumber;
        this.amount = amount;
        this.method = method;
        this.paymentDate = paymentDate;
        this.reference = reference;
    }

    public Long getId() {
        return id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
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