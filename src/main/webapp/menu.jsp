<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.vt.model.Menu,com.vt.model.User" %>    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>VT Food - Menu</title>
    <link rel="stylesheet" href="menus.css">
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
      <h1 class="page-title">Our Menu</h1>
      <div class="food-grid">
        
        
        <%
        	List<Menu> menuList = (List<Menu>)request.getAttribute("menus");
        	for(Menu m : menuList)
        	{
        		
        %>
        <div class="food-card">
          <img src=<%= m.getImagePath() %> alt="Pizza">
          <div class="food-info">
            <h2><%= m.getItemName() %></h2>
            <p><%= m.getDescription() %></p>
            <div class="price">₹ <%= m.getPrice() %></div>
            <form action="cart" method="post">
            <input type = "hidden" name="restaurantId" value="<%= request.getParameter("restaurantId") %>">
            <input type = "hidden" name="itemId" value="<%=  m.getMenuId() %>">
            <input type = "number" name="quantity" value="1" min="1">
            <input  type ="hidden" name="action" value="add">
            <button class="add-to-cart">Add to Cart</button>
            </form>
          </div>
        </div>
        
        <%
        	}
        %>

      </div>
    </div>

    
  </body>
</html>
