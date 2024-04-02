package com.ems.employeemanagement.service;

import com.ems.employeemanagement.model.Department;
import com.ems.employeemanagement.model.Employee;
import com.ems.employeemanagement.repository.DepartmentRepository;
import com.ems.employeemanagement.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {

//        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new EntityNotFoundException("Department not found"));
//
//        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

        if (existingEmployee != null) {

            existingEmployee.setName(employee.getName());
            existingEmployee.setPosition(employee.getPosition());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setProjects(employee.getProjects());
            return employeeRepository.save(existingEmployee);

        }

        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
