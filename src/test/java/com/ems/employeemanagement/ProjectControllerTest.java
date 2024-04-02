package com.ems.employeemanagement;


import com.ems.employeemanagement.controller.ProjectController;
import com.ems.employeemanagement.model.Project;
import com.ems.employeemanagement.service.ProjectServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {
    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectServices projectService;

    @Test
    public void testGetAllProjects() {
        // Setup
        List<Project> projects = Arrays.asList(new Project(), new Project());
        when(projectService.getAllProjects()).thenReturn(projects);

        // Test
        ResponseEntity<List<Project>> response = projectController.getAllProjects();

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projects, response.getBody());
    }

    @Test
    public void testGetProjectById() {
        // Setup
        Long id = 1L;
        Project project = new Project();
        project.setId(id);
        when(projectService.getProjectById(id)).thenReturn(project);

        // Test
        ResponseEntity<Project> response = projectController.getProjectById(id);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(project, response.getBody());
    }

    @Test
    public void testCreateProject() {
        // Setup
        Project project = new Project();
        project.setName("Project X");

        // Test
        ResponseEntity<Project> response = projectController.createProject(project);

        // Verify
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(project, response.getBody());
    }

    @Test
    public void testUpdateProject() {
        // Setup
        Long id = 1L;
        Project existingProject = new Project();
        existingProject.setId(id);
        existingProject.setName("Project X");

        Project updatedProject = new Project();
        updatedProject.setId(id);
        updatedProject.setName("Updated Project");

        // Test
        ResponseEntity<Project> response = projectController.updateProject(id, updatedProject);

        // Verify
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProject, response.getBody());
    }
}
