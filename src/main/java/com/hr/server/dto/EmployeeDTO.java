package com.hr.server.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder // Add a builder for easier mapping
public class EmployeeDTO {
    private long employeeId;
    private String firstName;
    private String lastName;
    private String profilePhoto;
    private String email;
    private String phoneNumber;

    // Department name instead of ID
    private String departmentName; 

    // Role name instead of ID
    private String roleName; 

    private Date hireDate;
    private double salary; // Consider using BigDecimal if needed for precision
    private String gender;
    private String address;
    private Date birthdate;
    private String status;

    // Add comments for clarity
    // departmentName: The name of the department the employee belongs to.
    // roleName: The name of the role assigned to the employee.
}
