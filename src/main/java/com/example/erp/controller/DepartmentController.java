package com.example.erp.controller;

import com.example.erp.entity.Department;
import com.example.erp.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // Create department
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public Department createDepartment(
            @RequestBody Department department) {

        return departmentService.createDepartment(department);
    }

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {

        return departmentService.getAllDepartments();
    }

    // Get department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(
            @PathVariable Long id) {

        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update department
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable Long id,
            @RequestBody Department department) {

        return departmentService.getDepartmentById(id)
                .map(existingDepartment -> {

                    department.setId(id);

                    Department updated = departmentService.updateDepartment(department);

                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete department
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(
            @PathVariable Long id) {

        if (departmentService.getDepartmentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
}