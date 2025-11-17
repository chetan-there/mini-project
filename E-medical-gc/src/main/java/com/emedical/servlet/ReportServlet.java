package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emedical.service.ReportService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReportServlet extends HttpServlet {
    private ReportService reportService = new ReportService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        try {
            if ("patients".equals(type)) {
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=patients_report.csv");
                response.getWriter().write(reportService.generatePatientReport());
            } else if ("audit".equals(type)) {
                response.setContentType("text/csv");
                response.setHeader("Content-Disposition", "attachment; filename=audit_report.csv");
                response.getWriter().write(reportService.generateAuditLogReport());
            } else {
                request.getRequestDispatcher("jsp/reports.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}