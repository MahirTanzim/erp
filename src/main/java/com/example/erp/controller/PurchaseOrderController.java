package com.example.erp.controller;

import com.example.erp.dto.PurchaseOrderRequestDTO;
import com.example.erp.dto.PurchaseOrderResponseDTO;
import com.example.erp.entity.PurchaseOrder;
import com.example.erp.service.PurchaseOrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(
            PurchaseOrderService purchaseOrderService) {

        this.purchaseOrderService = purchaseOrderService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public PurchaseOrderResponseDTO createPurchaseOrder(
            @Valid @RequestBody PurchaseOrderRequestDTO request) {

        PurchaseOrder purchaseOrder =
                purchaseOrderService.createPurchaseOrder(request);

        return purchaseOrderService.toResponseDTO(purchaseOrder);
    }

    @GetMapping
    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {

        return purchaseOrderService.getAllPurchaseOrderDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrderResponseDTO> getPurchaseOrderById(
            @PathVariable Long id) {

        return purchaseOrderService
                .getPurchaseOrderDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/number/{orderNumber}")
    public ResponseEntity<PurchaseOrderResponseDTO>
            getPurchaseOrderByOrderNumber(
                    @PathVariable String orderNumber) {

        return purchaseOrderService
                .getPurchaseOrderByOrderNumber(orderNumber)
                .map(purchaseOrderService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}