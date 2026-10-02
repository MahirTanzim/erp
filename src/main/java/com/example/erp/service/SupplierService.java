package com.example.erp.service;

import com.example.erp.dto.SupplierRequestDTO;
import com.example.erp.dto.SupplierResponseDTO;
import com.example.erp.entity.Supplier;
import com.example.erp.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    public Optional<Supplier> getSupplierByCode(String supplierCode) {
        return supplierRepository.findBySupplierCode(supplierCode);
    }

    public Optional<Supplier> getSupplierByName(String name) {
        return supplierRepository.findByName(name);
    }

    public Supplier updateSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public void deleteSupplier(Long id) {
        supplierRepository.deleteById(id);
    }

    public Supplier toEntity(SupplierRequestDTO dto) {

        Supplier supplier = new Supplier();

        supplier.setSupplierCode(dto.getSupplierCode());
        supplier.setName(dto.getName());
        supplier.setEmail(dto.getEmail());
        supplier.setPhone(dto.getPhone());
        supplier.setAddress(dto.getAddress());
        supplier.setContactPerson(dto.getContactPerson());

        return supplier;
    }

    public SupplierResponseDTO toResponseDTO(Supplier supplier) {

        return new SupplierResponseDTO(
                supplier.getId(),
                supplier.getSupplierCode(),
                supplier.getName(),
                supplier.getEmail(),
                supplier.getPhone(),
                supplier.getAddress(),
                supplier.getContactPerson()
        );
    }

    public List<SupplierResponseDTO> getAllSupplierDTOs() {

        return supplierRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<SupplierResponseDTO> getSupplierDTOById(Long id) {

        return supplierRepository.findById(id)
                .map(this::toResponseDTO);
    }
}   