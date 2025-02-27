<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign Up - VT Food</title>
    <link rel="stylesheet" href="signup.css" />
  </head>
  <body>
    <header class="header">
      <div class="header-content">
        <a href="signup.jsp" class="logo">VT Food</a>
        <nav class="nav-links">
          <a href="signin.jsp">Signin</a>
        </nav>
      </div>
    </header>

    <div id="app">
      <h1 class="page-title">Sign Up</h1>

      <div class="signup-form">
        <div class="form-container">
          <form action="signup" method="POST">
            <div class="input-group">
              <label for="name">Name</label>
              <input type="text" id="name" name="name" required placeholder="Enter your full name" />
            </div>
            
            <div class="input-group">
              <label for="username">Username</label>
              <input type="text" id="username" name="username" required placeholder="Enter your full name" />
            </div>
            
            <div class="input-group">
              <label for="role">Role</label>
              <input type="text" id="role" name="role" required placeholder="Enter your role" />
            </div>

            <div class="input-group">
              <label for="email">Email Address</label>
              <input type="email" id="email" name="email" required placeholder="Enter your email" />
            </div>

            <div class="input-group">
              <label for="password">Password</label>
              <input type="password" id="password" name="password" required placeholder="Enter your password" />
            </div>
            
            <div class="input-group">
              <label for="phone">Phone</label>
              <input type="tel" id="phone" name="phone" required placeholder="Enter your phonenumber" />
            </div>
            
            <div class="input-group">
              <label for="address">Address</label>
              <textarea id="address" name="address" required placeholder="Enter your shipping address"></textarea>
            </div>



            <div class="form-footer">
              <button type="submit" class="submit-btn">Sign Up</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>

