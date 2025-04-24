<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bank.UserInfo"  %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking Navbar</title>
    <link rel="stylesheet" href="../css/profile.css"> <!-- adjust path as needed -->
</head>
<body>
    <div class="navbar">
        <div class="logo">CASHLESS BANK</div>
        <div class="nav-links">
            <a href="home.jsp">Home</a>
            <a href="accounts.jsp">Accounts</a>
            <a href="transfer.jsp">Transfer</a>
            <a href="transactions.jsp">Transactions</a>
            <a href="support.jsp">Support</a>
            <a class="logout" href="logout.jsp">Logout</a>
            
            
        </div>
        <div class="user-area">
        
        	<%  UserInfo user = (UserInfo)session.getAttribute("username");  %>
            <h4>Welcome, <strong> <%= user.name %></strong></h4>
            
        </div>
    </div>
</body>
</html>
