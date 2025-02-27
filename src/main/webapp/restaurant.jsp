<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.vt.model.Restaurant,com.vt.model.User" %>    
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>VT Food - Restaurant Directory</title>
    <link rel="stylesheet" href="restaurants.css">
  </head>
  <body>
    <header class="header">
      <div class="header-content">
        <a href="home" class="logo">VT Food</a>
        <%
        User user=(User)session.getAttribute("loggedUser");
        String name= user.getUsername();%>
        <h2>Welcome <%=name %></h2>
       <nav class="nav-links">
       <form action="logout" method="get" >
       <input type="submit" value="Logout" id="logout-btn"></form>
          <a href="home">Home</a>
          <a href="cart.jsp">Cart</a>
          <a href="orderHistory.jsp">orderhistory</a>
       <%//if(user.getRole().equalsIgnoreCase("admin")) {%>
        <!--    <a href="admin.jsp">Admin</a>-->
       
       <%//} %>
      
        </nav>
      </div>
    </header>

	


    <div id="app">
      <h1 class="page-title">Popular Restaurants</h1>
      <div class="restaurant-grid">
      
      
      
      <%
      
      List<Restaurant> restaurants = (List<Restaurant>)request.getAttribute("restaurants");
      
      for( Restaurant r:restaurants )
      {
    	  
      
      
      %>
      
        <div class="restaurant-card">
        <a href="menu?restaurantId=<%= r.getRestaurantId() %>">
          <img src=<%= r.getImagePath() %> alt="Pizza">
          <div class="restaurant-info">        
            <h2><%=	r.getName() %></h2>
            <div class="tags">
              <span><%= r.getCuisineType() %></span>
              <span><%= r.getRating() %></span>
              <span><%= r.getEta() %></span>
              
              
            </div>
           
          </div>
           </a>
        </div>
		
		<%
      		}
		%>
       

        
      </div>
    </div>
  </body>
</html>