package com.Api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
	 Employee findByEmail(String email);
	    boolean existsByEmail(String email);
}
