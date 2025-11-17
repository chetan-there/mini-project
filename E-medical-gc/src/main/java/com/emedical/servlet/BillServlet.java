package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.emedical.service.AuditLogService;
import com.emedical.service.BillService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BillServlet extends HttpServlet {
    private BillService billService = new BillService();
    private AuditLogService auditService = new AuditLogService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("bills", billService.getAllBills());
            request.getRequestDispatcher("jsp/bills.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            double amount = Double.parseDouble(request.getParameter("amount"));
            LocalDate billDate = LocalDate.parse(request.getParameter("billDate"));
            String details = request.getParameter("details");
            billService.addBill(patientId, amount, billDate, details);
            auditService.logAction(null, "Added bill for patient ID: " + patientId);
            response.sendRedirect("BillServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}