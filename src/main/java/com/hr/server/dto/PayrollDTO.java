package com.hr.server.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayrollDTO {
    private long employeeId;
    private double salary;
    private double bonus;
    private double deductions;
    private double totalSalary;
}
