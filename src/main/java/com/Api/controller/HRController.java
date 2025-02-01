package com.Api.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.Api.model.Employee;
import com.Api.model.User;
import com.Api.repository.UserRepository;
import com.Api.service.EmployeeService;
import com.Api.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/hr")
public class HRController {
	private PasswordEncoder passwordEncoder; 
    private final EmployeeService employeeService;
    private final UserService userService;
    private final UserRepository userRepository;

    // Constructor injection for EmployeeService and UserService
    public HRController(EmployeeService employeeService, UserService userService) 
    { 
    	this.employeeService = employeeService;
        this.userService = userService;
	    this.userRepository = null;
    
    }

    // Fetch all employees
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    // Add a new employee (without login credentials)
    @PostMapping("/employees")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addEmployee(employee));
    } 
 
    // Add a new employee with login credentials
    @PostMapping("/employees/with-login")
    public ResponseEntity<String> addEmployeeWithLogin(@RequestBody Employee employee, @RequestParam String password) {
        // Step 1: Add employee details (EmployeeService)
        Employee savedEmployee = employeeService.addEmployee(employee);

        // Step 2: Create login credentials for the employee (UserService)
        User user = new User();
        user.setEmail(savedEmployee.getEmail());
        user.setPassword(passwordEncoder.encode(password)); // Encrypt the password using BCrypt
        user.setRole("ROLE_EMPLOYEE"); // Set the role (you can dynamically set it based on employee role)
        userRepository.save(user); // Save user credentials to the database

        return ResponseEntity.ok("Employee and login created successfully!");
    }


    // Update employee details
    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDetails));
    }

    // Delete an employee
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
