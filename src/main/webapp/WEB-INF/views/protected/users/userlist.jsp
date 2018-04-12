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
<%@ include file="../../include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
            <div class="col-sm-12">
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <button onclick="location.href='/protected/customers/find'" class="btn btn-primary btn-block">
                        Find Customer
                    </button>
                </sec:authorize>
            </div>
        </div>

        <div class="col-sm-9">
            <c:if test="${!empty message}">
                <div class="alert alert-success alert-dismissable">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <c:forEach var="messageItem" items="${message}">
                        <c:out value="${messageItem}"/> <br>
                    </c:forEach>
                </div>
            </c:if>

            <c:if test="${message != null}">
                <div class="alert alert-success alert-dismissable">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> ${message}.
                </div>
            </c:if>

            <c:if test="${!empty userList}">
                <table class="table table-striped">

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
                                <button onclick="location.href='/protected/users/${listItem.login}'"
                                        class="btn btn-info">Info
                                </button>
                                    <%--<button onclick="location.href='/customer/${listItem.phoneNumber}'">Query</button>--%>
                                <button onclick="location.href='/users/${listItem.login}/update'"
                                        class="btn btn-primary">Update
                                </button>
                                    <%--<a href="/users/${listItem.login}/update" class="button green">Update</a>--%>
                                <button onclick="location.href='/users/${listItem.login}/delete'"
                                        class="btn btn-danger">Delete
                                </button>
                                    <%--<a href="/users/${listItem.login}/delete" class="button red">Delete</a>--%>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
