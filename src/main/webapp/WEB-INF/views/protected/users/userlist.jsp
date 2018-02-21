<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 21.12.2017
  Time: 0:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%@ include file="../../include/headnav.jsp" %>

    <%--<nav class="navbar navbar-default">--%>
        <%--<div class="container-fluid">--%>
            <%--<div class="navbar-header">--%>
                <%--<a class="navbar-brand" href="/">WebSiteName</a>--%>
            <%--</div>--%>
            <%--<ul class="nav navbar-nav">--%>
                <%--<li class="active"><a href="/">Home</a></li>--%>
            <%--</ul>--%>
            <%--<ul class="nav navbar-nav navbar-right">--%>

                <%--&lt;%&ndash;<li><a href="#">Language :</a></li>&ndash;%&gt;--%>
                <%--<li><p class="navbar-text">Language:</p> </li>--%>
                <%--<li><a href="?lang=en">English</a></li>--%>
                <%--&lt;%&ndash;<li><a href="#">|</a></li>&ndash;%&gt;--%>
                <%--<li><p class="navbar-text">|</p> </li>--%>
                <%--<li><a href="?lang=ru">Russian</a></li>--%>
                <%--<li><a href="#">${pageContext.response.locale}</a></li>--%>

                <%--<c:if test="${pageContext.request.userPrincipal.name == null}">--%>
                    <%--<li><a href="/newlogin"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>--%>
                <%--</c:if>--%>
                <%--<c:if test="${pageContext.request.userPrincipal.name != null}">--%>
                    <%--<li><a href="#">Welcome : ${pageContext.request.userPrincipal.name}</a></li>--%>
                    <%--<c:url var="logoutUrl" value="/logout" />--%>
                    <%--<form action="${logoutUrl}" id="logout" class="navbar-form navbar-left" method="post">--%>
                        <%--<div class="input-group">--%>
                            <%--<input type="hidden" name="${_csrf.parameterName}"--%>
                                   <%--value="${_csrf.token}" />--%>
                            <%--<div class="input-group-btn">--%>
                                <%--<button class="btn btn-default" type="submit">Logout</button>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</form>--%>
                <%--</c:if>--%>
            <%--</ul>--%>
        <%--</div>--%>
    <%--</nav>--%>

    <%--<table>--%>
        <%--<tr>--%>
            <%--<td><a href="/userlogin">|Return to home|</a></td>--%>
            <%--<td>|Hello ${sessionScope.loginUser.login}|</td>--%>
            <%--<td><a href="/logout">|Logout|</a></td>--%>
        <%--</tr>--%>
    <%--</table>--%>

    <c:if test="${!empty customMessage2}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <c:forEach var="message" items="${customMessage2}">
                <c:out value="${message}"/> <br>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${customMessage2 != null}">
        <div class="alert alert-success alert-dismissable">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Success!</strong> ${customMessage2}.
        </div>
    </c:if>

    <c:if test="${!empty userList}">
        <table class="table table-striped" >

            <h2>List of Users:</h2>
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>E-mail</th>
                    <th>Roles</th>
                    <%--<th>Password</th>--%>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="listItem" items="${userList}">
                    <tr>
                        <td value="${listItem}">${listItem.id}</td>
                        <td value="${listItem}">${listItem.login}</td>
                        <td value="${listItem}">${listItem.name}</td>
                        <td value="${listItem}">${listItem.email}</td>
                        <td>
                            <c:if test="${!empty listItem.roleList}">
                                <c:forEach var="listItem2" items="${listItem.roleList}">
                                    <span class="label label-info"> ${listItem2.name}</span>
                                </c:forEach>
                            </c:if>
                        </td>
                        <%--<td value="${listItem}">${listItem.password}</td>--%>
                        <td>
                            <button onclick="location.href='/protected/users/${listItem.login}'" class="btn btn-info">Info</button>
                            <%--<button onclick="location.href='/customer/${listItem.phoneNumber}'">Query</button>--%>
                            <button onclick="location.href='/users/${listItem.login}/update'" class="btn btn-primary">Update</button>
                                <%--<a href="/users/${listItem.login}/update" class="button green">Update</a>--%>
                            <button onclick="location.href='/users/${listItem.login}/delete'" class="btn btn-danger">Delete</button>
                                <%--<a href="/users/${listItem.login}/delete" class="button red">Delete</a>--%>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
