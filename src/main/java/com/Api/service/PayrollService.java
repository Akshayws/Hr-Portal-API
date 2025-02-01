package com.Api.service;

import com.Api.model.Payroll;
import com.Api.repository.PayrollRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PayrollService {
    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    public List<Payroll> getPayrollsByEmployeeId(Long employeeId) {
        return payrollRepository.findByEmployee_EmployeeId(employeeId);
    }

    public Payroll savePayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }
}
