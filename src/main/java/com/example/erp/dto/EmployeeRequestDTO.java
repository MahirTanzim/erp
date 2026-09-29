package com.example.erp.dto;

import java.time.LocalDate;

public class EmployeeRequestDTO {

    private String employeeCode;
    private String name;
    private String email;
    private String phone;
    private Long departmentId;
    private String designation;
    private LocalDate joiningDate;

    public EmployeeRequestDTO() {
    }

    public EmployeeRequestDTO(
            String employeeCode,
            String name,
            String email,
            String phone,
            Long departmentId,
            String designation,
            LocalDate joiningDate) {

        this.employeeCode = employeeCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
        this.designation = designation;
        this.joiningDate = joiningDate;
    }

    public String getEmployeeCode() {
        return employeeCode;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public String getDesignation() {
        return designation;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }
}