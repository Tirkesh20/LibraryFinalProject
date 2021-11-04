<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="tag" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="page_content">
    <fmt:message key="issue.confirm" var="confirm"/>
    <fmt:message key="literature.name" var="name"/>
    <fmt:message key="literature.feedback" var="feedback"/>
    <fmt:message key="literature.author" var="author"/>
    <fmt:message key="literature.type" var="type"/>
</fmt:bundle>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/main.css">
    <title>Issue book</title>
</head>
<body>
<div>
    <tag:userMenu/>
</div>

<div class="issue_form">
    <form id="issueForm" name="IssueForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="CONFIRM_ISSUE_LITERATURE"/>
        <input type="hidden" name="literature_id" value="${sessionScope.literature.id}">
        <p><span>${pageScope.name}</span>
            ${sessionScope.literature.literatureName}
        </p>
        <p><span>${pageScope.type}</span>
            ${sessionScope.literature.literatureType}
        </p>
        <p>Days <label>
            <select name="return_date" class="log_input" required >
                <option value="7">1 week</option>
                <option value="14">2 weeks</option>
                <option value="30">1 month</option>
                <option value="60">2 month</option>
                <option value="90">3 month</option>
            </select>
        </label></p>
        <input class="issue_submit" id="issue_submit" type="submit" value="${pageScope.confirm}" />
    </form>
</div>

</body>
</html>
