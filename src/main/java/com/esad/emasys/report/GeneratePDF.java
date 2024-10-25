package com.esad.emasys.report;

import com.esad.emasys.dto.LeaveDTO;
import com.esad.emasys.model.Employee;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePDF {
    public  static byte[] generatePDF(List<Employee> employeeData, List<LeaveDTO> employeeLeaves){
    ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(out);
        PdfDocument pdfDocument= new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);

        document.add(new Paragraph("Employee Leave report"));
        document.add(new Paragraph("Employee Info"));

        Table table = new Table(new float[]{3,3,3,3,3}); // table to add Employee personal data
        table.addCell(new Cell().add(new Paragraph("Name")));
        table.addCell(new Cell().add(new Paragraph("Email")));
        table.addCell(new Cell().add(new Paragraph("Department")));
        table.addCell(new Cell().add(new Paragraph("Position")));

        for(Employee employee:employeeData){
            table.addCell(new Cell().add(new Paragraph(employee.getFirstName()+" "+employee.getLastName())));
            table.addCell(new Cell().add(new Paragraph(employee.getEmail())));
            table.addCell(new Cell().add(new Paragraph(employee.getDepartment().getName())));
            table.addCell(new Cell().add(new Paragraph(employee.getPosition().getTitle())));
        }

        document.add(table);

        document.add(new Paragraph("Employee Leave Details"));

        Table table2 = new Table(new float[]{3,3,3,3,3}); //table to add leave related data
        table2.addCell(new Cell().add(new Paragraph("Requested Date")));
        table2.addCell(new Cell().add(new Paragraph("Start Date")));
        table2.addCell(new Cell().add(new Paragraph("End Date")));
        table2.addCell(new Cell().add(new Paragraph("Leave Type")));
        table2.addCell(new Cell().add(new Paragraph("Status")));

        for(LeaveDTO leaveRequest:employeeLeaves){
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(leaveRequest.getRequestedDate()))));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(leaveRequest.getStartDate()))));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(leaveRequest.getEndDate()))));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(leaveRequest.getLeaveType()))));
            table2.addCell(new Cell().add(new Paragraph(String.valueOf(leaveRequest.getLeaveStatus()))));
        }

        document.add(table2);
        document.close();
        return out.toByteArray();
    }
}
