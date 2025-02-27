<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.List,com.vt.model.Menu,com.vt.model.User" %>  
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Checkout - VT Food</title>
    <link rel="stylesheet" href="checkout.css">
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
      <h1 class="page-title">Checkout</h1>

      <div class="checkout-container">
          <h3>Shipping Information</h3>
          <form action="checkout" method="POST">
            <label for="address">Shipping Address</label>
            <textarea id="address" name="address" required placeholder="Enter your shipping address"></textarea>
            <label for="payment">Payment Method</label>
            <select id="payment" name="payment" required>
              <option value="credit-card">Credit Card</option>
              <option value="debit-card">Debit Card</option>
              <option value="Cash-on-Delivery">Cash on Delivery</option>
            </select>
            <button type="submit" class="checkout-btn">Complete Purchase</button>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>

