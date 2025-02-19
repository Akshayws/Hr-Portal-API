package com.hr.server.service;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Cell;
import org.springframework.stereotype.Service;

import com.hr.server.model.Payroll;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public byte[] generatePayrollPdf(Payroll payroll) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            // Initialize PDF writer and document
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Add Title
            document.add(new com.itextpdf.layout.element.Paragraph("Payroll Slip").setBold().setFontSize(18));

            // Create Table
            Table table = new Table(2); // 2 columns
            table.setWidth(100);

            table.addCell(new Cell().add(new com.itextpdf.layout.element.Paragraph("Field").setBold()));
            table.addCell(new Cell().add(new com.itextpdf.layout.element.Paragraph("Value").setBold()));




            // Add Table Rows
            addTableRows(table, payroll);

            // Add Table to Document
            document.add(table);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return out.toByteArray();
    }

    private void addTableRows(Table table, Payroll payroll) {
        table.addCell("Employee ID");
        table.addCell(String.valueOf(payroll.getEmployee().getId()));
        table.addCell("First Name");
        table.addCell(payroll.getEmployee().getFirstName());
        table.addCell("Last Name");
        table.addCell(payroll.getEmployee().getLastName());
        table.addCell("Salary");
        table.addCell(String.valueOf(payroll.getSalary()));
        table.addCell("Bonus");
        table.addCell(String.valueOf(payroll.getBonus()));
        table.addCell("Deductions");
        table.addCell(String.valueOf(payroll.getDeductions()));
        table.addCell("Total Salary");
        table.addCell(String.valueOf(payroll.getTotalSalary()));
    }
}
