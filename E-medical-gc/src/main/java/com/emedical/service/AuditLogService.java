package com.emedical.service;

import java.sql.SQLException;
import java.util.List;

import com.emedical.dao.AuditLogDAO;
import com.emedical.dao.AuditLogDAO.AuditLog;

public class AuditLogService {
    private AuditLogDAO auditLogDAO = new AuditLogDAO();

    public void logAction(Integer userId, String action) throws SQLException {
        if (action == null || action.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid action description");
        }
        auditLogDAO.logAction(userId, action);
    }

    public List<AuditLog> getAllLogs() throws SQLException {
        return auditLogDAO.getAllLogs();
    }
}