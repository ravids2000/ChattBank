<%--
  Created by IntelliJ IDEA.
  User: Ravid
  Date: 2/18/2026
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Error</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<!-- added for Lab 7 step 1 -->
<jsp:useBean id="c1"
             scope="session"
             class="com.example.ChattBank.CustomerObject.Customer" />

<!-- Header with Logo and Page Title-->
<header class="top-header">
    <img src="images/chattbank_logo.png" alt="ChattBank Logo" class="logo">
    <h1 class="page-title">Login Error</h1>
</header>

<div class="container">
    <section class="hero">
        <div class="hero-text">
<%--
<!-- Lab 6 - Get an error message stored in LoginServlet, if no message use default -->
<%
    String msg = (String) request.getAttribute("errorMsg");
    if (msg == null) msg = "Invalid login.";
%>
<!-- Lab 6 - Print message on the page -->
<%= msg %>
--%>
    <!-- added for Lab 7 step 1 -->
            <h2>
                Error Logging in for User with ID <jsp:getProperty name="c1" property="custId" />
            </h2>

            <p>Invalid password!!</p>

            <br><br>
            <a href="login.jsp">Return to Login</a>

        </div>

        <img src="images/digital-tablet-laptop.jpg" alt="tablet and laptop">
    </section>

    <nav class="side-menu">
        <a href="index.jsp">HOME</a>
        <a href="login.jsp">LOGIN</a>
        <a href="accountLookup.jsp">ACCOUNT LOOKUP</a>
    </nav>
</div>

</body>
</html>
