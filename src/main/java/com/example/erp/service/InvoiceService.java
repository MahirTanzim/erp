package com.example.erp.service;

import com.example.erp.dto.InvoiceRequestDTO;
import com.example.erp.dto.InvoiceResponseDTO;
import com.example.erp.entity.Invoice;
import com.example.erp.entity.InvoiceStatus;
import com.example.erp.entity.SalesOrder;
import com.example.erp.entity.SalesOrderStatus;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.InvoiceRepository;
import com.example.erp.repository.SalesOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final SalesOrderRepository salesOrderRepository;

    public InvoiceService(
            InvoiceRepository invoiceRepository,
            SalesOrderRepository salesOrderRepository) {

        this.invoiceRepository = invoiceRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    @Transactional
    public Invoice createInvoice(InvoiceRequestDTO dto) {

        if (invoiceRepository.existsByInvoiceNumber(
                dto.getInvoiceNumber())) {

            throw new IllegalArgumentException(
                    "Invoice number already exists");
        }

        if (invoiceRepository.findBySalesOrderId(
                dto.getSalesOrderId()).isPresent()) {

            throw new IllegalArgumentException(
                    "Invoice already exists for this sales order");
        }

        SalesOrder salesOrder =
                salesOrderRepository.findById(dto.getSalesOrderId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Sales order not found"));

        if (salesOrder.getStatus() != SalesOrderStatus.DELIVERED) {
            throw new IllegalArgumentException(
                    "Invoice can only be created for a delivered sales order");
        }

        Invoice invoice = new Invoice();

        invoice.setInvoiceNumber(dto.getInvoiceNumber());
        invoice.setSalesOrder(salesOrder);
        invoice.setCustomer(salesOrder.getCustomer());
        invoice.setInvoiceDate(dto.getInvoiceDate());
        invoice.setDueDate(dto.getDueDate());
        invoice.setStatus(InvoiceStatus.UNPAID);
        invoice.setTotalAmount(salesOrder.getTotalAmount());

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Optional<Invoice> getInvoiceByNumber(
            String invoiceNumber) {

        return invoiceRepository.findByInvoiceNumber(invoiceNumber);
    }

    public Optional<Invoice> getInvoiceBySalesOrderId(
            Long salesOrderId) {

        return invoiceRepository.findBySalesOrderId(salesOrderId);
    }

    public InvoiceResponseDTO toResponseDTO(Invoice invoice) {

        return new InvoiceResponseDTO(
                invoice.getId(),
                invoice.getInvoiceNumber(),
                invoice.getSalesOrder().getId(),
                invoice.getSalesOrder().getOrderNumber(),
                invoice.getCustomer().getId(),
                invoice.getCustomer().getName(),
                invoice.getInvoiceDate(),
                invoice.getDueDate(),
                invoice.getStatus(),
                invoice.getTotalAmount()
        );
    }

    public List<InvoiceResponseDTO> getAllInvoiceDTOs() {

        return invoiceRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<InvoiceResponseDTO> getInvoiceDTOById(
            Long id) {

        return invoiceRepository.findById(id)
                .map(this::toResponseDTO);
    }
}