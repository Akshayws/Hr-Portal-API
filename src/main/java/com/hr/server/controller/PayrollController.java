package com.hr.server.controller;

import com.hr.server.model.Payroll;
import com.hr.server.service.PayrollService;
import com.hr.server.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    @Autowired
    private PayrollService payrollService;
    
    @Autowired
    private PdfService pdfService;

    // ✅ Fix: Closing } was missing
    @GetMapping("/{payrollId}/pdf")
    public ResponseEntity<byte[]> downloadPayrollPdf(@PathVariable Long payrollId) {
        Payroll payroll = payrollService.getPayrollById(payrollId);

        byte[] pdf = pdfService.generatePayrollPdf(payroll);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=payroll_slip_" + payrollId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF) // ✅ Fixed APPLICATION_PDF usage
                .body(pdf);
    } // ✅ Added missing closing bracket

    // Update payroll for a specific employee
    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Payroll> updatePayroll(
            @PathVariable Long employeeId,
            @RequestBody Payroll payrollRequest) {

        Payroll updatedPayroll = payrollService.updateOrCreatePayroll(
                employeeId,
                payrollRequest.getSalary(),
                payrollRequest.getBonus(),
                payrollRequest.getDeductions()
        );

        return ResponseEntity.ok(updatedPayroll);
    }

    // Get Payroll details by ID
    @GetMapping("/{payrollId}")
    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long payrollId) {
        Payroll payroll = payrollService.getPayrollById(payrollId);
        return ResponseEntity.ok(payroll);
    }

    // Get Payroll by Employee First Name
    @GetMapping("/employee/first-name/{firstName}")
    public ResponseEntity<Payroll> getPayrollByFirstName(@PathVariable String firstName) {
        Payroll payroll = payrollService.getPayrollByFirstName(firstName);
        return ResponseEntity.ok(payroll);
    }
}
