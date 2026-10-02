package com.example.erp.controller;

import com.example.erp.dto.SupplierRequestDTO;
import com.example.erp.dto.SupplierResponseDTO;
import com.example.erp.entity.Supplier;
import com.example.erp.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public SupplierResponseDTO createSupplier(
            @Valid @RequestBody SupplierRequestDTO request) {

        Supplier supplier = supplierService.toEntity(request);

        Supplier savedSupplier =
                supplierService.createSupplier(supplier);

        return supplierService.toResponseDTO(savedSupplier);
    }

    @GetMapping
    public List<SupplierResponseDTO> getAllSuppliers() {

        return supplierService.getAllSupplierDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(
            @PathVariable Long id) {

        return supplierService.getSupplierDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{supplierCode}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByCode(
            @PathVariable String supplierCode) {

        return supplierService.getSupplierByCode(supplierCode)
                .map(supplierService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByName(
            @PathVariable String name) {

        return supplierService.getSupplierByName(name)
                .map(supplierService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(
            @PathVariable Long id,
            @Valid @RequestBody SupplierRequestDTO request) {

        return supplierService.getSupplierById(id)
                .map(existingSupplier -> {

                    Supplier supplier =
                            supplierService.toEntity(request);

                    supplier.setId(id);

                    Supplier updated =
                            supplierService.updateSupplier(supplier);

                    return ResponseEntity.ok(
                            supplierService.toResponseDTO(updated)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(
            @PathVariable Long id) {

        if (supplierService.getSupplierById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        supplierService.deleteSupplier(id);

        return ResponseEntity.noContent().build();
    }
}