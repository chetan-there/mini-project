package com.emedical.servlet;

import java.io.IOException;
import java.sql.SQLException;

import com.emedical.service.AuditLogService;
import com.emedical.service.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserService();
    private AuditLogService auditService = new AuditLogService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("==== Inside LoginServlet ====");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("Username: " + username);

        try {
            // ---- VALID LOGIN ----
            if (userService.login(username, password)) {

                System.out.println("Login Successful!");

                HttpSession session = request.getSession();
                session.setAttribute("username", username);
                session.setAttribute("role", userService.getRole(username));

                auditService.logAction(userService.getUserId(username), "Login successful");

                // Proper path redirect
                response.sendRedirect(request.getContextPath() + "/jsp/dashboard.jsp");
                return;
            }

            // ---- INVALID LOGIN ----
            System.out.println("Login Failed!");

            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(500);
        }
    }
}
