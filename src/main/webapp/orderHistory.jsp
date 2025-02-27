<%@page import="com.vt.DAO.OrderDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,com.vt.model.Order,com.vt.model.User,com.vt.daoimplementation.OrderDAOImpl" %>    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Order History - VT Food</title>
    <link rel="stylesheet" href="orderhistorys.css" />
  </head>
  <body>
    <header class="header">
      <div class="header-content">
         <a href="home" class="logo">VT Food</a>
         <%User user=(User)session.getAttribute("loggedUser");
         String name= user.getUsername();%>
         <h2>Welcome <%=name %></h2>
        <nav class="nav-links">
        <form action="logout" method="get">
        <input type="submit" value="Logout"></form>
          <a href="home">Home</a>
          <a href="cart.jsp">Cart</a>
          <a href="orderHistory.jsp">orderhistory</a>
        </nav>
      </div>
    </header>

    <div id="app">
      <h1 class="page-title">Order History</h1>

      <div class="order-history">
        <div class="order-card">
        <%
        OrderDAOImpl orderDAO= new OrderDAOImpl();
        int userId= user.getUserId();
        List<Order> orders = orderDAO.getAllOrderByUser(userId);
      
      	
        for(Order o:orders){
        %>
          <h3 class="order-title">Order Id: <%=o.getOrderId() %></h3>
          <p class="order-date">Order date: <%=o.getOrderDate() %></p>
          <div class="order-items">
            <div class="order-item">
              <div class="item-info">
                <span class="item-price">Payment mode: <%= o.getPaymentMode() %></span><br>
                <span class="item-price">Status: <%= o.getStatus() %></span>
              </div>
            </div>
            
          </div>
          <p class="order-total">Total price: <%= o.getTotalAmount() %></p>
          <%} %>
        </div>

      </div>
    </div>
  </body>
</html>
