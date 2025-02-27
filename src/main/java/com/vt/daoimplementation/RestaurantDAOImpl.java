package com.vt.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vt.DAO.RestaurantDAO;
import com.vt.model.Restaurant;
//import com.vt.model.User;
import com.vt.utility.DBConnection;

public class RestaurantDAOImpl implements RestaurantDAO {
	private static final String INSERT_RESTAURANT_QUERY="INSERT INTO `restaurant`(`name`,`address`,`phone`,`cuisineType`,`rating`,`isActive`) VALUES(?,?,?,?,?,?)";
	private static final String GET_RESTAURANT_QUERY="SELECT * FROM `restaurant` WHERE `restaurantId` = ?";
	private static final String UPDATE_RESTAURANT_QUERY="UPDATE `restaurant` SET `name` = ?, `address` = ?, `phone` = ?, `rating` = ?, `isActive` = ? WHERE `restaurantId` = ?; ";
	private static final String DELETE_RESTAURANT_QUERY=" DELETE  FROM `restaurant` WHERE `restaurantId`=?";
	private static final String GET_ALL_RESTAURANT_QUERY="SELECT * FROM `restaurant`";
	@Override
	public void addRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement =con.prepareStatement(INSERT_RESTAURANT_QUERY);) {
			
			prepareStatement.setString(1,restaurant.getName());
			prepareStatement.setString(2,restaurant.getAddress());
			prepareStatement.setString(3,restaurant.getPhone());
			prepareStatement.setString(4,restaurant.getCuisineType());
			prepareStatement.setDouble(5,restaurant.getRating());
			prepareStatement.setBoolean(6,restaurant.isActive());
			int res =prepareStatement.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant getRestaurant(int restaurantId) {
		// TODO Auto-generated method stub
		
		Restaurant restaurant = null;
		
		
		

		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement =con.prepareStatement(GET_RESTAURANT_QUERY);) {
			
			prepareStatement.setInt(1, restaurantId);
			ResultSet res= prepareStatement.executeQuery();
				
			 restaurant=extractRestaurant(res);
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
		
		
		return restaurant;
	}

	@Override
	public void updateRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub

		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement =con.prepareStatement(UPDATE_RESTAURANT_QUERY)) {
			
//			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(1, restaurant.getName());
			prepareStatement.setString(2, restaurant.getAddress());
			prepareStatement.setString(3, restaurant.getPhone());
			prepareStatement.setString(4, restaurant.getCuisineType());
			prepareStatement.setDouble(5, restaurant.getRating());
			prepareStatement.setBoolean(6, restaurant.isActive());
			prepareStatement.setInt(7, restaurant.getRestaurantId());
			prepareStatement.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void deleteRestaurant(int restaurantId) {
		// TODO Auto-generated method stub

		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement =con.prepareStatement(DELETE_RESTAURANT_QUERY)) {
			
			prepareStatement.setInt(1, restaurantId);
			prepareStatement.execute();
			
			
			
			
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
	}

	@Override
	public List<Restaurant> getAllRestaurants() {
		// TODO Auto-generated method stub
		
		ArrayList<Restaurant> restaurantsList = new ArrayList<Restaurant>();
		try(Connection con=DBConnection.getConnection();
				Statement statement =con.createStatement()) {
			
			
			ResultSet res=statement.executeQuery(GET_ALL_RESTAURANT_QUERY);
			
			while(res.next()) {
				Restaurant restaurant=extractRestaurant(res);
				restaurantsList.add(restaurant);
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}
		
		return restaurantsList;
	}
	static Restaurant extractRestaurant(ResultSet res)throws SQLException{
		int restaurantId = res.getInt("restaurantId");
		String name= res.getString("name");
		String address= res.getString("address");
		String phone= res.getString("phone");
		double ratings= res.getDouble("rating");
		String cuisineType= res.getString("cuisineType");
		boolean isActive= res.getBoolean("isActive");
		int eta=res.getInt("eta");
		int adminUserId=res.getInt("adminUserId");
		String imagePath=res.getString("imagePath");
		Restaurant restaurant = new Restaurant(
			    restaurantId, name, address, phone, ratings, cuisineType, isActive, eta, adminUserId, imagePath
			);
		return restaurant;

//		
	}

}
