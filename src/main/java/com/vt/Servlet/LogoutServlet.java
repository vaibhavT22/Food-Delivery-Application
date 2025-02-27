package com.vt.Servlet;

import java.io.IOException;

import com.vt.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 HttpSession session = req.getSession();
	 User user=(User)session.getAttribute("loggedUser");
	 System.out.println(user);
	 	session.removeAttribute("loggedUser");
		RequestDispatcher rd = req.getRequestDispatcher("signin.jsp");
		
		rd.forward(req, resp);
	}

}
