package com.hr.server.controller;

import com.hr.server.model.Employee;
import com.hr.server.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Create Employee
    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
    }

    // Get all Employees
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllEmployees() {
        List<Map<String, Object>> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    // Get Employee by ID
    @GetMapping("/{employeeId}")
    public ResponseEntity<Map<String, Object>> getEmployeeById(@PathVariable long employeeId) {
        Map<String, Object> employee = employeeService.getEmployeeById(employeeId);
        return employee != null 
                ? ResponseEntity.ok(employee) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Update Employee
    @PutMapping("/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long employeeId, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employeeDetails);
        return updatedEmployee != null 
                ? ResponseEntity.ok(updatedEmployee) 
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    @GetMapping("/getEmail/{email}")
    public ResponseEntity<Employee> getEmployeeByEmail(@PathVariable String email) {
        Employee employee = employeeService.getEmployeeByEmail(email);
        return ResponseEntity.ok(employee);
    }
    
    // Delete Employee
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long employeeId) {
        Map<String, Object> employee = employeeService.getEmployeeById(employeeId);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Employee not found");
        }
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("✅ Employee deleted successfully");
    }
}
