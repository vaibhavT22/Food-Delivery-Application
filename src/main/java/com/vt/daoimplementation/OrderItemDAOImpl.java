package com.vt.daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.vt.DAO.OrderItemDAO;
import com.vt.model.OrderItem;
import com.vt.utility.DBConnection;

public class OrderItemDAOImpl implements OrderItemDAO {
	private static final String INSERT_ORDERITEM_QUERY="insert into `orderitem`(`orderId`,`menuId`,`quantity`,`totalPrice`)values(?,?,?,?);";
	private static final String GET_ORDERITEM_QUERY="select * from `orderitem` where `orderItemId` =? ;";
	private static final String UPDATE_ORDERITEM_QUERY="update `orderitem` set `quantity` = ? `totalPrice` =? ;";
	private static final String DELETE_ORDERITEM_QUERY="delete from `orderitem` where `orderItemId`=?;";
	private static final String GET_ALL_ORDERITEM_QUERY="select * from `orderitem`;";

	@Override
	public void addOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(INSERT_ORDERITEM_QUERY);){
				prepareStatement.setInt(1, orderItem.getOrderId());
				prepareStatement.setInt(2, orderItem.getMenuId());
				prepareStatement.setInt(3, orderItem.getQuantity());
				prepareStatement.setDouble(4, orderItem.getTotalPrice());
				int res=prepareStatement.executeUpdate();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
	}

	@Override
	public OrderItem getOrderItem(int orderItemId) {
		// TODO Auto-generated method stub
		OrderItem orderItem=null;
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(GET_ORDERITEM_QUERY);){
			prepareStatement.setInt(1, orderItemId);
			ResultSet res=prepareStatement.executeQuery();
			orderItem=extractOrderItem(res);
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		return orderItem;
	}



	@Override
	public void updateOrderItem(OrderItem orderItem) {
		// TODO Auto-generated method stub
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(UPDATE_ORDERITEM_QUERY);){
			prepareStatement.setInt(1, orderItem.getQuantity());
			prepareStatement.setDouble(2, orderItem.getTotalPrice());
			prepareStatement.executeUpdate();
			
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		
	}

	@Override
	public void deleteOrderItem(int orderItemId) {
		// TODO Auto-generated method stub
		
		try(Connection con= DBConnection.getConnection();
				PreparedStatement prepareStatement = con.prepareStatement(DELETE_ORDERITEM_QUERY);){
			prepareStatement.setInt(1, orderItemId);
			prepareStatement.execute();
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		
	}

	@Override
	public List<OrderItem> getAllOrder() {
		// TODO Auto-generated method stub
		ArrayList<OrderItem> orderItemsList =new ArrayList<OrderItem>();
		try(Connection con= DBConnection.getConnection();
				Statement statement = con.createStatement();){
			ResultSet res= statement.executeQuery(GET_ALL_ORDERITEM_QUERY);
			
			while(res.next()) {
				OrderItem orderItem = extractOrderItem(res);
				orderItemsList.add(orderItem);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
			
		}
		
		
		return orderItemsList;
	}
	
	static OrderItem extractOrderItem(ResultSet res) throws SQLException {
		// TODO Auto-generated method stub
		int orderItemId = res.getInt("orderItemId");
		int orderId=res.getInt("orderId");
		int menuId=res.getInt("menuId");
		int quantity= res.getInt("quantity");
		double totalPrice= res.getDouble("totalPrice");
		
		OrderItem orderItem = new OrderItem( orderItemId,orderId, menuId, quantity, totalPrice);
		
		return orderItem;
	}

}
