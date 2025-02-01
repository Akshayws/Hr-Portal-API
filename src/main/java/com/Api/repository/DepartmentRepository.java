package com.Api.repository;

import com.Api.model.Department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);  // Using Optional to avoid null returns
}
