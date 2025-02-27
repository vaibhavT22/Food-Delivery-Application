<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ page import="java.util.List,com.vt.model.Order,com.vt.model.User,com.vt.daoimplementation.OrderDAOImpl" %>    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Order Confirmation - VT Food</title>
    <link rel="stylesheet" href="orderConfirmations.css">
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
      <h1 class="page-title">Order Confirmation</h1>

      <div class="confirmation-container">
        <div class="confirmation-details">
        <%
        Order order =(Order)session.getAttribute("order");
        
        %>
        
          <h2>Thank You for Your Order!</h2>
          <p>Your order has been successfully placed. We are currently processing it, and you will receive a confirmation email shortly.</p>
        </div>


        <div class="customer-info">
          <h2>Customer Information</h2>
          <p><strong>Name:</strong> <%=user.getName() %></p>
          <p><strong>Email:</strong> <%=user.getEmail() %></p>
          <p><strong>Address:</strong> <%=user.getAddress() %></p>
          <p><strong>Phone:</strong> <%= user.getPhone() %></p>
        </div>

        <div class="next-steps">
          <p>Your food is on its way! We will notify you when it arrives.</p>
        </div>
      </div>
    </div>
  </body>
</html>
