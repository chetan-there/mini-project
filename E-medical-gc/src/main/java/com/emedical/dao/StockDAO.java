package com.emedical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.emedical.util.DBConnection;

public class StockDAO {
    public void updateCentralStock(int medicineId, int quantity) throws SQLException {
        String sql = "INSERT INTO central_stock (medicine_id, quantity) VALUES (?, ?) ON DUPLICATE KEY UPDATE quantity = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            ps.setInt(2, quantity);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }

    public void updateSubStock(int medicineId, int quantity) throws SQLException {
        String sql = "INSERT INTO sub_stock (medicine_id, quantity) VALUES (?, ?) ON DUPLICATE KEY UPDATE quantity = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            ps.setInt(2, quantity);
            ps.setInt(3, quantity);
            ps.executeUpdate();
        }
    }

    public int getCentralStock(int medicineId) throws SQLException {
        String sql = "SELECT quantity FROM central_stock WHERE medicine_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("quantity") : 0;
            }
        }
    }

    public int getSubStock(int medicineId) throws SQLException {
        String sql = "SELECT quantity FROM sub_stock WHERE medicine_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, medicineId);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getInt("quantity") : 0;
            }
        }
    }
}