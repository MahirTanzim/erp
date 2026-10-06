package com.example.erp.dto;

import java.math.BigDecimal;

public class DashboardSummaryResponseDTO {

    private long totalEmployees;
    private long totalProducts;
    private long totalCustomers;
    private long totalSuppliers;
    private long totalWarehouses;

    private long totalPurchaseOrders;
    private long totalSalesOrders;
    private long totalInvoices;

    private BigDecimal totalSales;
    private BigDecimal totalPurchases;
    private BigDecimal totalPaid;
    private BigDecimal totalOutstanding;

    public DashboardSummaryResponseDTO() {
    }

    public DashboardSummaryResponseDTO(
            long totalEmployees,
            long totalProducts,
            long totalCustomers,
            long totalSuppliers,
            long totalWarehouses,
            long totalPurchaseOrders,
            long totalSalesOrders,
            long totalInvoices,
            BigDecimal totalSales,
            BigDecimal totalPurchases,
            BigDecimal totalPaid,
            BigDecimal totalOutstanding) {

        this.totalEmployees = totalEmployees;
        this.totalProducts = totalProducts;
        this.totalCustomers = totalCustomers;
        this.totalSuppliers = totalSuppliers;
        this.totalWarehouses = totalWarehouses;
        this.totalPurchaseOrders = totalPurchaseOrders;
        this.totalSalesOrders = totalSalesOrders;
        this.totalInvoices = totalInvoices;
        this.totalSales = totalSales;
        this.totalPurchases = totalPurchases;
        this.totalPaid = totalPaid;
        this.totalOutstanding = totalOutstanding;
    }

    public long getTotalEmployees() {
        return totalEmployees;
    }

    public long getTotalProducts() {
        return totalProducts;
    }

    public long getTotalCustomers() {
        return totalCustomers;
    }

    public long getTotalSuppliers() {
        return totalSuppliers;
    }

    public long getTotalWarehouses() {
        return totalWarehouses;
    }

    public long getTotalPurchaseOrders() {
        return totalPurchaseOrders;
    }

    public long getTotalSalesOrders() {
        return totalSalesOrders;
    }

    public long getTotalInvoices() {
        return totalInvoices;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public BigDecimal getTotalPurchases() {
        return totalPurchases;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public BigDecimal getTotalOutstanding() {
        return totalOutstanding;
    }
}