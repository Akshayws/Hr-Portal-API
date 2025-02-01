package com.Api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Api.model.Payroll;

import java.util.List;

public interface PayrollRepository extends JpaRepository<Payroll, Integer> {
    List<Payroll> findByEmployee_EmployeeId(Long employeeId);
}
