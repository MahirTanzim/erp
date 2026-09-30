package com.example.erp.controller;

import com.example.erp.entity.Product;
import com.example.erp.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.example.erp.dto.ProductRequestDTO;
import com.example.erp.dto.ProductResponseDTO;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Create product
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public ProductResponseDTO createProduct(
            @Valid @RequestBody ProductRequestDTO request) {

        Product product = productService.toEntity(request);

        Product savedProduct = productService.createProduct(product);

        return productService.toResponseDTO(savedProduct);
    }

    // Get all products
    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productService.getAllProductDTOs();
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(
            @PathVariable Long id) {

        return productService.getProductDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get product by product code
    @GetMapping("/code/{productCode}")
    public ResponseEntity<Product> getProductByCode(
            @PathVariable String productCode) {

        return productService.getProductByCode(productCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update product
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")

    public ResponseEntity<ProductResponseDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequestDTO request) {

        return productService.getProductById(id)
                .map(existingProduct -> {

                    Product product = productService.toEntity(request);

                    product.setId(id);

                    Product updated = productService.updateProduct(product);

                    return ResponseEntity.ok(
                            productService.toResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete product
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        if (productService.getProductById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}