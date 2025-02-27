package com.vt.DAO;

import java.util.List;

import com.vt.model.Order;

public interface OrderDAO {
	 int addOrder(Order orderId);
	 Order getOrder(int orderId);
	 void updateOrder(Order orderId);
	 void deleteOrder(int orderId);
	 List<Order> getAllOrderByUser(int userId);
	

}