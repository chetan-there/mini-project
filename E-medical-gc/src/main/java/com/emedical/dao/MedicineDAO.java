package com.emedical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.emedical.util.DBConnection;

public class MedicineDAO {
    public static class Medicine {
        private int id;
        private String name;
        private String description;
        private double price;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public double getPrice() { return price; }
        public void setPrice(double price) { this.price = price; }
    }

    public void addMedicine(Medicine medicine) throws SQLException {
        String sql = "INSERT INTO medicines (name, description, price) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, medicine.getName());
            ps.setString(2, medicine.getDescription());
            ps.setDouble(3, medicine.getPrice());
            ps.executeUpdate();
        }
    }

    public List<Medicine> getAllMedicines() throws SQLException {
        String sql = "SELECT * FROM medicines";
        List<Medicine> medicines = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                medicines.add(extractMedicine(rs));
            }
        }
        return medicines;
    }

    public Medicine getMedicineById(int id) throws SQLException {
        String sql = "SELECT * FROM medicines WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? extractMedicine(rs) : null;
            }
        }
    }

    private Medicine extractMedicine(ResultSet rs) throws SQLException {
        Medicine medicine = new Medicine();
        medicine.setId(rs.getInt("id"));
        medicine.setName(rs.getString("name"));
        medicine.setDescription(rs.getString("description"));
        medicine.setPrice(rs.getDouble("price"));
        return medicine;
    }
}