package com.hr.server.controller;

import com.hr.server.model.Department;
import com.hr.server.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    // ✅ Fix: Inject DepartmentService through constructor
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // ✅ Fix: Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

  

    // ✅ Fix: Get department by name
    @GetMapping("/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Department department = departmentService.getDepartmentByName(name);
        return department != null ? ResponseEntity.ok(department) : ResponseEntity.notFound().build();
    }

    // ✅ Fix: Create department
    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }
}
