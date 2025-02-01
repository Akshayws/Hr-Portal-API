package com.Api.config;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Api.model.Department;
import com.Api.model.Employee;
import com.Api.model.Role;
import com.Api.repository.DepartmentRepository;
import com.Api.repository.EmployeeRepository;
import com.Api.repository.RoleRepository;


@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create the HR role if it does not exist
        Role hrRole = roleRepository.findByRoleName("HR").orElse(null);
        if (hrRole == null) {
            hrRole = new Role();
            hrRole.setRoleName("HR");
            hrRole.setDescription("Super HR Role");
            roleRepository.save(hrRole);
        }

        // Check if the "HR Department" exists, if not, create it
        departmentRepository.findByName("HR Department").ifPresentOrElse(
            department -> {
                // If department exists, do nothing
                System.out.println("Department already exists: " + department.getName());
            },
            () -> {
                // If department does not exist, create and save it
                Department defaultDepartment = new Department();
                defaultDepartment.setName("HR Department");
                defaultDepartment.setDescription("Human Resources Department");
                departmentRepository.save(defaultDepartment);
                System.out.println("Created new department: HR Department");
            }
        );

        // Check if employees exist, if not, create a super admin
        if (employeeRepository.count() == 0) {
            Employee superHR = new Employee();
            superHR.setFirstName("Super");
            superHR.setLastName("Admin");
            superHR.setEmail("adminhr@company.com");
            superHR.setPassword(passwordEncoder.encode("admin123"));
            superHR.setRole(hrRole);
            superHR.setStatus(Employee.Status.ACTIVE);
            superHR.setPhoneNumber("7666910765");
            superHR.setAddress("Bangalore.");
            superHR.setGender(Employee.Gender.MALE);
            superHR.setJoiningDate(LocalDate.now());
            superHR.setSalary(BigDecimal.valueOf(50000));
            superHR.setBirthdate(LocalDate.of(2001, 3, 9));

            // Set default department value (HR Department)
            Department hrDepartment = departmentRepository.findByName("HR Department").orElse(null);
            superHR.setDepartment(hrDepartment);

            // Save super admin employee
            employeeRepository.save(superHR);
        }
    }
}
