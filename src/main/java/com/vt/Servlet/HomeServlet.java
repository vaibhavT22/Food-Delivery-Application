package com.vt.Servlet;

import java.io.IOException;
import java.util.List;

import com.vt.daoimplementation.RestaurantDAOImpl;
import com.vt.model.Restaurant;
import com.vt.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("Home Servlet called");
//		HttpSession session = req.getSession();
//		User user=(User)session.getAttribute("loggedUser");
//		System.out.println(user.getUserId());
		
		RestaurantDAOImpl restaurantDAO = new RestaurantDAOImpl();
		List<Restaurant> allRestaurants = restaurantDAO.getAllRestaurants();
		
		req.setAttribute("restaurants", allRestaurants);
		
		RequestDispatcher rd = req.getRequestDispatcher("restaurant.jsp");
		
		rd.forward(req, resp);
		
		
	}
}
