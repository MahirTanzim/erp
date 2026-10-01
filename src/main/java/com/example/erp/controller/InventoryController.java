package com.example.erp.controller;

import com.example.erp.dto.InventoryRequestDTO;
import com.example.erp.dto.InventoryResponseDTO;
import com.example.erp.entity.Inventory;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public InventoryResponseDTO createInventory(
            @Valid @RequestBody InventoryRequestDTO request) {

        Inventory inventory = inventoryService.createInventory(request);

        return inventoryService.toResponseDTO(inventory);
    }

    @GetMapping
    public List<InventoryResponseDTO> getAllInventory() {

        return inventoryService.getAllInventoryDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> getInventoryById(
            @PathVariable Long id) {

        return inventoryService.getInventoryDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productId}/warehouse/{warehouseId}")
    public ResponseEntity<InventoryResponseDTO> getInventoryByProductAndWarehouse(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        return inventoryService
                .getInventoryByProductAndWarehouse(
                        productId,
                        warehouseId)
                .map(inventoryService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDTO> updateInventory(
            @PathVariable Long id,
            @Valid @RequestBody InventoryRequestDTO request) {

        try {
            Inventory updated = inventoryService.updateInventory(id, request);

            return ResponseEntity.ok(
                    inventoryService.toResponseDTO(updated));

        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(
            @PathVariable Long id) {

        if (inventoryService.getInventoryById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        inventoryService.deleteInventory(id);

        return ResponseEntity.noContent().build();
    }
}