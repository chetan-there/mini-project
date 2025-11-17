<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% 
    String role = (String) session.getAttribute("role");
    if (role == null) { response.sendRedirect("login.jsp"); }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= session.getAttribute("username") %> (<%= role %>)</h2>
        <nav>
            <ul class="nav nav-pills">
                <% if ("ADMIN".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="PatientServlet?action=list">Patient Management</a></li>
                    <li class="nav-item"><a class="nav-link" href="StockServlet">Stock Management</a></li>
                    <li class="nav-item"><a class="nav-link" href="ReportServlet">Reports</a></li>
                    <li class="nav-item"><a class="nav-link" href="AuditLogServlet">Audit Logs</a></li>
                <% } else if ("DOCTOR".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="PatientServlet?action=list">Patients</a></li>
                    <li class="nav-item"><a class="nav-link" href="PrescriptionServlet?action=list">Prescriptions</a></li>
                <% } else if ("PHARMACIST".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="DispensingServlet">Dispensing</a></li>
                    <li class="nav-item"><a class="nav-link" href="StockServlet">Sub-Store Stock</a></li>
                    <li class="nav-item"><a class="nav-link" href="BillServlet">Billing</a></li>
                <% } %>
                <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
            </ul>
        </nav>
    </div>
</body>
</html>