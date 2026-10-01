package com.example.erp.dto;

public class SupplierResponseDTO {

    private Long id;
    private String supplierCode;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String contactPerson;

    public SupplierResponseDTO() {
    }

    public SupplierResponseDTO(
            Long id,
            String supplierCode,
            String name,
            String email,
            String phone,
            String address,
            String contactPerson) {

        this.id = id;
        this.supplierCode = supplierCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.contactPerson = contactPerson;
    }

    public Long getId() {
        return id;
    }

    public String getSupplierCode() {
        return supplierCode;
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

    public String getContactPerson() {
        return contactPerson;
    }
}