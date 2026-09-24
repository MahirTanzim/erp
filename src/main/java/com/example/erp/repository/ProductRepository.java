package com.example.erp.repository;
import com.example.erp.entity.Product;
import org.springframework.stereotype.Component;

@Component 
public class ProductRepository {
    public Product saveProduct(Product productReq){
        // save to DB
        System.out.println("Inside Product Repository");
        return null;
    }
}
