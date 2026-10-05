package com.example.erp.controller;

import com.example.erp.dto.SalesOrderRequestDTO;
import com.example.erp.dto.SalesOrderResponseDTO;
import com.example.erp.entity.SalesOrder;
import com.example.erp.service.SalesOrderService;
import com.example.erp.entity.SalesOrderStatus;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public SalesOrderResponseDTO createSalesOrder(
            @Valid @RequestBody SalesOrderRequestDTO request) {

        SalesOrder salesOrder = salesOrderService.createSalesOrder(request);

        return salesOrderService.toResponseDTO(salesOrder);
    }

    @GetMapping
    public List<SalesOrderResponseDTO> getAllSalesOrders() {

        return salesOrderService.getAllSalesOrderDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SalesOrderResponseDTO> getSalesOrderById(
            @PathVariable Long id) {

        return salesOrderService
                .getSalesOrderDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<SalesOrderResponseDTO> getSalesOrderByOrderNumber(
            @PathVariable String orderNumber) {

        return salesOrderService
                .getSalesOrderByOrderNumber(orderNumber)
                .map(salesOrderService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}/status")
    public ResponseEntity<SalesOrderResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestParam SalesOrderStatus status) {

        SalesOrder salesOrder = salesOrderService.updateStatus(id, status);

        return ResponseEntity.ok(
                salesOrderService.toResponseDTO(salesOrder));
    }
}