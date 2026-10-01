package com.example.erp.controller;

import com.example.erp.dto.StockMovementRequestDTO;
import com.example.erp.dto.StockMovementResponseDTO;
import com.example.erp.entity.StockMovement;
import com.example.erp.service.StockMovementService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(
            StockMovementService stockMovementService) {

        this.stockMovementService = stockMovementService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public StockMovementResponseDTO createMovement(
            @Valid @RequestBody StockMovementRequestDTO request) {

        StockMovement movement = stockMovementService.createMovement(request);

        return stockMovementService.toResponseDTO(movement);
    }

    @GetMapping
    public List<StockMovementResponseDTO> getAllMovements() {

        return stockMovementService.getAllMovements();
    }

    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public List<StockMovementResponseDTO> getMovementsByProductAndWarehouse(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        return stockMovementService
                .getMovementsByProductAndWarehouse(
                        productId,
                        warehouseId);
    }
}