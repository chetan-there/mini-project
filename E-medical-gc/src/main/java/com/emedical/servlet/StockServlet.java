package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.emedical.service.AuditLogService;
import com.emedical.service.MedicineService;
import com.emedical.service.StockService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StockServlet extends HttpServlet {
    private StockService stockService = new StockService();
    private MedicineService medicineService = new MedicineService();
    private AuditLogService auditService = new AuditLogService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Set medicine list and stock levels for JSP
            request.setAttribute("medicines", medicineService.getAllMedicines());
            Map<Integer, Integer> centralStock = new HashMap<>();
            Map<Integer, Integer> subStock = new HashMap<>();
            for (var medicine : medicineService.getAllMedicines()) {
                centralStock.put(medicine.getId(), stockService.getCentralStock(medicine.getId()));
                subStock.put(medicine.getId(), stockService.getSubStock(medicine.getId()));
            }
            request.setAttribute("centralStock", centralStock);
            request.setAttribute("subStock", subStock);
            request.getRequestDispatcher("jsp/stock.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            int medicineId = Integer.parseInt(request.getParameter("medicineId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            if ("updateCentral".equals(action)) {
                stockService.updateCentralStock(medicineId, quantity);
                auditService.logAction(null, "Updated central stock for medicine ID: " + medicineId);
            } else if ("updateSub".equals(action)) {
                stockService.updateSubStock(medicineId, quantity);
                auditService.logAction(null, "Updated sub-store stock for medicine ID: " + medicineId);
            } else if ("transfer".equals(action)) {
                stockService.transferStock(medicineId, quantity);
                auditService.logAction(null, "Transferred stock for medicine ID: " + medicineId);
            }
            response.sendRedirect("StockServlet");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}