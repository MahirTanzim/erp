package com.example.erp.controller;
import com.example.erp.entity.Product;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @PostMapping
    public void createProduct(@RequestBody Product product) {
        System.out.println(product.getName());
        System.out.println(product.getOrigin());
    }

}
