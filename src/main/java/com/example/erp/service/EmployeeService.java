package com.example.erp.service;

import com.example.erp.annotation.Auditable;
import com.example.erp.dto.EmployeeRequestDTO;
import com.example.erp.repository.DepartmentRepository;
import com.example.erp.dto.EmployeeResponseDTO;
import com.example.erp.entity.Department;
import com.example.erp.entity.Employee;
import com.example.erp.exception.ResourceNotFoundException;
import com.example.erp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;

    }

    @Auditable(action = "CREATE", entity = "Employee")
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Optional<Employee> getEmployeeByCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode);
    }

    @Auditable(action = "UPDATE", entity = "Employee")
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);

    }

    @Auditable(action = "DELETE", entity = "Employee")
    public Employee deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        employeeRepository.delete(employee);

        return employee;
    }

    public EmployeeResponseDTO toResponseDTO(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getEmployeeCode(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getDepartment() != null
                        ? employee.getDepartment().getName()
                        : null,
                employee.getDesignation(),
                employee.getJoiningDate());
    }

    public List<EmployeeResponseDTO> getAllEmployeeDTOs() {

        return employeeRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    public Optional<EmployeeResponseDTO> getEmployeeDTOById(Long id) {

        return employeeRepository.findById(id)
                .map(this::toResponseDTO);
    }

    public Employee toEntity(EmployeeRequestDTO dto) {

        Department department = null;

        if (dto.getDepartmentId() != null) {
            department = departmentRepository
                    .findById(dto.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        }

        Employee employee = new Employee();

        employee.setEmployeeCode(dto.getEmployeeCode());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setDepartment(department);
        employee.setDesignation(dto.getDesignation());
        employee.setJoiningDate(dto.getJoiningDate());

        return employee;
    }

}