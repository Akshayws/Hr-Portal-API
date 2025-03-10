package com.Api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long departmentId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String description;

	public Department orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
