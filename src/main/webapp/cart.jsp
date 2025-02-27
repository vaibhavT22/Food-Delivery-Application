<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>   
<%@ page import="java.util.List,com.vt.model.Cart,com.vt.model.CartItem,com.vt.model.User" %>   
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>VT Food - Cart</title>
    <link rel="stylesheet" href="cart.css">
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

    <div id="app"></div>
    <h1 class="page-title">Your Cart</h1>
    
    <div class="cart-items">
      <%
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);  
        }
        
        if (cart != null && !cart.getItems().isEmpty()) {
            for (CartItem item : cart.getItems().values()) {
      %>
      
      <div class="cart-item">
        <div class="cart-item-info">
          <h2><%= item.getName() %></h2>
          <div class="cart-item-price">
            <p>Price: ₹<%= String.format("%.2f", item.getPrice()) %></p>
            <p>Total: ₹<%= String.format("%.2f", item.getTotalPrice()) %></p>
          </div>
          
          <div class="cart-item-quantity">
            <form action="cart" method="post">
              <input type="hidden" name="itemId" value="<%= item.getId() %>">
              <input type="hidden" name="action" value="update">
              <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
              <input type="hidden" name="quantity" value="<%= item.getQuantity() + 1 %>">
              <button class="quantity-btn">+</button>
            </form>
            <p><%= item.getQuantity() %></p>
            <form action="cart" method="post">
              <input type="hidden" name="itemId" value="<%= item.getId() %>">
              <input type="hidden" name="action" value="update">
              <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
              <input type="hidden" name="quantity" value="<%= item.getQuantity() - 1 %>">
              <button class="quantity-btn" <% if(item.getQuantity() <= 1) { %> disabled <% } %>>-</button>
            </form>
          </div>
        </div>
        
        <form action="cart" method="post">
          <input type="hidden" name="itemId" value="<%= item.getId() %>">
          <input type="hidden" name="action" value="remove">
          <input type="hidden" name="restaurantId" value="<%= session.getAttribute("restaurantId") %>">
          <button class="quantity-btn">Remove</button>
        </form>
      </div>
      
      <% } %>
      
      <div class="total">
        Grand Total: ₹<%= String.format("%.2f", cart.getTotalPrice()) %>
      </div>
      
      <div class="add-more-items">
        <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn-add">Add more items</a>
      </div>
      
      <form action="check-out.jsp" method="post">
      	<input type="hidden" name="cart" value="<%= cart %>">
      	<input type="hidden" name="totalPrice" value="<%= String.format("%.2f", cart.getTotalPrice()) %>">
        <input type="submit" value="Proceed to Checkout" class="checkout-btn">
      </form>
      
      <% } else { %>
      <h3>Your cart is empty.</h3>
      <div class="add-more-items">
        <a href="menu?restaurantId=<%= session.getAttribute("restaurantId") %>" class="btn-add">Add Items</a>
      </div>
      <% } %>
    </div>

  </body>
</html>
