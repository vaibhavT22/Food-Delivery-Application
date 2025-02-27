package com.vt.DAO;

import java.util.List;

import com.vt.model.OrderItem;

public interface OrderItemDAO {
	 void addOrderItem(OrderItem orderItem);
	OrderItem getOrderItem(int orderItemId);
	void updateOrderItem(OrderItem orderItem);
	void deleteOrderItem(int orderItemId);
	List<OrderItem> getAllOrder();
}
