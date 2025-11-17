package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.emedical.dao.DispensingDAO.Dispensing;
import com.emedical.service.AuditLogService;
import com.emedical.service.DispensingService;
import com.emedical.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class DispensingServlet extends HttpServlet {
    private DispensingService dispensingService = new DispensingService();
    private AuditLogService auditService = new AuditLogService();
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("dispensings", dispensingService.getAllDispensing());
            request.getRequestDispatcher("jsp/dispensing.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            int pharmacistId = userService.getUserId(username);
            Dispensing dispensing = new Dispensing();
            dispensing.setPrescriptionId(Integer.parseInt(request.getParameter("prescriptionId")));
            dispensing.setPharmacistId(pharmacistId);
            dispensing.setDispenseDate(LocalDate.parse(request.getParameter("dispenseDate")));
            dispensing.setMedicinesDispensed(request.getParameter("medicinesDispensed"));
            dispensingService.addDispensing(dispensing);
            auditService.logAction(pharmacistId, "Dispensed prescription ID: " + dispensing.getPrescriptionId());
            response.sendRedirect("DispensingServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}