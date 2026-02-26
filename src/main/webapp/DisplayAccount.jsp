<%@ page import="java.text.NumberFormat" %><%--
  Created by IntelliJ IDEA.
  User: Ravid
  Date: 2/25/2026
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.

  Results page (account data populated)
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>AccountLookup - ChattBank</title>
    <!-- Link to CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

<jsp:useBean id="a1"
             scope="session"
             class="com.example.ChattBank.AccountObject.Account" />

<!-- Formating the Balance to Currency with cents-->
<%
    NumberFormat nf = NumberFormat.getCurrencyInstance();
    String balanceFormatted = nf.format(a1.getBalance());
%>

<!-- Header with Logo and Page Title-->
<header class="top-header">
    <img src="images/chattbank_logo.png" alt="ChattBank Logo" class="logo">
    <h1 class="page-title">Account Lookup</h1>
</header>

<!-- Main Content -->
<div class="container">

    <!-- Hero / Credit Card Image -->
    <section class="hero">

        <!-- Account Lookup Form overlay credit card image (W3School HTML Tags <form>) -->
        <div class="form-box">
            <form name="lookupForm" action="AccountLookupServlet" method="post">

                <!-- Account No row -->
                <div class="form-row">
                    <label for="acctNo">Account No</label>
                    <input type="text"
                           id="acctNo"
                           name="acctNo"
                           value="<jsp:getProperty name='a1' property='acctNo' />">
                </div>

                <!-- Customer ID row -->
                <div class="form-row">
                    <label for="custId">Customer ID</label>
                    <input type="text"
                           id="custId"
                           name="custId"
                           value="<jsp:getProperty name='a1' property='custId' />"
                           readonly>
                </div>

                <!-- Type row -->
                <div class="form-row">
                    <label for="type">Type</label>
                    <input type="text"
                           id="type"
                           name="type"
                           value="<jsp:getProperty name='a1' property='type' />"
                           readonly>
                </div>

                <!-- Balance row -->
                <div class="form-row">
                    <label for="balance">Balance</label>
                    <input type="text"
                           id="balance"
                           name="balance"
                           value="<%= balanceFormatted %>"
                           readonly>
                </div>

                <!-- Buttons row -->
                <div class="form-buttons">
                    <input type="submit" value="Lookup" class="btn-submit">
                    <input type="reset" value="Clear" class="btn-clear">
                </div>

            </form>
        </div>

        <img src="images/credit-card.jpg" alt="credit cards">
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