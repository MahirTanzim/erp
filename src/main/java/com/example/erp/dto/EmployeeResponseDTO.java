package com.example.erp.dto;

import java.time.LocalDate;

public class EmployeeResponseDTO {

    private Long id;
    private String employeeCode;
    private String name;
    private String email;
    private String phone;
    private String departmentName;
    private String designation;
    private LocalDate joiningDate;

    public EmployeeResponseDTO() {
    }

    public EmployeeResponseDTO(
            Long id,
            String employeeCode,
            String name,
            String email,
            String phone,
            String departmentName,
            String designation,
            LocalDate joiningDate) {

        this.id = id;
        this.employeeCode = employeeCode;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentName = departmentName;
        this.designation = designation;
        this.joiningDate = joiningDate;
    }

    public Long getId() {
        return id;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDesignation() {
        return designation;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

}