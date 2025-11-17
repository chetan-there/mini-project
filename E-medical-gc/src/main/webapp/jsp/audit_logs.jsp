<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Audit Logs</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Audit Logs</h2>
        <% 
            String role = (String) session.getAttribute("role");
            if (!"ADMIN".equals(role)) { 
                response.sendRedirect("jsp/dashboard.jsp"); 
            }
        %>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Action</th>
                    <th>Timestamp</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="log" items="${logs}">
                    <tr>
                        <td>${log.id}</td>
                        <td>${log.userId != null ? log.userId : 'N/A'}</td>
                        <td>${log.action}</td>
                        <td>${log.timestamp}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="jsp/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
</body>
</html>