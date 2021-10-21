<%@ page contentType="text/html;charset=UTF-8" language="java" %>
%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<fmt:bundle basename="page_content">
    <fmt:message key="admin.role" var="role"/>
    <fmt:message key="issue.confirm" var="confirm"/>
    <fmt:message key="literature.name" var="Literature name"/>
    <fmt:message key="register.email" var="email"/>
    <fmt:message key="client.telephone" var="phone"/>
    <fmt:message key="register.password" var="password"/>
    <fmt:message key="register.repeat_password" var="repeat_password"/>
    <fmt:message key="register.first_name" var="first_name"/>
    <fmt:message key="register.last_name" var="last_name"/>
    <fmt:message key="register.register_submit" var="register_submit"/>
    <fmt:message key="register.confirmation_code" var="confirmation_code"/>
    <fmt:message key="title.login" var="title_login"/>
    <fmt:message key="title.password" var="title_password"/>
    <fmt:message key="title.confirm_password" var="title_confirm_password"/>
    <fmt:message key="title.first_name" var="title_first_name"/>
    <fmt:message key="title.last_name" var="title_last_name"/>
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
