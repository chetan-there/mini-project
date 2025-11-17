package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emedical.service.AuditLogService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuditLogServlet extends HttpServlet {
    private AuditLogService auditService = new AuditLogService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("logs", auditService.getAllLogs());
            request.getRequestDispatcher("jsp/audit_logs.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}