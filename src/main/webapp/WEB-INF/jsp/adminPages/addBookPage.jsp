<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:bundle basename="page_content">
    <fmt:message key="literature.name" var="name"/>
    <fmt:message key="literature.type" var="type"/>
    <fmt:message key="literature.author" var="author"/>
    <fmt:message key="literature.publisher" var="publisher"/>
    <fmt:message key="literature.pages" var="pages"/>
    <fmt:message key="literature.genre" var="genre"/>
    <fmt:message key="add.submit" var="add"/>
</fmt:bundle>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/main.css">
    <title>${pageScope.title}</title>
</head>
<body class="page">
<p class="error">${requestScope.message}</p>
<div class="reg_form">
    <form id="reg_form" name="AddForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="add_literature"/>

        <p><span>${pageScope.name}</span>
            <input id="literature_name" title="${pageScope.name}" type="text" name="literature_name" value=""required/>
        </p>
        <p><span>${pageScope.type}</span>
            <input id="literature_type" title="${pageScope.type}" type="text" name="type" value="" required/>
        </p>
        <p><span>${pageScope.genre}</span>
        <p><label>
            <select name=${pageScope.genre} class="log_input" required >
                <option >HORROR</option>
                <option >HISTORICAL</option>
                <option >POEM</option>
                <option >ROMAN</option>
                <option >DRAMA</option>
                <option>COMEDY</option>
            </select>
        </label></p>
        <p><span>${pageScope.author}</span>
            <input id="password" title="${pageScope.author}" type="text" name="author" value="" required/>
        </p>
        <p><span>${pageScope.publisher}</span>
            <input id="confirm_password" title="${pageScope.publisher}" type="text" value="" name="publisher" required/>
        </p>
        <p><span>${pageScope.pages}</span>
            <input id="email" title="${pageScope.email}" type="text" name="pages" value=""/>
        </p>
        <input class="reg_submit" id="reg_submit" type="submit" value="${pageScope.add}"/>
    </form>
</div>
</body>
</html>