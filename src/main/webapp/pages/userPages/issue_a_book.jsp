<%@ page contentType="text/html;charset=UTF-8" language="java" %>
%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="issue.confirm" var="confirm"/>
    <fmt:message key="literature.name" var="Literature name"/>
    <fmt:message key="literature.feedback" var="feedbacks"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
    <title>Issue book</title>
</head>
<body>

<div class="reg_form">
    <form id="reg" name="RegisterForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="common_register"/>

        <p><span>${pageScope.first_name}</span>
            <input id="first_name" title="${pageScope.title_first_name}" type="text" name="firstname" value="" placeholder="Jonson"/>
        </p>
        <p><span>${pageScope.last_name}</span>
            <input id="last_name" title="${pageScope.title_last_name}" type="text" name="lastname" value="" placeholder="Kidora"
                   onkeyup="checkName();"/>
        </p>
        <p><span>${pageScope.login}</span>
            <input id="login" title="${pageScope.title_login}" type="text" name="login" value="" placeholder="Jonson2019"
                   onkeyup="checkLogin();"/>
        </p>
        <p><span>${pageScope.email}</span>
            <label>
                <input type="date" name="party" min="2017-04-01" max="2017-04-30">
            </label>
        </p>
        <input class="reg_submit" id="reg_submit" type="submit" value="${pageScope.register_submit}" disabled/>
    </form>
</div>

</body>
</html>
