package com.ems.employeemanagement;

import com.ems.employeemanagement.controller.DepartmentController;
import com.ems.employeemanagement.model.Department;
import com.ems.employeemanagement.service.DepartmentServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {
    @InjectMocks
    private DepartmentController departmentController;

    @Mock
    private DepartmentServices departmentService;

    @Test
    public void testGetAllDepartments() {

        // Setup
        List<Department> departments = Arrays.asList(new Department(), new Department());
        Mockito.when(departmentService.getAllDepartments()).thenReturn(departments);

        // Test
        ResponseEntity<List<Department>> response = (ResponseEntity<List<Department>>) departmentController.getAllDepartments();

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(departments, response.getBody());
    }

    @Test
    public void testGetDepartmentById() {
        // Setup
        Long id = 1L;
        Department department = new Department();
        department.setId(id);
        when(departmentService.getDepartmentById(id)).thenReturn(department);

        // Test
        ResponseEntity<Department> response = departmentController.getDepartmentById(id);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(department, response.getBody());
    }

    @Test
    public void testCreateDepartment() {
        // Setup
        Department department = new Department();
        department.setName("Engineering");

        // Mock the service method
        when(departmentService.createDepartment(department)).thenReturn(department);

        // Test
        ResponseEntity<Department> response = departmentController.createDepartment(department);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(department, response.getBody());
    }

    @Test
    public void testUpdateDepartment() {
        // Setup
        Long id = 1L;
        Department existingDepartment = new Department();
        existingDepartment.setId(id);
        existingDepartment.setName("Engineering");

        Department updatedDepartment = new Department();
        updatedDepartment.setId(id);
        updatedDepartment.setName("Updated Department");

        // Mock the service method
        when(departmentService.getDepartmentById(id)).thenReturn(existingDepartment);
        when(departmentService.updateDepartment(id, updatedDepartment)).thenReturn(updatedDepartment);

        // Test
        ResponseEntity<Department> response = departmentController.updateDepartment(id, updatedDepartment);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedDepartment, response.getBody());
    }
}
