package com.example.erp.dto;

public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String role;
    private Long employeeId;

    public UserResponseDTO() {
    }

    public UserResponseDTO(
            Long id,
            String username,
            String email,
            String role,
            Long employeeId) {

        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
        this.employeeId = employeeId;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Long getEmployeeId() {
        return employeeId;
    }
}