package com.example.erp.controller;

import com.example.erp.dto.WarehouseRequestDTO;
import com.example.erp.dto.WarehouseResponseDTO;
import com.example.erp.entity.Warehouse;
import com.example.erp.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public WarehouseResponseDTO createWarehouse(
            @Valid @RequestBody WarehouseRequestDTO request) {

        Warehouse warehouse = warehouseService.toEntity(request);

        Warehouse savedWarehouse = warehouseService.createWarehouse(warehouse);

        return warehouseService.toResponseDTO(savedWarehouse);
    }

    @GetMapping
    public List<WarehouseResponseDTO> getAllWarehouses() {

        return warehouseService.getAllWarehouseDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> getWarehouseById(
            @PathVariable Long id) {

        return warehouseService.getWarehouseDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{warehouseCode}")
    public ResponseEntity<WarehouseResponseDTO> getWarehouseByCode(
            @PathVariable String warehouseCode) {

        return warehouseService.getWarehouseByCode(warehouseCode)
                .map(warehouseService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponseDTO> updateWarehouse(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequestDTO request) {

        return warehouseService.getWarehouseById(id)
                .map(existingWarehouse -> {

                    Warehouse warehouse = warehouseService.toEntity(request);

                    warehouse.setId(id);

                    Warehouse updated = warehouseService.updateWarehouse(warehouse);

                    return ResponseEntity.ok(
                            warehouseService.toResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWarehouse(
            @PathVariable Long id) {

        if (warehouseService.getWarehouseById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        warehouseService.deleteWarehouse(id);

        return ResponseEntity.noContent().build();
    }
}