</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/main.css">
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
%>
<ul>
    <c:if test="${(sessionScope.user.roleName == 'user')}">
        <li><a class="active" href="main.jsp">Home</a></li>
        <li><a href="newOrder.jsp">Issue a literature</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=clientOrder">My issues</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=clientOrder">My books</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
        <li style="float:right"><a href="accountUpdate.jsp">${sessionScope.user.status}</a></li>
    </c:if>
    <c:if test="${(sessionScope.user.roleName == 'ADMIN')}">
        <li><a class="active" href="main.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=userList">Users</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=orders">user issues</a></li>
        <li><a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a></li>
        <li style="float:right"><a href="accountUpdate.jsp">${sessionScope.user.status}</a></li>
    </c:if>
</ul>
</body>
</html>