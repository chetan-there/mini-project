package com.emedical.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.emedical.dao.PrescriptionDAO;
import com.emedical.dao.PrescriptionDAO.Prescription;

public class PrescriptionService {
    private PrescriptionDAO prescriptionDAO = new PrescriptionDAO();

    public void addPrescription(int patientId, int doctorId, LocalDate prescriptionDate, String details) throws SQLException {
        if (patientId <= 0 || doctorId <= 0 || prescriptionDate == null || details == null || details.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid prescription details");
        }
        prescriptionDAO.addPrescription(patientId, doctorId, prescriptionDate, details);
    }

    public List<Prescription> getAllPrescriptions() throws SQLException {
        return prescriptionDAO.getAllPrescriptions();
    }

    public List<Prescription> getPrescriptionsByPatient(int patientId) throws SQLException {
        if (patientId <= 0) {
            throw new IllegalArgumentException("Invalid patient ID");
        }
        return prescriptionDAO.getPrescriptionsByPatient(patientId);
    }

    public Prescription getPrescriptionById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid prescription ID");
        }
        return prescriptionDAO.getPrescriptionById(id);
    }

    public void updatePrescription(int id, LocalDate prescriptionDate, String details) throws SQLException {
        if (id <= 0 || prescriptionDate == null || details == null || details.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid prescription details for update");
        }
        prescriptionDAO.updatePrescription(id, prescriptionDate, details);
    }

    public void deletePrescription(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid prescription ID");
        }
        prescriptionDAO.deletePrescription(id);
    }

    public List<Prescription> getPrescriptionsByDoctor(int doctorId) throws SQLException {
        if (doctorId <= 0) {
            throw new IllegalArgumentException("Invalid doctor ID");
        }
        return prescriptionDAO.getAllPrescriptions().stream()
                .filter(p -> p.getDoctorId() == doctorId)
                .collect(Collectors.toList());
    }

    public List<Prescription> getPrescriptionsAfterDate(LocalDate date) throws SQLException {
        if (date == null) {
            throw new IllegalArgumentException("Invalid date");
        }
        return prescriptionDAO.getAllPrescriptions().stream()
                .filter(p -> p.getPrescriptionDate().isAfter(date))
                .collect(Collectors.toList());
    }
}