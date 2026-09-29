package com.example.erp.controller;

import com.example.erp.dto.EmployeeResponseDTO;
import com.example.erp.dto.EmployeeRequestDTO;
import com.example.erp.entity.Employee;
import com.example.erp.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // Create employee
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PostMapping
    public EmployeeResponseDTO createEmployee(
            @RequestBody EmployeeRequestDTO request) {

        Employee employee = employeeService.toEntity(request);

        Employee savedEmployee = employeeService.createEmployee(employee);

        return employeeService.toResponseDTO(savedEmployee);
    }

    // Get all employees
    @GetMapping
    public List<EmployeeResponseDTO> getAllEmployees() {
        return employeeService.getAllEmployeeDTOs();
    }

    // Get employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id) {

        return employeeService.getEmployeeDTOById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get employee by employee code
    @GetMapping("/code/{employeeCode}")
    public ResponseEntity<Employee> getEmployeeByCode(
            @PathVariable String employeeCode) {

        return employeeService.getEmployeeByCode(employeeCode)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update employee
    @PreAuthorize("hasAnyRole('ADMIN', 'HR')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeRequestDTO request) {

        return employeeService.getEmployeeById(id)
                .map(existingEmployee -> {

                    Employee employee = employeeService.toEntity(request);
                    employee.setId(id);

                    Employee updated = employeeService.updateEmployee(employee);

                    return ResponseEntity.ok(
                            employeeService.toResponseDTO(updated));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete employee
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {

        if (employeeService.getEmployeeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}