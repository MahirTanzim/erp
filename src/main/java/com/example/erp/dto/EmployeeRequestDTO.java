package com.example.erp.dto;

import java.time.LocalDate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class EmployeeRequestDTO {

    @NotBlank(message = "Employee code is required")
    private String employeeCode;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String phone;

    @NotNull(message = "Department is required")
    private Long departmentId;

    @NotBlank(message = "Designation is required")
    private String designation;

    @NotNull(message = "Joining date is required")
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