package com.vt.Servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import com.vt.DAO.OrderDAO;
import com.vt.daoimplementation.OrderDAOImpl;
import com.vt.daoimplementation.OrderItemDAOImpl;
import com.vt.model.Cart;
import com.vt.model.CartItem;
import com.vt.model.Order;
import com.vt.model.OrderItem;
import com.vt.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet{
	private OrderDAO orderDAO;
	private 	OrderItemDAOImpl orderItemDAOImpl;
	
	@Override
	public void init() {
		try {
			System.out.println("init method called");
			orderDAO=new OrderDAOImpl();
			 orderItemDAOImpl = new OrderItemDAOImpl();
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("Failed to initialize OrderDAO",e);
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(req.getSession().getAttribute("loggedUser"));
		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		User user=(User) session.getAttribute("loggedUser");
		
		if(cart!=null&&user!=null&&!cart.getItems().isEmpty()) {
			String paymentMethod = req.getParameter("payment");
			String address=req.getParameter("address");
			
			Order order=new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(new Timestamp(new Date().getTime()));
			order.setPaymentMode(paymentMethod);
			order.setStatus("pending");
			
			double totalAmount=0;
			for(CartItem item:cart.getItems().values()) {
				totalAmount +=item.getPrice()*item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			int orderId =orderDAO.addOrder(order);
			System.out.println("orderId---->"+orderId);
			System.out.println("userId---->"+user.getUserId());
			
			
			for(CartItem item:cart.getItems().values()) {
				int itemId = item.getId();
				String name = item.getName();
				double price = item.getPrice();
				int quantity = item.getQuantity();				
				double totalPrice= price*quantity;		
				OrderItem orderItem = new OrderItem(orderId,itemId,quantity,totalPrice);
				
				orderItemDAOImpl.addOrderItem(orderItem);
				}
			
			
			
			session.removeAttribute("cart");
			session.setAttribute("order", order);
			resp.sendRedirect("orderConfirmation.jsp");
					
		}else {
			resp.sendRedirect("cart.jsp");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
