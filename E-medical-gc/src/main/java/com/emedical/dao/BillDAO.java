package com.emedical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.emedical.util.DBConnection;

public class BillDAO {
    public static class Bill {
        private int id;
        private int patientId;
        private double amount;
        private LocalDate billDate;
        private String details;
        private boolean paid;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public int getPatientId() { return patientId; }
        public void setPatientId(int patientId) { this.patientId = patientId; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public LocalDate getBillDate() { return billDate; }
        public void setBillDate(LocalDate billDate) { this.billDate = billDate; }
        public String getDetails() { return details; }
        public void setDetails(String details) { this.details = details; }
        public boolean isPaid() { return paid; }
        public void setPaid(boolean paid) { this.paid = paid; }
    }

    public void addBill(Bill bill) throws SQLException {
        String sql = "INSERT INTO bills (patient_id, amount, bill_date, details, paid) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, bill.getPatientId());
            ps.setDouble(2, bill.getAmount());
            ps.setObject(3, bill.getBillDate());
            ps.setString(4, bill.getDetails());
            ps.setBoolean(5, bill.isPaid());
            ps.executeUpdate();
        }
    }

    public List<Bill> getAllBills() throws SQLException {
        String sql = "SELECT * FROM bills";
        List<Bill> bills = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                bills.add(extractBill(rs));
            }
        }
        return bills;
    }

    private Bill extractBill(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setId(rs.getInt("id"));
        bill.setPatientId(rs.getInt("patient_id"));
        bill.setAmount(rs.getDouble("amount"));
        bill.setBillDate(rs.getObject("bill_date", LocalDate.class));
        bill.setDetails(rs.getString("details"));
        bill.setPaid(rs.getBoolean("paid"));
        return bill;
    }
}