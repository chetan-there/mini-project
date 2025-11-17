<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Reports</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Generate Reports</h2>
        <% 
            String role = (String) session.getAttribute("role");
            if (!"ADMIN".equals(role)) { 
                response.sendRedirect("jsp/dashboard.jsp"); 
            }
        %>
        <div class="list-group">
            <a href="ReportServlet?type=patients" class="list-group-item list-group-item-action">Download Patient Report (CSV)</a>
            <a href="ReportServlet?type=audit" class="list-group-item list-group-item-action">Download Audit Log Report (CSV)</a>
        </div>
        <a href="jsp/dashboard.jsp" class="btn btn-secondary mt-3">Back to Dashboard</a>
    </div>
</body>
</html>