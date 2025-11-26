package com.lms.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.lms.dao.UserDao;
import com.lms.pojo.User;
import com.lms.util.DbUtil;

public class UserDaoImpl implements UserDao {

	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement preparedStatement = null;

	@Override
	public User checkLogin(String username, String password) {
		ResultSet rs = null;
		Connection conn= null;
		PreparedStatement preparedStatement =null ;
		
		
		try {
			String sql ="SELECT * FROM USERS WHERE EMAIL =? AND PASSWORD =?";
			
			conn= DbUtil.getConnection();		
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				User user  = new User();
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhoneNo(rs.getString("phone_no"));
				
				return user;
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			
				try {
					if (rs != null) {
					rs.close();
					}
					if (conn != null) {
						conn.close();
					}
					if (preparedStatement != null) {
						preparedStatement.close();
					}
										
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		return null;
	}

}
