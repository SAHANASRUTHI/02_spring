<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<html>
<head>
<title>Home</title>
</head>
<body>
    <h1>Hello world!</h1>
 
    <P>The time on the server is ${serverTime}.</p>
 
    <form action="${pageContext.request.contextPath}/item/user" method="post">
        <input type="text" name="itemId">item iD: <br> 
        <input type="text" name="itemName">item Name: <br> 
        <input type="date" name="expDate">EXP DATE: <br> 
        <input type="text" name="price">price: <br> 
        <input type="text" name="category">category: <br> 

        <input type="submit" value="Submit">
    </form>
    <!-- helloMessage =  ${helloMessage} -->
    <!-- welcomeMessage =  ${welcomeMessage} -->
    
</body>
</html>