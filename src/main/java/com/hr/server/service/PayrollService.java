package com.hr.server.service;

import com.hr.server.exception.ResourceNotFoundException;
import com.hr.server.model.Employee;
import com.hr.server.model.Payroll;
import com.hr.server.repository.EmployeeRepository;
import com.hr.server.repository.PayrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class PayrollService {

    @Autowired
    private PayrollRepository payrollRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Payroll getPayrollById(Long payrollId) {
        return payrollRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id " + payrollId));
    }
    public Payroll getPayrollByFirstName(String firstName) {
        return payrollRepository.findByEmployeeFirstName(firstName)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found for employee with first name " + firstName));
    }
    public Payroll updateOrCreatePayroll(Long employeeId, Double salary, Double bonus, Double deductions) {
        // Check if payroll exists for this employee
        Payroll payroll = payrollRepository.findByEmployeeId(employeeId)
                .orElse(null);  // Return null if no payroll found

        // Get the employee (ensuring they exist)
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID " + employeeId));

        // If payroll doesn't exist for this employee, create a new payroll entry
        if (payroll == null) {
            payroll = new Payroll(); // New Payroll object if not found
            payroll.setPayrollMonth(new Date()); // Set current date as payroll month
            payroll.setEmployee(employee);  // Link employee
        }

        // Calculate total salary
        Double totalSalary = salary + bonus - deductions;

        // Set payroll details
        payroll.setSalary(salary);
        payroll.setBonus(bonus);
        payroll.setDeductions(deductions);
        payroll.setTotalSalary(totalSalary);

        // Save payroll entry
        payroll = payrollRepository.save(payroll);

        // Update employee salary field
        employee.setSalary(salary);
        employeeRepository.save(employee);

        return payroll;
    }

    public Payroll updatePayroll(Long payrollId, Double salary, Double bonus, Double deductions) {
        // Find payroll record by ID
        Payroll payroll = payrollRepository.findById(payrollId)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll not found with id " + payrollId));

        // Calculate total salary
        Double totalSalary = salary + bonus - deductions;

        // Update payroll details
        payroll.setSalary(salary);
        payroll.setBonus(bonus);
        payroll.setDeductions(deductions);
        payroll.setTotalSalary(totalSalary);

        // Save payroll record
        payrollRepository.save(payroll);

        // Also update employee salary
        Employee employee = payroll.getEmployee();
        if (employee != null) {
            employee.setSalary(salary);  // Sync salary with payroll
            employeeRepository.save(employee);
        }

        return payroll;
    }
}
