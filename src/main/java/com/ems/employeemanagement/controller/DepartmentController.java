package com.ems.employeemanagement.controller;

import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Department;
import com.ems.employeemanagement.service.DepartmentServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@Validated
public class DepartmentController {

    private final DepartmentServices departmentServices;

    public DepartmentController(DepartmentServices departmentServices){
        this.departmentServices = departmentServices;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentServices.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentServices.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        Department createDepartment = departmentServices.createDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(createDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @Valid @RequestBody Department departmentDetails) {

        try {
            Department department = departmentServices.getDepartmentById(id);
            if (department == null) {
                return ResponseEntity.notFound().build();
            }

            department.setName(departmentDetails.getName()); // Update department name
            Department savedDepartment = departmentServices.updateDepartment(department); // Save the updated department

            return ResponseEntity.ok(savedDepartment);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        departmentServices.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
