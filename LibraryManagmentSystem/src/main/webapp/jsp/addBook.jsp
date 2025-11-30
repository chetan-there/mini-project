<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Book</title>
    <style>
        body { font-family: Arial; margin: 40px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="number"] { 
            width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; 
        }
        button { background: #28a745; color: white; padding: 12px 30px; 
                 border: none; border-radius: 4px; cursor: pointer; }
        button:hover { background: #218838; }
        .msg { padding: 10px; margin: 10px 0; border-radius: 4px; }
        .success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    </style>
</head>
<body>

<!-- Common Header (copy from dashboard) -->
<div style="background: #f8f9fa; padding: 20px; border-bottom: 1px solid #dee2e6;">
    <h2>📚 Library Management System</h2>
    <a href="dashboard.jsp">Dashboard</a> | 
    <a href="bookController?action=showAddBook">Add Book</a> |
    <a href="logout.jsp">Logout</a>
</div>

<!-- Messages -->
<c:if test="${not empty successMsg}">
    <div class="msg success">${successMsg}</div>
    <c:remove var="successMsg"/>
</c:if>
<c:if test="${not empty errorMsg}">
    <div class="msg error">${errorMsg}</div>
    <c:remove var="errorMsg"/>
</c:if>

<!-- Add Book Form -->
<div style="max-width: 500px; margin: 20px auto;">
    <h2>Add New Book</h2>
    <form action="bookController" method="post">
        <input type="hidden" name="action" value="addBook">
        
        <div class="form-group">
            <label>Book Title *</label>
            <input type="text" name="title" id="title" required>
        </div>
        
        <div class="form-group">
            <label>Author *</label>
            <input type="text" name="author" id="author" required>
        </div>
        
        <div class="form-group">
            <label>ISBN</label>
            <input type="text" name="isbn" id="isbn">
        </div>
        
        <div class="form-group">
            <label>Category</label>
            <input type="text" name="category" id="category">
        </div>
        
        <div class="form-group">
            <label>Publisher</label>
            <input type="text" name="publisher" id="publisher">
        </div>
        
        <div class="form-group">
            <label>Total Copies *</label>
            <input type="number" name="totalCopies" id="totalCopies" min="1" required>
        </div>
        
        <div class="form-group">
            <label>Available Copies *</label>
            <input type="number" name="availableCopies" id="availableCopies" min="0" required>
        </div>
        
        <button type="submit">Add Book</button>
        <a href="dashboard.jsp" style="margin-left: 10px;">Cancel</a>
    </form>
</div>

<!-- Common Sidebar (copy from dashboard) -->
<div style="position: fixed; right: 20px; top: 100px; background: #e9ecef; padding: 20px; width: 200px;">
    <h4>Quick Links</h4>
    <ul style="list-style: none; padding: 0;">
        <li><a href="dashboard.jsp">🏠 Dashboard</a></li>
        <li><a href="bookController?action=showAddBook">➕ Add Book</a></li>
        <li><a href="bookController?action=booksList">📋 View Books</a></li>
    </ul>
</div>

</body>
</html>
