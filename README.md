# Employee Management System

## Project Overview
The Employee Management System is a Java Spring Boot application designed to manage employees, departments, and projects within a company. It provides RESTful API endpoints for performing CRUD operations on employee data and includes features such as security, validation, audit logging, and performance optimization.

## Folder Structure
```
employeemanagement/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com/
│ │ │ └── ems/
│ │ │ └── employeemanagement/
│ │ │ ├── controller/
│ │ │ │ ├── EmployeeController.java
│ │ │ │ ├── DepartmentController.java
│ │ │ │ └── ProjectController.java
│ │ │ ├── aspect/
│ │ │ │ └── AuditAspect.java
│ │ │ ├── exception/
│ │ │ │ ├── ResourceNotFoundException.java
│ │ │ │ └── GlobalExceptionHandler.java
│ │ │ ├── model/
│ │ │ │ ├── Employee.java
│ │ │ │ ├── Department.java
│ │ │ │ └── Project.java
│ │ │ ├── repository/
│ │ │ │ ├── EmployeeRepository.java
│ │ │ │ ├── DepartmentRepository.java
│ │ │ │ └── EmployeeRepository.java
│ │ │ ├── service/
│ │ │ │ ├── EmployeeService.java
│ │ │ │ ├── DepartmentService.java
│ │ │ │ └── ProjectRepository.java
│ │ │ ├── config/
│ │ │ │ └── SecurityConfig.java
│ │ │ └── Application.java
│ │ └── resources/
│ │ └── application.properties
│ └── test/
│ └── java/
│ └── com/
│ └── ems/
│ └── employeemanagementsystem/
│ ├── EmployeeControllerIntegrationTests.java
│ ├── EmployeeControllerTest.java
│ ├── DepartentControllerTest.java
│ └── ProjectControllerTest.java
├── pom.xml
└── README.md
```

## Features Implemented
- CRUD operations for employees, departments, and projects
- Basic authentication for API security
- Request payload validation using Spring's validation annotations
- Audit logging for entity CRUD operations
- Performance optimization using Spring Boot's caching mechanisms

## API Endpoints
- `GET /api/employees`: Get all employees
- `GET /api/employees/{id}`: Get employee by ID
- `POST /api/employees`: Create a new employee
- `PUT /api/employees/{id}`: Update employee details
- `DELETE /api/employees/{id}`: Delete employee by ID
- Other endpoints for departments and projects

## Build and Run Instructions
1. Clone the repository: `git clone https://github.com/JWEi1994/EmployeeManagementSystem.git`
2. Navigate to the project directory: `cd EmployeeManagementSystem`
3. Build the application: `mvn clean package`
4. Run the application: `java -jar target/EmployeeManagementSystem.jar`

## Notes
- Replace `{id}` with the actual ID of the employee, department, or project in the API endpoints.
- Customize the security, validation, and caching settings in `SecurityConfig`, `GlobalExceptionHandler`, and service classes as needed.
