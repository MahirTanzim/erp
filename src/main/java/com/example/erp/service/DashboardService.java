package com.example.erp.service;

import com.example.erp.dto.DashboardSummaryResponseDTO;
import com.example.erp.repository.CustomerRepository;
import com.example.erp.repository.EmployeeRepository;
import com.example.erp.repository.InvoiceRepository;
import com.example.erp.repository.ProductRepository;
import com.example.erp.repository.PurchaseOrderRepository;
import com.example.erp.repository.SalesOrderRepository;
import com.example.erp.repository.SupplierRepository;
import com.example.erp.repository.WarehouseRepository;
import com.example.erp.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final SupplierRepository supplierRepository;
    private final WarehouseRepository warehouseRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;

    public DashboardService(
            EmployeeRepository employeeRepository,
            ProductRepository productRepository,
            CustomerRepository customerRepository,
            SupplierRepository supplierRepository,
            WarehouseRepository warehouseRepository,
            PurchaseOrderRepository purchaseOrderRepository,
            SalesOrderRepository salesOrderRepository,
            InvoiceRepository invoiceRepository,
            PaymentRepository paymentRepository) {

        this.employeeRepository = employeeRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.supplierRepository = supplierRepository;
        this.warehouseRepository = warehouseRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
        this.invoiceRepository = invoiceRepository;
        this.paymentRepository = paymentRepository;
    }

    public DashboardSummaryResponseDTO getSummary() {

        BigDecimal totalPaid = paymentRepository.getTotalPaid();

        BigDecimal totalInvoiceAmount = invoiceRepository.getTotalInvoiceAmount();

        BigDecimal totalOutstanding = totalInvoiceAmount.subtract(totalPaid);

        BigDecimal totalSales = salesOrderRepository.getTotalSales();
        BigDecimal totalPurchases = purchaseOrderRepository.getTotalPurchases();

        return new DashboardSummaryResponseDTO(
                employeeRepository.count(),
                productRepository.count(),
                customerRepository.count(),
                supplierRepository.count(),
                warehouseRepository.count(),

                purchaseOrderRepository.count(),
                salesOrderRepository.count(),
                invoiceRepository.count(),

                totalSales,
                totalPurchases,
                totalPaid,
                totalOutstanding);
    }
}