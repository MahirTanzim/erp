package com.example.erp.repository;

import com.example.erp.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    Optional<SalesOrder> findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);
}