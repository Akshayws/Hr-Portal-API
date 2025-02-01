package com.Api.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")  // Table name in the database
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generate primary key
    private Long id;

    @Column(nullable = false, unique = true)  // Email must be unique
    private String email;

    @Column(nullable = false)  // Password cannot be null
    private String password;

    @Column(nullable = false)  // Role must be provided (e.g., ROLE_HR, ROLE_EMPLOYEE)
    private String role;

    @Column(nullable = false)  // Automatically set to current timestamp when the user is created
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getters and Setters for the fields
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
