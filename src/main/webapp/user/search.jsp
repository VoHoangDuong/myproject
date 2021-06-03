<%--
  Created by IntelliJ IDEA.
  User: Dương Võ Hoàng
  Date: 6/2/2021
  Time: 2:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Details</title>
    <link rel="stylesheet" href="/user/styleform.css">
</head>
<body>
<center>
    <h1>User Details </h1>
    <p>
        <a href="/users">Back to user list</a>
    </p>
</center>
<fieldset class="container">
    <legend class="legend">Search list is</legend>
    <table>
        <tr>
            <th>Name: </th>
            <th>Email: </th>
            <th>Country</th>
        </tr>
        <c:forEach items='${requestScope["userList"]}' var="user">
            <tr>
                <td>${user.getName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getCountry()}</td>
            </tr>
        </c:forEach>
    </table>
</fieldset>
</body>
</html>