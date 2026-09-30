package com.example.erp.service;

import com.example.erp.entity.Product;
import com.example.erp.repository.ProductRepository;
import org.springframework.stereotype.Service;
import com.example.erp.dto.ProductRequestDTO;
import com.example.erp.dto.ProductResponseDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getProductByCode(String productCode) {
        return productRepository.findByProductCode(productCode);
    }

    public Optional<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Product toEntity(ProductRequestDTO dto) {

        Product product = new Product();

        product.setProductCode(dto.getProductCode());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setUnit(dto.getUnit());

        return product;
    }

    public ProductResponseDTO toResponseDTO(Product product) {

        return new ProductResponseDTO(
                product.getId(),
                product.getProductCode(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getUnit());
    }

    public List<ProductResponseDTO> getAllProductDTOs() {

        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<ProductResponseDTO> getProductDTOById(Long id) {

        return productRepository.findById(id)
                .map(this::toResponseDTO);
    }
}