package com.emedical.service;

import com.emedical.dao.DispensingDAO;
import com.emedical.dao.DispensingDAO.Dispensing;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class DispensingService {
    private DispensingDAO dispensingDAO = new DispensingDAO();

    public void addDispensing(Dispensing dispensing) throws SQLException {
        if (dispensing.getPrescriptionId() <= 0 || dispensing.getPharmacistId() <= 0 || 
            dispensing.getDispenseDate() == null || dispensing.getMedicinesDispensed() == null) {
            throw new IllegalArgumentException("Invalid dispensing details");
        }
        dispensingDAO.addDispensing(dispensing);
    }

    public List<Dispensing> getAllDispensing() throws SQLException {
        return dispensingDAO.getAllDispensing();
    }
}