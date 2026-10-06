package com.example.erp.service;

import com.example.erp.dto.PaymentRequestDTO;
import com.example.erp.dto.PaymentResponseDTO;
import com.example.erp.entity.Invoice;
import com.example.erp.entity.InvoiceStatus;
import com.example.erp.entity.Payment;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.InvoiceRepository;
import com.example.erp.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository; 
    private final InvoiceRepository invoiceRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            InvoiceRepository invoiceRepository) {

        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
    }

    @Transactional
    public Payment createPayment(PaymentRequestDTO dto) {

        Invoice invoice = invoiceRepository
                .findById(dto.getInvoiceId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Invoice not found"));

        if (invoice.getStatus() == InvoiceStatus.PAID) {
            throw new IllegalArgumentException(
                    "Invoice is already fully paid");
        }

        if (invoice.getStatus() == InvoiceStatus.CANCELLED) {
            throw new IllegalArgumentException(
                    "Payment cannot be made for a cancelled invoice");
        }

        BigDecimal paidAmount = paymentRepository
                .findByInvoiceId(invoice.getId())
                .stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal remainingAmount =
                invoice.getTotalAmount().subtract(paidAmount);

        if (dto.getAmount().compareTo(remainingAmount) > 0) {
            throw new IllegalArgumentException(
                    "Payment amount exceeds remaining invoice amount");
        }

        Payment payment = new Payment();

        payment.setInvoice(invoice);
        payment.setAmount(dto.getAmount());
        payment.setMethod(dto.getMethod());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setReference(dto.getReference());

        Payment savedPayment = paymentRepository.save(payment);

        BigDecimal newPaidAmount =
                paidAmount.add(dto.getAmount());

        if (newPaidAmount.compareTo(invoice.getTotalAmount()) == 0) {
            invoice.setStatus(InvoiceStatus.PAID);
        } else {
            invoice.setStatus(InvoiceStatus.PARTIALLY_PAID);
        }

        invoiceRepository.save(invoice);

        return savedPayment;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public List<Payment> getPaymentsByInvoiceId(Long invoiceId) {
        return paymentRepository.findByInvoiceId(invoiceId);
    }

    public PaymentResponseDTO toResponseDTO(Payment payment) {

        return new PaymentResponseDTO(
                payment.getId(),
                payment.getInvoice().getId(),
                payment.getInvoice().getInvoiceNumber(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getPaymentDate(),
                payment.getReference()
        );
    }

    public List<PaymentResponseDTO> getAllPaymentDTOs() {

        return paymentRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<PaymentResponseDTO> getPaymentDTOById(Long id) {

        return paymentRepository.findById(id)
                .map(this::toResponseDTO);
    }
}