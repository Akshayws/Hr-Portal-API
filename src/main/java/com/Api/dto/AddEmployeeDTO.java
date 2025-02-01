package com.Api.dto;

import java.time.LocalDate;

public class AddEmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String role;
    private String department;
    private String PhoneNo;
    private String gender;
    private LocalDate birthDate;
    private LocalDate joiningDate;

    // Constructors
    public AddEmployeeDTO() {}

    public AddEmployeeDTO(String firstName, String lastName, String email, String address, String role, String department, String PhoneNo, String gender, LocalDate birthDate, LocalDate joiningDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.role = role;
        this.department = department;
        this.PhoneNo = PhoneNo;
        this.gender = gender;
        this.birthDate = birthDate;
        this.joiningDate = joiningDate;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getImage() {
        return PhoneNo;
    }

    public void setImage(String PhoneNo) {
        this.PhoneNo = PhoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }
}
