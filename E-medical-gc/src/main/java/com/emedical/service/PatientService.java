package com.emedical.service;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.emedical.dao.PatientDAO;
import com.emedical.dao.PatientDAO.Patient;

public class PatientService {
    private PatientDAO patientDAO = new PatientDAO();

    public void addPatient(Patient patient) throws SQLException {
        if (patient.getName() == null || patient.getAge() <= 0 || patient.getGender() == null) {
            throw new IllegalArgumentException("Invalid patient details");
        }
        patientDAO.addPatient(patient);
    }

    public List<Patient> getAllPatients() throws SQLException {
        return patientDAO.getAllPatients();
    }

    public Patient getPatientById(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid patient ID");
        }
        return patientDAO.getPatientById(id);
    }

    public void updatePatient(Patient patient) throws SQLException {
        if (patient.getId() <= 0 || patient.getName() == null || patient.getAge() <= 0 || patient.getGender() == null) {
            throw new IllegalArgumentException("Invalid patient details for update");
        }
        patientDAO.updatePatient(patient);
    }

    public void deletePatient(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid patient ID");
        }
        patientDAO.deletePatient(id);
    }

    public List<Patient> getPatientsByGender(String gender) throws SQLException {
        return patientDAO.getAllPatients().stream()
                .filter(p -> p.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());
    }
}