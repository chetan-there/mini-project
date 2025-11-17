package com.emedical.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.emedical.util.DBConnection;

public class AuditLogDAO {
    public static class AuditLog {
        private int id;
        private Integer userId;
        private String action;
        private LocalDateTime timestamp;

        public int getId() { return id; }
        public void setId(int id) { this.id = id; }
        public Integer getUserId() { return userId; }
        public void setUserId(Integer userId) { this.userId = userId; }
        public String getAction() { return action; }
        public void setAction(String action) { this.action = action; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    }

    public void logAction(Integer userId, String action) throws SQLException {
        String sql = "INSERT INTO audit_logs (user_id, action) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            if (userId == null) {
                ps.setNull(1, Types.INTEGER);
            } else {
                ps.setInt(1, userId);
            }
            ps.setString(2, action);
            ps.executeUpdate();
        }
    }

    public List<AuditLog> getAllLogs() throws SQLException {
        String sql = "SELECT * FROM audit_logs";
        List<AuditLog> logs = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                logs.add(extractLog(rs));
            }
        }
        return logs;
    }

    private AuditLog extractLog(ResultSet rs) throws SQLException {
        AuditLog log = new AuditLog();
        log.setId(rs.getInt("id"));
        log.setUserId(rs.getObject("user_id", Integer.class));
        log.setAction(rs.getString("action"));
        log.setTimestamp(rs.getObject("timestamp", LocalDateTime.class));
        return log;
    }
}