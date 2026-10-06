package com.example.erp.repository;

import com.example.erp.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface PurchaseOrderRepository
        extends JpaRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findByOrderNumber(String orderNumber);

    boolean existsByOrderNumber(String orderNumber);

    @Query("SELECT COALESCE(SUM(p.totalAmount), 0) FROM PurchaseOrder p")
    BigDecimal getTotalPurchases();
}