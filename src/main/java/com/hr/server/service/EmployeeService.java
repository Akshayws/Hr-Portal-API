package com.hr.server.service;

import com.hr.server.exception.ResourceNotFoundException;
import com.hr.server.model.Department;
import com.hr.server.model.Employee;

import java.util.ArrayList;
import com.hr.server.model.Role;
import com.hr.server.model.User;
import com.hr.server.repository.AttendanceRepository;
import com.hr.server.repository.DepartmentRepository;
import com.hr.server.repository.EmployeeRepository;
import com.hr.server.repository.LeaveRepository;
import com.hr.server.repository.PayrollRepository;
import com.hr.server.repository.RoleRepository;
import com.hr.server.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service

@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    
    private DepartmentRepository departmentRepository;

    
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private final AttendanceRepository attendanceRepository ;

    @Autowired
    private final LeaveRepository leaveRepository ;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public Employee createEmployee(Employee employee) {
        // ✅ Fetch department & role from database
    	 String departmentName = employee.getDepartment().getDName();
    	 Department department = departmentRepository.findByDName(employee.getDepartment().getDName())
                 
    	 .orElseGet(() -> {
             Department newDept = new Department();
             newDept.setDName(departmentName);
             return departmentRepository.save(newDept);
         });;
         // 🔹 Fetch role by name
         Role role = roleRepository.findByRoleName(employee.getRole().getRoleName())
                 .orElseThrow(() -> new RuntimeException("❌ Role not found"));

        // ✅ Set department & role
        employee.setDepartment(department);
        employee.setRole(role);

        // ✅ 1️⃣ First Save Employee
        employee = employeeRepository.save(employee);
        
        // ✅ 2️⃣ Then Create & Save User
        String defaultPassword = "Welcome@123";
        String encodedPassword = passwordEncoder.encode(defaultPassword);

        User user = new User();
        user.setEmail(employee.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(role);
        user.setEmployee(employee);

        userRepository.save(user);

        // ✅ 3️⃣ Associate Employee with User & Update Employee
        employee.setUser(user);
        return employeeRepository.save(employee);
      
    }




    // Fetch employee by ID
    public Employee getEmployeeByyId(long employeeId) {
        Optional<Employee> employee = employeeRepository.findById(employeeId);
        return employee.orElse(null);
    }
    
    

    public Map<String, Object> getEmployeeById(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("❌ Employee not found"));

        // ✅ Employee details in Map format
        Map<String, Object> empData = new HashMap<>();
        empData.put("employeeId", employee.getId());
        empData.put("firstName", employee.getFirstName());
        empData.put("lastName", employee.getLastName());
        empData.put("profilePhoto", employee.getProfilePhoto());
        empData.put("email", employee.getEmail());
        empData.put("phoneNumber", employee.getPhoneNumber());
        empData.put("hireDate", employee.getHireDate());
        empData.put("salary", employee.getSalary());
        empData.put("gender", employee.getGender());
        empData.put("address", employee.getAddress());
        empData.put("birthdate", employee.getBirthdate());
        empData.put("status", employee.getStatus());

        // ✅ Ensure department & role names are included
        if (employee.getDepartment() != null) {
            empData.put("departmentName", employee.getDepartment().getDName());
        } else {
            empData.put("departmentName", "N/A");
        }

        if (employee.getRole() != null) {
            empData.put("roleName", employee.getRole().getRoleName());
        } else {
            empData.put("roleName", "N/A");
        }

        return empData;
    }
    public Employee getEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employee not found with email: " + email));
    }


    public List<Map<String, Object>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<Map<String, Object>> employeeList = new ArrayList<>();

        for (Employee employee : employees) {
            Map<String, Object> empData = new HashMap<>();
            empData.put("employeeId", employee.getId());
            empData.put("firstName", employee.getFirstName());
            empData.put("lastName", employee.getLastName());
            empData.put("profilePhoto", employee.getProfilePhoto());
            empData.put("email", employee.getEmail());
            empData.put("phoneNumber", employee.getPhoneNumber());
            empData.put("hireDate", employee.getHireDate());
            empData.put("salary", employee.getSalary());
            empData.put("gender", employee.getGender());
            empData.put("address", employee.getAddress());
            empData.put("birthdate", employee.getBirthdate());
            empData.put("status", employee.getStatus());

            // ✅ Department & Role Names Include 
            empData.put("departmentName", (employee.getDepartment() != null) ? employee.getDepartment().getDName() : "N/A");
            empData.put("roleName", (employee.getRole() != null) ? employee.getRole().getRoleName() : "N/A");

            employeeList.add(empData);
        }

        return employeeList;
    }
    public Employee updateEmployee(long employeeId, Employee employeeDetails) {
        // ✅ Check if employee exists
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("❌ Employee not found with ID: " + employeeId));

        // 🔹 Update basic details
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setProfilePhoto(employeeDetails.getProfilePhoto());
        employee.setHireDate(employeeDetails.getHireDate());
        employee.setSalary(employeeDetails.getSalary());
        employee.setGender(employeeDetails.getGender());
        employee.setAddress(employeeDetails.getAddress());
        employee.setBirthdate(employeeDetails.getBirthdate());
        employee.setStatus(employeeDetails.getStatus());

        // ✅ Fetch & update department by name
        if (employeeDetails.getDepartment() != null && employeeDetails.getDepartment().getDName() != null) {
            Department department = departmentRepository.findByDName(employeeDetails.getDepartment().getDName())
                    .orElseThrow(() -> new ResourceNotFoundException("❌ Department not found: " + employeeDetails.getDepartment().getDName()));
            employee.setDepartment(department);
        }

        // ✅ Fetch & update role by name
        if (employeeDetails.getRole() != null && employeeDetails.getRole().getRoleName() != null) {
            Role role = roleRepository.findByRoleName(employeeDetails.getRole().getRoleName())
                    .orElseThrow(() -> new ResourceNotFoundException("❌ Role not found: " + employeeDetails.getRole().getRoleName()));
            employee.setRole(role);
        }

        // ✅ Update employee
        return employeeRepository.save(employee);
    }
  

    public void deleteEmployee(long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));

        // Delete related records manually
        attendanceRepository.deleteByEmployeeId(employeeId);
        leaveRepository.deleteByEmployeeId(employeeId);
        payrollRepository.deleteByEmployeeId(employeeId);
//        userRepository.deleteByEmployeeId(employeeId);

        // Now delete the employee
        employeeRepository.delete(employee);
    }


}