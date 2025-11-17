package com.emedical.service;

import java.sql.SQLException;

import com.emedical.dao.UserDAO;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean login(String username, String password) throws SQLException {
        return userDAO.validateUser(username, password);
    }

    public String getRole(String username) throws SQLException {
        return userDAO.getUserRole(username);
    }

    public int getUserId(String username) throws SQLException {
        return userDAO.getUserId(username);
    }
}