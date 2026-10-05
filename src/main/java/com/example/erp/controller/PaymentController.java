package com.example.erp.controller;

import com.example.erp.dto.PaymentRequestDTO;
import com.example.erp.dto.PaymentResponseDTO;
import com.example.erp.entity.Payment;
import com.example.erp.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public PaymentResponseDTO createPayment(
            @Valid @RequestBody PaymentRequestDTO request) {

        Payment payment = paymentService.createPayment(request);

        return paymentService.toResponseDTO(payment);
    }

    @GetMapping
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentService.getAllPaymentDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponseDTO> getPaymentById(
            @PathVariable Long id) {

        return paymentService
                .getPaymentDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/invoice/{invoiceId}")
    public List<PaymentResponseDTO> getPaymentsByInvoice(
            @PathVariable Long invoiceId) {

        return paymentService
                .getPaymentsByInvoiceId(invoiceId)
                .stream()
                .map(paymentService::toResponseDTO)
                .toList();
    }
}