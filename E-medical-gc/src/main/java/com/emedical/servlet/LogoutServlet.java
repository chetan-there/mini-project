package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emedical.service.AuditLogService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
    private AuditLogService auditService = new AuditLogService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            try {
                auditService.logAction(null, "Logout: " + username);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            session.invalidate();
        }
        response.sendRedirect("jsp/login.jsp");
    }
}