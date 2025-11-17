<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Prescriptions</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Prescription Management</h2>
        <a href="jsp/add_prescription.jsp" class="btn btn-primary mb-3">Add Prescription</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Patient ID</th>
                    <th>Doctor ID</th>
                    <th>Date</th>
                    <th>Details</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="prescription" items="${prescriptions}">
                    <tr>
                        <td>${prescription.id}</td>
                        <td>${prescription.patientId}</td>
                        <td>${prescription.doctorId}</td>
                        <td>${prescription.prescriptionDate}</td>
                        <td>${prescription.details}</td>
                        <td>
                            <a href="PrescriptionServlet?action=edit&id=${prescription.id}" class="btn btn-sm btn-warning">Edit</a>
                            <form action="PrescriptionServlet" method="post" style="display:inline;">
                                <input type="hidden" name="action" value="delete">
                                <input type="hidden" name="id" value="${prescription.id}">
                                <button type="submit" class="btn btn-sm btn-danger">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="jsp/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
</body>
</html>