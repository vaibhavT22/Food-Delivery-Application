<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Sign In - VT Food</title>
    <link rel="stylesheet" href="signin.css" />
  </head>
  <body>
    <header class="header">
      <div class="header-content">
        <a href="signin.jsp" class="logo">VT Food</a>
        <nav class="nav-links">
        </nav>
      </div>
    </header>

    <div id="app">
      <h1 class="page-title">Sign In</h1>

      <div class="signin-form">
        <div class="form-container">
          <form action="signin" method="POST">
            <% 
              Boolean retry = (Boolean) session.getAttribute("retry");
              if (retry != null && retry) { 
            %>
              <p class="error-message">Invalid email or password. Please try again.</p>
            <% 
              } 
            %>
            <div class="input-group">
              <label for="email">Email Address</label>
              <input type="email" id="email" name="email" required placeholder="Enter your email" />
            </div>

            <div class="input-group">
              <label for="password">Password</label>
              <input type="password" id="password" name="password" required placeholder="Enter your password" />
            </div>

            <div class="form-footer">
              <button type="submit" class="submit-btn">Sign In</button>
              <p class="no-account">Don't have an account? <a href="signup.jsp">Sign Up</a></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
