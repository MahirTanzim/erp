package com.example.erp.service;

import com.example.erp.dto.WarehouseRequestDTO;
import com.example.erp.dto.WarehouseResponseDTO;
import com.example.erp.entity.Warehouse;
import com.example.erp.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    public Optional<Warehouse> getWarehouseByCode(String warehouseCode) {
        return warehouseRepository.findByWarehouseCode(warehouseCode);
    }

    public Optional<Warehouse> getWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
    }

    public Warehouse updateWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id) {
        warehouseRepository.deleteById(id);
    }

    // DTO → Entity
    public Warehouse toEntity(WarehouseRequestDTO dto) {

        Warehouse warehouse = new Warehouse();

        warehouse.setWarehouseCode(dto.getWarehouseCode());
        warehouse.setName(dto.getName());
        warehouse.setLocation(dto.getLocation());
        warehouse.setDescription(dto.getDescription());

        return warehouse;
    }

    // Entity → DTO
    public WarehouseResponseDTO toResponseDTO(Warehouse warehouse) {

        return new WarehouseResponseDTO(
                warehouse.getId(),
                warehouse.getWarehouseCode(),
                warehouse.getName(),
                warehouse.getLocation(),
                warehouse.getDescription());
    }

    public List<WarehouseResponseDTO> getAllWarehouseDTOs() {

        return warehouseRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<WarehouseResponseDTO> getWarehouseDTOById(Long id) {

        return warehouseRepository.findById(id)
                .map(this::toResponseDTO);
    }
}