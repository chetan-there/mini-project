<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Stock Management</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Stock Management</h2>
        <% 
            String role = (String) session.getAttribute("role");
            if (!"ADMIN".equals(role) && !"PHARMACIST".equals(role)) { 
                response.sendRedirect("jsp/dashboard.jsp"); 
            }
        %>
        <% if ("ADMIN".equals(role)) { %>
            <h3>Central Stock</h3>
            <form action="StockServlet" method="post" class="mb-3">
                <input type="hidden" name="action" value="updateCentral">
                <div class="form-group">
                    <label>Medicine</label>
                    <select name="medicineId" class="form-control" required>
                        <c:forEach var="medicine" items="${medicines}">
                            <option value="${medicine.id}">${medicine.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Update Central Stock</button>
            </form>
            <h3>Transfer to Sub-Store</h3>
            <form action="StockServlet" method="post" class="mb-3">
                <input type="hidden" name="action" value="transfer">
                <div class="form-group">
                    <label>Medicine</label>
                    <select name="medicineId" class="form-control" required>
                        <c:forEach var="medicine" items="${medicines}">
                            <option value="${medicine.id}">${medicine.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Transfer</button>
            </form>
        <% } %>
        <% if ("PHARMACIST".equals(role)) { %>
            <h3>Sub-Store Stock</h3>
            <form action="StockServlet" method="post" class="mb-3">
                <input type="hidden" name="action" value="updateSub">
                <div class="form-group">
                    <label>Medicine</label>
                    <select name="medicineId" class="form-control" required>
                        <c:forEach var="medicine" items="${medicines}">
                            <option value="${medicine.id}">${medicine.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group">
                    <label>Quantity</label>
                    <input type="number" name="quantity" class="form-control" required>
                </div>
                <button type="submit" class="btn btn-primary">Update Sub-Store Stock</button>
            </form>
        <% } %>
        <h3>Stock Levels</h3>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>Medicine ID</th>
                    <th>Medicine Name</th>
                    <th>Central Stock</th>
                    <th>Sub-Store Stock</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="medicine" items="${medicines}">
                    <tr>
                        <td>${medicine.id}</td>
                        <td>${medicine.name}</td>
                        <td>${centralStock[medicine.id]}</td>
                        <td>${subStock[medicine.id]}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="jsp/dashboard.jsp" class="btn btn-secondary">Back to Dashboard</a>
    </div>
</body>
</html>