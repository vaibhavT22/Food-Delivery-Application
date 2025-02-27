package com.vt.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.vt.DAO.OrderDAO;
import com.vt.model.Order;
import com.vt.utility.DBConnection;

public class OrderDAOImpl implements OrderDAO{
	private static final String INSERT_ORDER_QUERY="insert into `order` (`userId`,`restaurantId`,`orderDate`,`totalAmount`,`status`,`paymentMode`) values(?,?,?,?,?,?)";
	private static final String GET_ORDER_QUERY="select * from `order` where `orderId` = ? ";
	private static final String UPDATE_ORDER_QUERY="UPDATE `order` SET `totalAmount`=?, `status`=?, `paymentMode`=? WHERE `orderId`=?";
	private static final String DELETE_ORDER_QUERY="delete from `order` where `orderId` = ? ";
	private static final String GET_ALL_ORDER_QUERY="select * from `order` where `userId` = ?";
	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		int orderId=0;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(INSERT_ORDER_QUERY,Statement.RETURN_GENERATED_KEYS)){
			prepareStatement.setInt(1, order.getUserId());
			prepareStatement.setInt(2, order.getRestaurantId());
			prepareStatement.setTimestamp(3,order.getOrderDate());
			prepareStatement.setDouble(4, order.getTotalAmount());
			prepareStatement.setString(5, order.getStatus());
		prepareStatement.setString(6, order.getPaymentMode());
			int affectedRows=prepareStatement.executeUpdate();
			if(affectedRows==0) {
				throw new SQLException("adding order failed , no rows affected");
			}
			System.out.println("Order added");
			
			ResultSet res = prepareStatement.getGeneratedKeys();
			while(res.next()) {
				orderId = res.getInt(1);
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return orderId;
		
	}

	@Override
	public Order getOrder(int orderId) {
		// TODO Auto-generated method stub
		Order order=null;
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(GET_ORDER_QUERY);){
			
			prepareStatement.setInt(1, orderId);
			ResultSet res=prepareStatement.executeQuery();
			order=extractOrder(res);
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(UPDATE_ORDER_QUERY);){
			prepareStatement.setDouble(1, order.getTotalAmount());
            prepareStatement.setString(2, order.getStatus());
            prepareStatement.setString(3, order.getPaymentMode());
            prepareStatement.setInt(4, order.getOrderId());
            prepareStatement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteOrder(int orderId) {
		// TODO Auto-generated method stub
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(DELETE_ORDER_QUERY);){
			prepareStatement.setInt(1, orderId);
			prepareStatement.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Order> getAllOrderByUser(int userId) {
		// TODO Auto-generated method stub
		ArrayList<Order> ordersList =new ArrayList<Order>();
		try(Connection con=DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(GET_ALL_ORDER_QUERY);){
			prepareStatement.setInt(1, userId);
			ResultSet res= prepareStatement.executeQuery();
			while(res.next()) {
				Order order=extractOrder(res);
				ordersList.add(order);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	static Order extractOrder(ResultSet res)  throws SQLException{
		// TODO Auto-generated method stub
		int orderId = res.getInt("orderId");
        int userId = res.getInt("userId"); 
        int restaurantId = res.getInt("restaurantId");
        Timestamp orderDate = res.getTimestamp("orderDate"); 
        int totalAmount = res.getInt("totalAmount");
        String status = res.getString("status");
        String paymentMode = res.getString("paymentMode");
		 
		 Order order=new Order(orderId, userId, restaurantId,orderDate, totalAmount, status, paymentMode);
		return order;
	}

}
