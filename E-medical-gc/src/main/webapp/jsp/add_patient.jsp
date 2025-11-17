<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add/Edit Patient</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>${patient == null ? 'Add Patient' : 'Edit Patient'}</h2>
        <form action="PatientServlet" method="post">
            <input type="hidden" name="action" value="${patient == null ? 'add' : 'update'}">
            <input type="hidden" name="id" value="${patient.id}">
            <div class="form-group">
                <label>Name</label>
                <input type="text" name="name" class="form-control" value="${patient.name}" required>
            </div>
            <div class="form-group">
                <label>Age</label>
                <input type="number" name="age" class="form-control" value="${patient.age}" required>
            </div>
            <div class="form-group">
                <label>Gender</label>
                <select name="gender" class="form-control" required>
                    <option value="MALE" ${patient.gender == 'MALE' ? 'selected' : ''}>Male</option>
                    <option value="FEMALE" ${patient.gender == 'FEMALE' ? 'selected' : ''}>Female</option>
                    <option value="OTHER" ${patient.gender == 'OTHER' ? 'selected' : ''}>Other</option>
                </select>
            </div>
            <div class="form-group">
                <label>Address</label>
                <textarea name="address" class="form-control">${patient.address}</textarea>
            </div>
            <div class="form-group">
                <label>Phone</label>
                <input type="text" name="phone" class="form-control" value="${patient.phone}">
            </div>
            <button type="submit" class="btn btn-primary">Save</button>
            <a href="PatientServlet?action=list" class="btn btn-secondary">Cancel</a>
        </form>
    </div>
</body>
</html>