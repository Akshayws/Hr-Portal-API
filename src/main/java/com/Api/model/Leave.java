package com.Api.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "leaves")  // Renamed table to follow conventions
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int leaveId;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(nullable = false)
    private String leaveType;

    @Column(nullable = false)
    private LocalDate startDate;  // Changed from Date to LocalDate

    @Column(nullable = false)
    private LocalDate endDate;  // Changed from Date to LocalDate

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status = Status.PENDING;  // Default status is PENDING

    private String reason;

    public enum Status {
        APPROVED, PENDING, REJECTED
    }
}
