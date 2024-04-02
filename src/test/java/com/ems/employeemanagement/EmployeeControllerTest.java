package com.ems.employeemanagement;

import com.ems.employeemanagement.controller.EmployeeController;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.service.EmployeeServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeServices employeeServices;

    @Test
    public void testGetAllEmployees() {
        // Setup
        List<Employee> employees = Arrays.asList(new Employee(), new Employee());
        Mockito.when(employeeServices.getAllEmployees()).thenReturn(employees);

        // Test
        ResponseEntity<List<Employee>> response = employeeController.getAllEmployees();

        // Verify

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    public void testGetEmployeeById() {
        // Setup
        Long id = 1L;
        Employee employee = new Employee();
        employee.setId(id);
        Mockito.when(employeeServices.getEmployeeById(id)).thenReturn(employee);

        // Test
        ResponseEntity<Employee> response = employeeController.getEmployeeById(id);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employee, response.getBody());
    }

    @Test
    public void testCreateEmployee() {
        // Setup
        Employee employee = new Employee();
        employee.setName("John Doe");
        employee.setPosition("Software Engineer");

        // Test
        ResponseEntity<Employee> response = employeeController.createEmployee(employee);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());

        // Optional: Verify the URI in the Location header
        assertNotNull(response.getHeaders().getLocation());
        assertEquals("/api/employees/" + response.getBody().getId(), response.getHeaders().getLocation().getPath());
    }

    @Test
    public void testUpdateEmployee() {
        // Setup
        Long id = 1L;
        Employee updatedEmployee = new Employee(1L,"Ali","Software Engineer",1L);

        // Test
        ResponseEntity<Employee> response = employeeController.updateEmployee(id, updatedEmployee);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void testDeleteEmployee() {
        // Setup
        Long id = 1L;

        // Test
        ResponseEntity<Void> response = employeeController.deleteEmployee(id);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
