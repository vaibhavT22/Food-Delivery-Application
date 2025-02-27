package com.vt.Servlet;

import java.io.IOException;


import com.vt.daoimplementation.MenuDAOImpl;
import com.vt.model.Cart;
import com.vt.model.CartItem;
import com.vt.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Cart cart = (Cart)session.getAttribute("cart");
		System.out.println(req.getSession().getAttribute("loggedUser"));
		int newRestaurantId = Integer.parseInt(req.getParameter("restaurantId")) ;
		Integer currentRestaurantId= (Integer)session.getAttribute("restaurantId");
		
		if(cart==null || currentRestaurantId!=newRestaurantId) {
			cart = new Cart();
			session.setAttribute("cart", cart);
			session.setAttribute("restaurantId", newRestaurantId);
		}
		
		String action = req.getParameter("action");
		try {
			
			if("add".equals(action)) {
				System.out.println("add succesfull");
				addItemToCart(req,cart);
			}
			else if("update".equals(action)) {
				System.out.println("update succesfull");
				updateItemToCart(req,cart);
			}
			else if("remove".equals(action)) {
				System.out.println("remove succesfull");
				removeItemToCart(req,cart);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("cart.jsp");
	}

	private void addItemToCart(HttpServletRequest req, Cart cart) {
		int itemId =Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		
		MenuDAOImpl menuDAO = new MenuDAOImpl();
		Menu menuItem = menuDAO.getMenu(itemId);
		
		if(menuItem!=null) {
			
			CartItem item = new CartItem(menuItem.getMenuId(),
								menuItem.getRestaurantId(),
								menuItem.getItemName(),
								quantity,
								menuItem.getPrice());
			
			cart.addCartItem(item);
		}
		
	}
	
	private void updateItemToCart(HttpServletRequest request,Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		cart.updateCartItem(itemId, quantity);
	}
	
	private void removeItemToCart(HttpServletRequest request,Cart cart) {
		int itemId = Integer.parseInt(request.getParameter("itemId"));
		cart.removeCartItem(itemId);
	}

}