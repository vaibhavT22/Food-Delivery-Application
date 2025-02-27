package com.vt.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.vt.DAO.UserDAO;
import com.vt.model.User;
import com.vt.utility.DBConnection;

public class UserDAOImpl implements UserDAO{

	private static final String INSERT_USER_QUERY="insert into `user` (`name`,`username`,`password`,`email`,`phone`,`address`,`role`,`createDate`,`lastLoginDate`) values(?,?,?,?,?,?,?,?,?)";
	private static final String GET_USER_QUERY = "SELECT * FROM `user` WHERE `user_id`=?";
	private static final String UPDATE_USER_QUERY = "update `user` set `name`= ? `password`= ? `phone`= ? `address`= ? `role` =?";
	private static final String DELETE_USER_QUERY = "delete from user where `userId` = ?";
	private static final String GET_ALL_USERS_QUERY = "select * from `user`";
	
	
	@Override
	public void addUser(User user) {
	
	
//	PreparedStatement preparedStatement=null;
	try(Connection con = DBConnection.getConnection();
			PreparedStatement preparedStatement = con.prepareStatement(INSERT_USER_QUERY)) {
		
		preparedStatement.setString(1,user.getName() );
		preparedStatement.setString(2,user.getUsername() );
		preparedStatement.setString(3, user.getPassword());
		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPhone());
		preparedStatement.setString(6, user.getAddress());
		preparedStatement.setString(7, user.getRole());
		preparedStatement.setTimestamp(8, user.getCreatedDate());
		preparedStatement.setTimestamp(9, user.getLastLoginDate());
		
		
		System.out.println("User added");
		int res= preparedStatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	}

	@Override
	public User validateUser(String email, String password) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public User getUser(int userId) {
		// TODO Auto-generated method stub
		
		
		User user=null;
		
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(GET_USER_QUERY);){
		
		
		prepareStatement.setInt(1, userId);
		ResultSet res = prepareStatement.executeQuery();
		
		 user=extractUser(res);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void updateUser(User user) {
		
	
		;
		try (Connection con = DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(UPDATE_USER_QUERY);){
			
			
			prepareStatement.setString(1, user.getName());
			prepareStatement.setString(2, user.getPassword());
			prepareStatement.setString(3, user.getPhone());
			prepareStatement.setString(4, user.getAddress());
			prepareStatement.setString(5, user.getRole());
			
			prepareStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void deleteUser(int userId) {
		
		
		try(Connection con = DBConnection.getConnection();
				PreparedStatement prepareStatement= con.prepareStatement(DELETE_USER_QUERY);) {
			
			
			prepareStatement.setInt(1, userId);
			prepareStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public List<User> getAllUsers() {
		
		
		
	
	ArrayList<User> usersList = new ArrayList<User>();
	
	try (Connection con =DBConnection.getConnection();
			Statement statement =con.createStatement();
			){ 
		
		
		ResultSet res =statement.executeQuery(GET_ALL_USERS_QUERY);
		while(res.next()) {
			User user=extractUser(res);
			usersList.add(user);
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	return usersList;
	}	
	static User extractUser(ResultSet res) throws SQLException
		{
		
			int userId = res.getInt("user_id");
			String name = res.getString("name");
			String usernamename = res.getString("username");
			String password = res.getString("password");
			String email = res.getString("email");
			String phone = res.getString("phone");
			String address = res.getString("address");
			String role = res.getString("role");
			Timestamp createdDate = res.getTimestamp("createDate");
			Timestamp lastLoginDate = res.getTimestamp("lastLoginDate");
			
			User user=new User( userId,name, usernamename, password, email, phone, address, role);
			return user;
		
			
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


