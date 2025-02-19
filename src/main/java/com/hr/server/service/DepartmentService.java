package com.hr.server.service;

import com.hr.server.model.Department;
import com.hr.server.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // ✅ Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // ✅ Get department by name (Fix Optional issue)
    public Department getDepartmentByName(String name) {
        return departmentRepository.findByDName(name).orElse(null); // ✅ Now correctly handling Optional
    }

    // ✅ Create a new department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // ✅ Get department by ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }
}


