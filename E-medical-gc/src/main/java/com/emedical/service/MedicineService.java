package com.emedical.service;

import com.emedical.dao.MedicineDAO;
import com.emedical.dao.MedicineDAO.Medicine;
import java.sql.SQLException;
import java.util.List;

public class MedicineService {
    private MedicineDAO medicineDAO = new MedicineDAO();

    public void addMedicine(Medicine medicine) throws SQLException {
        if (medicine.getName() == null || medicine.getPrice() <= 0) {
            throw new IllegalArgumentException("Invalid medicine details");
        }
        medicineDAO.addMedicine(medicine);
    }

    public List<Medicine> getAllMedicines() throws SQLException {
        return medicineDAO.getAllMedicines();
    }

    public Medicine getMedicineById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid medicine ID");
        }
        return medicineDAO.getMedicineById(id);
    }
}