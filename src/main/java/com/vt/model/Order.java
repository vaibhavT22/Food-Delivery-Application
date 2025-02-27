package com.vt.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private int orderId;
	private int userId;

	private int restaurantId;
	private Timestamp orderDate;
	private double totalAmount;
	private String status;
	private String paymentMode;
	private List<OrderItem> orderItems;
	
	public Order() {
		this.orderDate=new Timestamp(System.currentTimeMillis());
		this.orderItems=new ArrayList<>();
	}

	public Order(int orderId, int userId, int restaurantId,Timestamp orderDate, double totalAmount, String status, String paymentMode) {
		this.orderId = orderId;
		this.userId = userId;
		this.restaurantId=restaurantId;
		this.orderDate = orderDate;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	
	
	
}
