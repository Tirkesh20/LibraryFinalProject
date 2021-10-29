<%@tag pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@taglib prefix="tag" tagdir="/WEB-INF/tags" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/header.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static.contents/css/font-awesome.min.css">

<fmt:bundle basename="page_content">
    <fmt:message key="menu.title" var="title"/>
    <fmt:message key="menu.hello" var="hello"/>
    <fmt:message key="menu.hello_guest" var="hello_guest"/>
    <fmt:message key="menu.login" var="login"/>
    <fmt:message key="menu.register" var="register"/>
    <fmt:message key="menu.logout" var="logout"/>
    <fmt:message key="menu.add_literature" var="Add"/>
    <fmt:message key="menu.profile" var="profile"/>
    <fmt:message key="menu.show_users" var="show_users"/>
    <fmt:message key="menu.find_literature" var="find_literature"/>
    <fmt:message key="user.curr_issue" var="issues"/>
    <fmt:message key="literatures.title" var="literatures"/>

</fmt:bundle>

<header class="header">
    <h1 class="top">${pageScope.title}</h1>
    <div class="change_level">
        <ul>
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=common_change_language&locale=ru">RU</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/controller?command=common_change_language&locale=en">EN</a>
            </li>
        </ul>
    </div>
    <div class="hello_message">
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <span class="hello_text">${pageScope.hello_guest}</span>
                <a class="logout_a"
                   href="${pageContext.request.contextPath}/login" id="login">${pageScope.login}</a>
                <a class="register_login_a"
                   href="${pageContext.request.contextPath}/register"
                   id="register">${pageScope.register}</a>
            </c:when>
            <c:otherwise>
                <span class="hello_text">${pageScope.hello} ${sessionScope.user.name} ${sessionScope.user.lastName}</span>
                <a class="register_login_a"
                   href="${pageContext.request.contextPath}/controller?command=logout">${pageScope.logout}</a>
            </c:otherwise>
        </c:choose>
    </div>
</header>

<div class="user_menu">
    <ul>
        <c:if test="${sessionScope.user != null}">
            <li>
                <a href="${pageContext.request.contextPath}/index.jsp">
                    <i class="fa fa-home" aria-hidden="true"></i>
                </a>
            </li>
            <c:choose>
                <c:when test="${sessionScope.user.role == 'USER' }">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=COMMON_LITERATURES">${pageScope.title}</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=COMMON_USER_LITERATURES">${pageScope.issues}</a>
                    </li>
                    <li>
                        <tag:status/>
                    </li>
                </c:when>
                <c:when test="${sessionScope.user.role == 'ADMIN'}">
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=COMMON_LITERATURES">${pageScope.literatures}</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=Add_literature">${pageScope.Add}</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/controller?command=show_users">${pageScope.show_users}</a>
                    </li>
                    <li>
                        <form id="find" name="FindForm" method="POST"
                              action="${pageContext.request.contextPath}/controller">
                            <input type="hidden" name="command" value="find_book"/>
                            <label>${pageScope.find_book} <input type="text" name="name" value=""/></label>
                            <button type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                        </form>
                    </li>
                </c:when>
            </c:choose>
        </c:if>
    </ul>
</div>