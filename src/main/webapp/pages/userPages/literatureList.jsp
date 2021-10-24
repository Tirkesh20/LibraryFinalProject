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
</fmt:bundle>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"

"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>${pageScope.title}</title>
</head>
<body>
<table border="1" cellpadding="6" cellspacing="6">
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
        </tr>
    </c:forEach>
</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="${pageContext.request.contextPath}/controller?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers.
The when condition does not display a link for the current page--%>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="${pageContext.request.contextPath}/controller?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>
<c:if test="${currentPage lt noOfPages}">
    <td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
</c:if>

</body>
</html>