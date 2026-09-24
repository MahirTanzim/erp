package com.example.erp.controller;
import com.example.erp.entity.Product;
import com.example.erp.service.ProductService;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    @PostMapping
    public String createProduct(@RequestBody Product product) {
        System.out.println("Inside Product Controller");
        Product createProduct = productService.createProduct(product);
        return "Product Created";
    }

}
