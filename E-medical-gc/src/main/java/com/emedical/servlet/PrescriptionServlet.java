package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import com.emedical.service.AuditLogService;
import com.emedical.service.PrescriptionService;
import com.emedical.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PrescriptionServlet extends HttpServlet {
    private PrescriptionService prescriptionService = new PrescriptionService();
    private AuditLogService auditService = new AuditLogService();
    private UserService userService = new UserService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                request.setAttribute("prescriptions", prescriptionService.getAllPrescriptions());
                request.getRequestDispatcher("jsp/prescriptions.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("prescription", prescriptionService.getPrescriptionById(id));
                request.getRequestDispatcher("jsp/add_prescription.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            int doctorId = userService.getUserId(username);
            if ("add".equals(action)) {
                int patientId = Integer.parseInt(request.getParameter("patientId"));
                LocalDate prescriptionDate = LocalDate.parse(request.getParameter("prescriptionDate"));
                String details = request.getParameter("details");
                prescriptionService.addPrescription(patientId, doctorId, prescriptionDate, details);
                auditService.logAction(doctorId, "Added prescription for patient ID: " + patientId);
                response.sendRedirect("PrescriptionServlet?action=list");
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                LocalDate prescriptionDate = LocalDate.parse(request.getParameter("prescriptionDate"));
                String details = request.getParameter("details");
                prescriptionService.updatePrescription(id, prescriptionDate, details);
                auditService.logAction(doctorId, "Updated prescription ID: " + id);
                response.sendRedirect("PrescriptionServlet?action=list");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                prescriptionService.deletePrescription(id);
                auditService.logAction(doctorId, "Deleted prescription ID: " + id);
                response.sendRedirect("PrescriptionServlet?action=list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}