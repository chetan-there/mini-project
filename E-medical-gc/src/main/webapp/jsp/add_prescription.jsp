<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add/Edit Prescription</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>${prescription == null ? 'Add Prescription' : 'Edit Prescription'}</h2>
        <form action="PrescriptionServlet" method="post">
            <input type="hidden" name="action" value="${prescription == null ? 'add' : 'update'}">
            <input type="hidden" name="id" value="${prescription.id}">
            <div class="form-group">
                <label>Patient ID</label>
                <input type="number" name="patientId" class="form-control" value="${prescription.patientId}" required>
            </div>
            <div class="form-group">
                <label>Prescription Date</label>
                <input type="date" name="prescriptionDate" class="form-control" value="${prescription.prescriptionDate}" required>
            </div>
            <div class="form-group">
                <label>Details</label>
                <textarea name="details" class="form-control" required>${prescription.details}</textarea>
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="PrescriptionServlet?action=list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>