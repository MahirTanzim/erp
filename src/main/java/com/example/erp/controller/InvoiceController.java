package com.example.erp.controller;

import com.example.erp.dto.InvoiceRequestDTO;
import com.example.erp.dto.InvoiceResponseDTO;
import com.example.erp.entity.Invoice;
import com.example.erp.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public InvoiceResponseDTO createInvoice(
            @Valid @RequestBody InvoiceRequestDTO request) {

        Invoice invoice = invoiceService.createInvoice(request);

        return invoiceService.toResponseDTO(invoice);
    }

    @GetMapping
    public List<InvoiceResponseDTO> getAllInvoices() {
        return invoiceService.getAllInvoiceDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceById(
            @PathVariable Long id) {

        return invoiceService
                .getInvoiceDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{invoiceNumber}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceByNumber(
            @PathVariable String invoiceNumber) {

        return invoiceService
                .getInvoiceByNumber(invoiceNumber)
                .map(invoiceService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sales-order/{salesOrderId}")
    public ResponseEntity<InvoiceResponseDTO> getInvoiceBySalesOrder(
            @PathVariable Long salesOrderId) {

        return invoiceService
                .getInvoiceBySalesOrderId(salesOrderId)
                .map(invoiceService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}