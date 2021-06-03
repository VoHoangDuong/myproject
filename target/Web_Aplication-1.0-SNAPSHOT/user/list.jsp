<%--
  Created by IntelliJ IDEA.
  User: Dương Võ Hoàng
  Date: 6/2/2021
  Time: 2:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Manager Application</title>
    <link rel="stylesheet" href="/user/style.css">
</head>
<body>
<center>
    <h1><a href="/users">User Management</a></h1>
    <h3>
        <a href="/users?action=create">Add New User</a>&nbsp;&nbsp;&nbsp;<a href="/users?action=sort">Sort By Names</a>
    </h3>
    <form method="get">
        <input type="hidden" name="action" value="search"/>
        <input type="text" name="keyword" id="country" placeholder="Enter keyword..."/>
        <input class="search" type="submit" value="Search"/>
    </form>
</center>
<div id="table-wrapper">
    <table border="1">
        <caption class="caption"><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Country</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${userList}" var="list">
            <tr>
                <td><c:out value="${list.id}"/></td>
                <td><c:out value="${list.name}"/></td>
                <td><c:out value="${list.email}"/></td>
                <td><c:out value="${list.country}"/></td>
                <td><a class="a" href="/users?action=edit&id=${list.id}">Edit</a>
                    <a class="a" href="/users?action=delete&id=${list.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
