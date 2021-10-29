<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="literatures.title" var="title"/>
    <fmt:message key="literature.name" var="name"/>
    <fmt:message key="literature.author" var="author"/>
    <fmt:message key="literature.pages" var="pages"/>
    <fmt:message key="literature.publisher" var="publisher"/>
    <fmt:message key="literature.type" var="type"/>
    <fmt:message key="literatures.rate" var="rate"/>
</fmt:bundle>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/literature_list.css">
    <title>${pageScope.title}</title>
</head>
<body>
<div>
    <tag:userMenu/>
</div>

<table name="table" id="table" border="1" cellpadding="6" cellspacing="6">
    <tr>
        <th>${pageScope.name}</th>
        <th>${pageScope.author}</th>
        <th>${pageScope.genre}</th>
        <th>${pageScope.type}</th>
        <th>${pageScope.pages}</th>
        <th>${pageScope.publisher}</th>
    </tr>
    <c:forEach var="literature" items="${literatures}">
        <tr>
            <td>${literature.literatureName}</td>
            <td>${literature.author}</td>
            <td>${literature.genre}</td>
            <td>${literature.literatureType}</td>
            <td>${literature.bookPages}</td>
            <td>${literature.publisher}</td>
            <td><a href="${pageContext.request.contextPath}/controller?command=ADD_FEEDBACK&literature_id=${literature.id}">${pageScope.rate}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
