package com.emedical.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.emedical.dao.BillDAO;
import com.emedical.dao.BillDAO.Bill;

public class BillService {
    private BillDAO billDAO = new BillDAO();

    public void addBill(int patientId, double amount, LocalDate billDate, String details) throws SQLException {
        if (patientId <= 0 || amount <= 0 || billDate == null) {
            throw new IllegalArgumentException("Invalid bill details");
        }
        Bill bill = new Bill();
        bill.setPatientId(patientId);
        bill.setAmount(amount);
        bill.setBillDate(billDate);
        bill.setDetails(details);
        bill.setPaid(false);
        billDAO.addBill(bill);
    }

    public List<Bill> getAllBills() throws SQLException {
        return billDAO.getAllBills();
    }
}