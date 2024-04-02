package com.ems.employeemanagement.service;

import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Project;
import com.ems.employeemanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "projects")
public class ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    @Cacheable
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id" + id));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {

        Project existingProject = projectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Project not found with id" + id));

        if (existingProject != null) {

            existingProject.setName(project.getName());
            // Update other fields as needed
            return projectRepository.save(existingProject);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
