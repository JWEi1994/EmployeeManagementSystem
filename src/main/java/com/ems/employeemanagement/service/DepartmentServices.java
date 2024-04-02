package com.ems.employeemanagement.service;

import com.ems.employeemanagement.exception.ResourceNotFoundException;
import com.ems.employeemanagement.model.Department;
import com.ems.employeemanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@CacheConfig(cacheNames = "departments")
public class DepartmentServices {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Cacheable
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id" + id));
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found with id" + id));
        if (existingDepartment != null) {
            existingDepartment.setName(department.getName());
            // Update other fields as needed
            return departmentRepository.save(existingDepartment);
        }
        return null;
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
