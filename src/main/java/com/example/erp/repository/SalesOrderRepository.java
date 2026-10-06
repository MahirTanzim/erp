package com.example.erp.repository;

import com.example.erp.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.math.BigDecimal;
import java.util.Optional;

public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

    Optional<SalesOrder> findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);

    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM SalesOrder s")
    BigDecimal getTotalSales();
}