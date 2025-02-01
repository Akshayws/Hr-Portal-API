package com.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.model.Leave;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {
    List<Leave> findByEmployee_EmployeeId(Long employeeId);
}
