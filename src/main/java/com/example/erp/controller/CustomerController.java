package com.example.erp.controller;

import com.example.erp.dto.CustomerRequestDTO;
import com.example.erp.dto.CustomerResponseDTO;
import com.example.erp.entity.Customer;
import com.example.erp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public CustomerResponseDTO createCustomer(
            @Valid @RequestBody CustomerRequestDTO request) {

        Customer customer = customerService.toEntity(request);
        Customer savedCustomer = customerService.createCustomer(customer);

        return customerService.toResponseDTO(savedCustomer);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomerDTOs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> getCustomerById(
            @PathVariable Long id) {

        return customerService.getCustomerDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/code/{customerCode}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByCode(
            @PathVariable String customerCode) {

        return customerService.getCustomerByCode(customerCode)
                .map(customerService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CustomerResponseDTO> getCustomerByName(
            @PathVariable String name) {

        return customerService.getCustomerByName(name)
                .map(customerService::toResponseDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponseDTO> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody CustomerRequestDTO request) {

        return customerService.getCustomerById(id)
                .map(existingCustomer -> {

                    Customer customer = customerService.toEntity(request);
                    customer.setId(id);

                    Customer updated =
                            customerService.updateCustomer(customer);

                    return ResponseEntity.ok(
                            customerService.toResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable Long id) {

        if (customerService.getCustomerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        customerService.deleteCustomer(id);

        return ResponseEntity.noContent().build();
    }
}