package com.example.erp.service;
import org.springframework.stereotype.Service;

import com.example.erp.entity.Product;
import com.example.erp.repository.ProductRepository;


@Service 
public class ProductService {
    private ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;

    }
    public Product createProduct(Product productReq){
        // business logic
        //  store to db 
        System.out.println("Inside Product Service");
        Product productResp = productRepository.saveProduct(productReq);
        return productResp; 
    }
    
}