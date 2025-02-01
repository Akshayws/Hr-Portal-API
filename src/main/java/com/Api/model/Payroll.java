package com.Api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payrollId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private LocalDate payrollMonth;  // Changed from Date to LocalDate

    @Column(nullable = false)
    private double salary;

    @Column(nullable = false)
    private double bonus;

    @Column(nullable = false)
    private double deductions;

    // Remove totalSalary as a column; instead, calculate it dynamically
    public double getTotalSalary() {
        return salary + bonus - deductions;
    }
}
