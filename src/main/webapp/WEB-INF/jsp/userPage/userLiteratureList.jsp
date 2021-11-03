<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="page_content">
    <fmt:message key="menu.title" var="title"/>
</fmt:bundle>

<fmt:bundle basename="page_content">
    <fmt:message key="literatures.title" var="title"/>
    <fmt:message key="literature.name" var="name"/>
    <fmt:message key="literature.author" var="author"/>
    <fmt:message key="literature.pages" var="pages"/>
    <fmt:message key="literature.publisher" var="publisher"/>
    <fmt:message key="literature.type" var="type"/>
    <fmt:message key="literatures.rate" var="rate"/>
    <fmt:message key="literature.return_issue" var="return_issue"/>

</fmt:bundle>

<!DOCTYPE html>
<html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/main.css">
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
            <td><a href="${pageContext.request.contextPath}/controller?command=RETURN_ISSUE&literature_id=${literature.id}">${pageScope.return_issue}</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>


