package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emedical.dao.PatientDAO.Patient;
import com.emedical.service.AuditLogService;
import com.emedical.service.PatientService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PatientServlet extends HttpServlet {
    private PatientService patientService = new PatientService();
    private AuditLogService auditService = new AuditLogService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                request.setAttribute("patients", patientService.getAllPatients());
                request.getRequestDispatcher("jsp/patients.jsp").forward(request, response);
            } else if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("patient", patientService.getPatientById(id));
                request.getRequestDispatcher("jsp/add_patient.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("add".equals(action)) {
                Patient patient = new Patient();
                patient.setName(request.getParameter("name"));
                patient.setAge(Integer.parseInt(request.getParameter("age")));
                patient.setGender(request.getParameter("gender"));
                patient.setAddress(request.getParameter("address"));
                patient.setPhone(request.getParameter("phone"));
                patientService.addPatient(patient);
                auditService.logAction(null, "Added patient: " + patient.getName());
                response.sendRedirect("PatientServlet?action=list");
            } else if ("update".equals(action)) {
                Patient patient = new Patient();
                patient.setId(Integer.parseInt(request.getParameter("id")));
                patient.setName(request.getParameter("name"));
                patient.setAge(Integer.parseInt(request.getParameter("age")));
                patient.setGender(request.getParameter("gender"));
                patient.setAddress(request.getParameter("address"));
                patient.setPhone(request.getParameter("phone"));
                patientService.updatePatient(patient);
                auditService.logAction(null, "Updated patient: " + patient.getName());
                response.sendRedirect("PatientServlet?action=list");
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                patientService.deletePatient(id);
                auditService.logAction(null, "Deleted patient ID: " + id);
                response.sendRedirect("PatientServlet?action=list");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}