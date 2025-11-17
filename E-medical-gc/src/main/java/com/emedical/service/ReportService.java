package com.emedical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.emedical.dao.AuditLogDAO;
import com.emedical.dao.PatientDAO;

public class ReportService {
    private PatientDAO patientDAO = new PatientDAO();
    private AuditLogDAO auditLogDAO = new AuditLogDAO();

    public String generatePatientReport() throws SQLException {
        List<PatientDAO.Patient> patients = patientDAO.getAllPatients();
        return patients.stream()
                .map(p -> String.format("%d,%s,%d,%s,%s,%s", 
                        p.getId(), p.getName(), p.getAge(), p.getGender(), 
                        p.getAddress(), p.getPhone()))
                .collect(Collectors.joining("\n", "ID,Name,Age,Gender,Address,Phone\n", ""));
    }

    public String generateAuditLogReport() throws SQLException {
        List<AuditLogDAO.AuditLog> logs = auditLogDAO.getAllLogs();
        return logs.stream()
                .map(l -> String.format("%d,%s,%s", 
                        l.getId(), l.getUserId() != null ? l.getUserId() : "N/A", l.getAction()))
                .collect(Collectors.joining("\n", "ID,UserID,Action\n", ""));
    }
}