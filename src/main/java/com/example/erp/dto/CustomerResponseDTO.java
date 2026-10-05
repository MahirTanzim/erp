package com.example.erp.dto;

public class CustomerResponseDTO {

    private Long id;
    private String customerCode;
    private String name;
    private String email;
    private String phone;
    private String address;

    public CustomerResponseDTO() {}

    public CustomerResponseDTO(Long id, String customerCode,
                               String name, String email,
                               String phone, String address) {
        this.id = id;
        this.customerCode = customerCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}