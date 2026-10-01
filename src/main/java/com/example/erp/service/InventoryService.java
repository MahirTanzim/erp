package com.example.erp.service;

import com.example.erp.dto.InventoryRequestDTO;
import com.example.erp.dto.InventoryResponseDTO;
import com.example.erp.entity.Inventory;
import com.example.erp.entity.Product;
import com.example.erp.entity.Warehouse;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.InventoryRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;

    public InventoryService(
            InventoryRepository inventoryRepository,
            ProductRepository productRepository,
            WarehouseRepository warehouseRepository) {

        this.inventoryRepository = inventoryRepository;
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    // Create inventory
    public Inventory createInventory(InventoryRequestDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found"));

        Warehouse warehouse = warehouseRepository.findById(
                dto.getWarehouseId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Warehouse not found"));

        Inventory inventory = new Inventory();

        inventory.setProduct(product);
        inventory.setWarehouse(warehouse);
        inventory.setQuantity(dto.getQuantity());

        return inventoryRepository.save(inventory);
    }

    // Get all inventory
    public List<Inventory> getAllInventory() {
        return inventoryRepository.findAll();
    }

    // Get inventory by ID
    public Optional<Inventory> getInventoryById(Long id) {
        return inventoryRepository.findById(id);
    }

    // Get inventory for a specific product in a specific warehouse
    public Optional<Inventory> getInventoryByProductAndWarehouse(
            Long productId,
            Long warehouseId) {

        return inventoryRepository
                .findByProductIdAndWarehouseId(
                        productId,
                        warehouseId);
    }

    // Update inventory
    public Inventory updateInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // Delete inventory
    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    // Entity → Response DTO
    public InventoryResponseDTO toResponseDTO(Inventory inventory) {

        return new InventoryResponseDTO(
                inventory.getId(),
                inventory.getProduct().getId(),
                inventory.getProduct().getName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getName(),
                inventory.getQuantity()
        );
    }

    // Get all inventory as DTOs
    public List<InventoryResponseDTO> getAllInventoryDTOs() {

        return inventoryRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    // Get inventory by ID as DTO
    public Optional<InventoryResponseDTO> getInventoryDTOById(Long id) {

        return inventoryRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public Inventory updateInventory(
        Long id,
        InventoryRequestDTO dto) {

    Inventory existingInventory =
            inventoryRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Inventory not found"));

    Product product =
            productRepository.findById(dto.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found"));

    Warehouse warehouse =
            warehouseRepository.findById(dto.getWarehouseId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Warehouse not found"));

    existingInventory.setProduct(product);
    existingInventory.setWarehouse(warehouse);
    existingInventory.setQuantity(dto.getQuantity());

    return inventoryRepository.save(existingInventory);
}
}