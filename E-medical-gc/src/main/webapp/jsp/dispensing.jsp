<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Dispensing</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.role ne 'PHARMACIST'}">
            <c:redirect url="jsp/dashboard.jsp" />
        </c:when>
        <c:otherwise>
            <div class="container">
                <h2>Dispensing Management</h2>
                <form action="DispensingServlet" method="post" class="mb-3">
                    <div class="form-group">
                        <label>Prescription ID</label>
                        <input type="number" name="prescriptionId" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Dispense Date</label>
                        <input type="date" name="dispenseDate" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label>Medicines Dispensed</label>
                        <textarea name="medicinesDispensed" class="form-control" required placeholder="e.g., Paracetamol: 10 tablets"></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Dispense</button>
                    <a href="jsp/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
                </form>

                <h3>Dispensing Records</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Prescription ID</th>
                            <th>Pharmacist ID</th>
                            <th>Dispense Date</th>
                            <th>Medicines Dispensed</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dispensing" items="${dispensings}">
                            <tr>
                                <td>${dispensing.id}</td>
                                <td>${dispensing.prescriptionId}</td>
                                <td>${dispensing.pharmacistId}</td>
                                <td>${dispensing.dispenseDate}</td>
                                <td>${dispensing.medicinesDispensed}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>