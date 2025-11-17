package com.emedical.dao;

import com.emedical.util.DBConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    public void addPrescription(int patientId, int doctorId, LocalDate prescriptionDate, String details) throws SQLException {
        String sql = "INSERT INTO prescriptions (patient_id, doctor_id, prescription_date, details) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setObject(3, prescriptionDate);
            ps.setString(4, details);
            ps.executeUpdate();
        }
    }

    public List<Prescription> getAllPrescriptions() throws SQLException {
        String sql = "SELECT * FROM prescriptions";
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                prescriptions.add(extractPrescription(rs));
            }
        }
        return prescriptions;
    }

    public List<Prescription> getPrescriptionsByPatient(int patientId) throws SQLException {
        String sql = "SELECT * FROM prescriptions WHERE patient_id = ?";
        List<Prescription> prescriptions = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    prescriptions.add(extractPrescription(rs));
                }
            }
        }
        return prescriptions;
    }

    public Prescription getPrescriptionById(int id) throws SQLException {
        String sql = "SELECT * FROM prescriptions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return extractPrescription(rs);
                }
                return null;
            }
        }
    }

    public void updatePrescription(int id, LocalDate prescriptionDate, String details) throws SQLException {
        String sql = "UPDATE prescriptions SET prescription_date = ?, details = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, prescriptionDate);
            ps.setString(2, details);
            ps.setInt(3, id);
            ps.executeUpdate();
        }
    }

    public void deletePrescription(int id) throws SQLException {
        String sql = "DELETE FROM prescriptions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Prescription extractPrescription(ResultSet rs) throws SQLException {
        Prescription prescription = new Prescription();
        prescription.setId(rs.getInt("id"));
        prescription.setPatientId(rs.getInt("patient_id"));
        prescription.setDoctorId(rs.getInt("doctor_id"));
        prescription.setPrescriptionDate(rs.getObject("prescription_date", LocalDate.class));
        prescription.setDetails(rs.getString("details"));
        return prescription;
    }

    public static class Prescription {
        private int id;
        private int patientId;
        private int doctorId;
        private LocalDate prescriptionDate;
        private String details;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getPatientId() { return patientId; }
        public void setPatientId(int patientId) { this.patientId = patientId; }
        public int getDoctorId() { return doctorId; }
        public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
        public LocalDate getPrescriptionDate() { return prescriptionDate; }
        public void setPrescriptionDate(LocalDate prescriptionDate) { this.prescriptionDate = prescriptionDate; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
    }
}