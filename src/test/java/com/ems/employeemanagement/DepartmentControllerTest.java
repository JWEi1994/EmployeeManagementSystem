package com.ems.employeemanagement;

import com.ems.employeemanagement.controller.DepartmentController;
import com.ems.employeemanagement.model.Department;
import com.ems.employeemanagement.service.DepartmentServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
public class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
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
        ResponseEntity<List<Department>> response = departmentController.getAllDepartments();

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
    public void testCreateDepartment() throws Exception {
        // Setup
        Department department = new Department();
        department.setName("Engineering");

        Department createdDepartment = new Department();
        createdDepartment.setId(1L);
        createdDepartment.setName("Engineering");

        given(departmentService.createDepartment(department)).willReturn(createdDepartment);

        // Test
        mockMvc.perform(post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(department)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Engineering")));
    }

    @Test
    public void testUpdateDepartment() throws Exception {
        // Setup
        Long id = 1L;
        Department updatedDepartment = new Department();
        updatedDepartment.setId(id);
        updatedDepartment.setName("Engineering");

        Department existingDepartment = new Department();
        existingDepartment.setId(id);
        existingDepartment.setName("Department");

        given(departmentService.getDepartmentById(id)).willReturn(existingDepartment);
        given(departmentService.updateDepartment(existingDepartment)).willReturn(updatedDepartment);


        // Test
        mockMvc.perform(put("/api/departments/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedDepartment)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Updated Department")));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
