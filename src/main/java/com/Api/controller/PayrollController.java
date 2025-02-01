package com.Api.controller;

import com.Api.model.Payroll;
import com.Api.service.PayrollService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {
    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<List<Payroll>> getPayrollsByEmployee(@PathVariable Long employeeId) {
        return ResponseEntity.ok(payrollService.getPayrollsByEmployeeId(employeeId));
    }

    @PostMapping("/create")
    public ResponseEntity<Payroll> createPayroll(@RequestBody Payroll payroll) {
        return ResponseEntity.ok(payrollService.savePayroll(payroll));
    }
}
