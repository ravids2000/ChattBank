<%--
  Created by IntelliJ IDEA.
  User: Ravid
  Date: 1/29/2026
  Time: 9:45 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login - ChattBank</title>
    <!-- Link to CSS and JS files -->
    <link rel="stylesheet" href="css/style.css">
    <script src="js/login.js" defer></script>
</head>
<body>

<!-- Header with Logo and Page Title -->
<header class="top-header">
    <img src="images/chattbank_logo.png" alt="ChattBank Logo" class="logo">
    <h1 class="page-title">Login to ChattBank</h1>
</header>

<!-- Main Content -->
<div class="container">

    <!-- Hero / Laptop Image -->
    <section class="hero">

        <!-- Login Form overlay laptop image (W3School HTML Tags <form>)-->
        <div class="login-box">
            <!-- for lab3 step 3 removed onsubmit="return validateLogin();"-->
            <form name="loginForm" action="LoginServlet" method="post" onsubmit="return validateLogin();" autocomplete="off">

                <!-- com.example.ChattBank.CustomerObject.Customer ID row -->
                <div class="form-row">
                    <label for="customerId">Customer ID</label>
                    <input type="text"
                           id="customerId"
                           name="customerId"
                           placeholder="Enter Customer ID"
                           autocomplete="off">
                </div>

                <!-- Password row -->
                <div class="form-row">
                    <label for="password">Password</label>
                    <input type="password"
                           id="password"
                           name="password"
                           placeholder="Enter password"
                           autocomplete="off">
                </div>

                <!-- Buttons row -->
                <div class="form-buttons">
                    <input type="submit" value="Login" class="btn-login">
                    <input type="reset" value="Clear" class="btn-clear">
                </div>

            </form>
        </div>

        <img src="images/digital-tablet-laptop.jpg" alt="tablet and laptop">
    </section>

    <!-- Side Navigation Menu -->
    <nav class="side-menu">
        <a href="index.jsp">HOME</a>
        <a href="login.jsp">LOGIN</a>
        <a href="accountLookup.jsp">ACCOUNT LOOKUP</a>
    </nav>

</div>

</body>
</html>
