
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/j>s/bootstrap.min.js"></script--%>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">WebSiteName</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/all/users/${pageContext.request.userPrincipal.name}">Home</a></li>
                <li><a href="/protected/users/userlist">User List</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <%--<li><p class="navbar-text">Language:</p> </li>--%>
                <%--<li><a href="?lang=en">English</a></li>--%>
                <%--<li><p class="navbar-text">|</p> </li>--%>
                <%--<li><a href="?lang=ru">Russian</a></li>--%>
                <%--<li><a href="#">${pageContext.response.locale}</a></li>--%>

                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">Language
                        <span class="caret"></span> ${pageContext.response.locale}</a>
                    <ul class="dropdown-menu">
                        <li><a href="?lang=en">English</a></li>
                        <li><a href="?lang=ru">Russian</a></li>
                    </ul>
                </li>
                <%--<p class="navbar-text">${pageContext.response.locale}</p>--%>

                <c:if test="${pageContext.request.userPrincipal.name == null}">
                    <li><a href="/userregistration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
                    <li><a href="/newlogin"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name != null}">
                    <li><a href="#">Welcome : ${pageContext.request.userPrincipal.name}</a></li>
                    <c:url var="logoutUrl" value="/logout"/>
                    <form action="${logoutUrl}" id="logout" class="navbar-form navbar-left" method="post">
                        <div class="input-group">
                            <input type="hidden" name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>
                            <div class="input-group-btn">
                                <button class="btn btn-link" type="submit"><span
                                        class="glyphicon glyphicon-log-out"></span> Logout
                                </button>
                            </div>
                        </div>
                    </form>
                </c:if>
            </ul>
        </div>
    </div>
</nav>