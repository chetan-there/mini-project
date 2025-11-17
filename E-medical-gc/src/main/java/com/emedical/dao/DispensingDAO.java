package com.emedical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.emedical.util.DBConnection;

public class DispensingDAO {
    public static class Dispensing {
        private int id;
        private int prescriptionId;
        private int pharmacistId;
        private LocalDate dispenseDate;
        private String medicinesDispensed;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getPrescriptionId() { return prescriptionId; }
        public void setPrescriptionId(int prescriptionId) { this.prescriptionId = prescriptionId; }
        public int getPharmacistId() { return pharmacistId; }
        public void setPharmacistId(int pharmacistId) { this.pharmacistId = pharmacistId; }
        public LocalDate getDispenseDate() { return dispenseDate; }
        public void setDispenseDate(LocalDate dispenseDate) { this.dispenseDate = dispenseDate; }
        public String getMedicinesDispensed() { return medicinesDispensed; }
        public void setMedicinesDispensed(String medicinesDispensed) { this.medicinesDispensed = medicinesDispensed; }
    }

    public void addDispensing(Dispensing dispensing) throws SQLException {
        String sql = "INSERT INTO dispensing (prescription_id, pharmacist_id, dispense_date, medicines_dispensed) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, dispensing.getPrescriptionId());
            ps.setInt(2, dispensing.getPharmacistId());
            ps.setObject(3, dispensing.getDispenseDate());
            ps.setString(4, dispensing.getMedicinesDispensed());
            ps.executeUpdate();
        }
    }

    public List<Dispensing> getAllDispensing() throws SQLException {
        String sql = "SELECT * FROM dispensing";
        List<Dispensing> dispensings = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                dispensings.add(extractDispensing(rs));
            }
        }
        return dispensings;
    }

    private Dispensing extractDispensing(ResultSet rs) throws SQLException {
        Dispensing dispensing = new Dispensing();
        dispensing.setId(rs.getInt("id"));
        dispensing.setPrescriptionId(rs.getInt("prescription_id"));
        dispensing.setPharmacistId(rs.getInt("pharmacist_id"));
        dispensing.setDispenseDate(rs.getObject("dispense_date", LocalDate.class));
        dispensing.setMedicinesDispensed(rs.getString("medicines_dispensed"));
        return dispensing;
    }
}