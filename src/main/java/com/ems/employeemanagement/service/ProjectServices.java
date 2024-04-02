package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Project;
import com.ems.employeemanagement.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServices {

    @Autowired
    private ProjectRepository projectRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
        //                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "Project not found with id " + id));
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project updateProject(Long id, Project project) {

        Project existingProject = projectRepository.findById(id).orElse(null);

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
