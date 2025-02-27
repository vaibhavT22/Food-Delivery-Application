package com.vt.Servlet;

import java.io.IOException;

import com.vt.daoimplementation.UserDAOImpl;
import com.vt.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username=req.getParameter("username");
		String name=req.getParameter("name");
		String role=req.getParameter("role");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		String address=req.getParameter("address");
		
		

		User user = new User();
		user.setUsername(username);
		user.setName(name);
		user.setRole(role);
		user.setEmail(email);
		user.setPassword(password);
		user.setPhone(phone);
		user.setAddress(address);
		
		
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.addUser(user);
		resp.sendRedirect("signin.jsp");
		
	}

}
