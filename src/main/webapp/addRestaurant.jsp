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
              <input type="text" id="name" name="name" required placeholder="Restaurant name" />
            </div>
            <div class="input-group">
              <label for="address">Address</label>
              <input type="text" id="address" name="address" required placeholder="Restaurant address" />
            </div>
            <div class="input-group">
              <label for="ph">Phone</label>
              <input type="text" id=ph name="ph" required placeholder="Restaurant phone" />
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

