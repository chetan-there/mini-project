<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Session Validation
    String role = (String) session.getAttribute("role");
    if (role == null) {
        // Redirect to actual login page (index.jsp)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-4">
        <h2>Welcome, <%= session.getAttribute("username") %> (<%= role %>)</h2>

        <nav class="mt-3">
            <ul class="nav nav-pills">

                <% if ("ADMIN".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/PatientServlet?action=list">Patient Management</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/StockServlet">Stock Management</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/ReportServlet">Reports</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/AuditLogServlet">Audit Logs</a></li>

                <% } else if ("DOCTOR".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/PatientServlet?action=list">Patients</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/PrescriptionServlet?action=list">Prescriptions</a></li>

                <% } else if ("PHARMACIST".equals(role)) { %>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/DispensingServlet">Dispensing</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/StockServlet">Sub-Store Stock</a></li>
                    <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/BillServlet">Billing</a></li>

                <% } %>

                <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/LogoutServlet">Logout</a></li>

            </ul>
        </nav>
    </div>
</body>
</html>
