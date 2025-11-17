package com.emedical.service;

import java.sql.SQLException;

import com.emedical.dao.StockDAO;

public class StockService {
    private StockDAO stockDAO = new StockDAO();

    public void updateCentralStock(int medicineId, int quantity) throws SQLException {
        if (medicineId <= 0 || quantity < 0) {
            throw new IllegalArgumentException("Invalid stock details");
        }
        stockDAO.updateCentralStock(medicineId, quantity);
    }

    public void updateSubStock(int medicineId, int quantity) throws SQLException {
        if (medicineId <= 0 || quantity < 0) {
            throw new IllegalArgumentException("Invalid stock details");
        }
        stockDAO.updateSubStock(medicineId, quantity);
    }

    public void transferStock(int medicineId, int quantity) throws SQLException {
        if (medicineId <= 0 || quantity <= 0) {
            throw new IllegalArgumentException("Invalid transfer details");
        }
        int centralStock = stockDAO.getCentralStock(medicineId);
        if (centralStock < quantity) {
            throw new SQLException("Insufficient central stock");
        }
        stockDAO.updateCentralStock(medicineId, centralStock - quantity);
        int subStock = stockDAO.getSubStock(medicineId);
        stockDAO.updateSubStock(medicineId, subStock + quantity);
    }

    public int getCentralStock(int medicineId) throws SQLException {
        if (medicineId <= 0) {
            throw new IllegalArgumentException("Invalid medicine ID");
        }
        return stockDAO.getCentralStock(medicineId);
    }

    public int getSubStock(int medicineId) throws SQLException {
        if (medicineId <= 0) {
            throw new IllegalArgumentException("Invalid medicine ID");
        }
        return stockDAO.getSubStock(medicineId);
    }
}