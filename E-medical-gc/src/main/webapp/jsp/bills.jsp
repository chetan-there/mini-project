<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Billing</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Billing Management</h2>
        <% 
            String role = (String) session.getAttribute("role");
            if (!"PHARMACIST".equals(role)) { 
                response.sendRedirect("jsp/dashboard.jsp"); 
            }
        %>
        <form action="BillServlet" method="post" class="mb-3">
            <div class="form-group">
                <label>Patient ID</label>
                <input type="number" name="patientId" class="form-control" required>
            </div>
            <div class="form-group">
                <label>Amount</label>
                <input type="number" name="amount" class="form-control" step="0.01" required>
            </div>
            <div class="form-group">
                <label>Bill Date</label>
                <input type="date" name="billDate" class="form-control" required>
            </div>
            <div class="form-group">
                <label>Details</label>
                <textarea name="details" class="form-control" placeholder="e.g., Paracetamol: $5.00"></textarea>
            </div>
            <button type="submit" class="btn btn-primary">Add Bill</button>
            <a href="jsp/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
        </form>
        <h3>Bills</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Patient ID</th>
                    <th>Amount</th>
                    <th>Bill Date</th>
                    <th>Details</th>
                    <th>Paid</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bill" items="${bills}">
                    <tr>
                        <td>${bill.id}</td>
                        <td>${bill.patientId}</td>
                        <td>${bill.amount}</td>
                        <td>${bill.billDate}</td>
                        <td>${bill.details}</td>
                        <td>${bill.paid ? 'Yes' : 'No'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>