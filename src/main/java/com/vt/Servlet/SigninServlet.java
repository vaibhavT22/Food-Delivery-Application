package com.vt.Servlet;

import java.io.IOException;
import java.util.List;

import com.vt.daoimplementation.UserDAOImpl;
import com.vt.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signin")
public class SigninServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		UserDAOImpl userDAO = new UserDAOImpl();
		List<User> allUsers = userDAO.getAllUsers();
//		System.out.println(allUsers);
//		System.out.println("Signin Servlet");
//		System.out.println(password);
		boolean userFound=false;
		for(User user:allUsers) {
			String userEmail= user.getEmail();
			String userPassword=user.getPassword();
//			System.out.println(userEmail+"--->"+userPassword);
			if(userEmail.equals(email)&&userPassword.equals(password)) {
				System.out.println("Logged in Succesfully");
				req.getSession().removeAttribute("retry");
				userFound=true;
				req.getSession().setAttribute("loggedUser", user);
				resp.sendRedirect("home");
		}
		}if(!userFound) {
			System.out.println("Email or Password incorrect");
			req.getSession().setAttribute("retry", true);
			resp.sendRedirect("signin.jsp");
		}
		
	}

}
