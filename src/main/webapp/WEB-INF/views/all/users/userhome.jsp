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
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Enter successful</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">

    <%@ include file="../../include/headnav.jsp" %>

    <c:if test="${selectedUser != null}">
        <p>Numbers for ${selectedUser.login}  </p>
    </c:if>
    <c:if test="${selectedUser == null}">
        <p>Numbers for ${pageContext.request.userPrincipal.name}  </p>
    </c:if>

    <sec:authorize access="hasRole('ROLE_ADMIN')">

        <button onclick="location.href='/protected/customers/add'" class="btn btn-primary">Add New Customer</button>

    </sec:authorize>

    <c:if test="${!empty customerList}">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Phone number</th>
                    <th>Customer Status</th>
                    <th>Balance</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="listItem" items="${customerList}">
                    <tr>
                        <td value="${listItem}">|${listItem.phoneNumber}|</td>
                        <td value="${listItem}">|${listItem.customerStatus.name}|</td>
                        <td value="${listItem}">|${listItem.balance}|</td>
                        <td>

                            <%--<a href="/customer/${listItem.phoneNumber}" class="button green">Info</a>--%>
                            <button onclick="location.href='/all/customers/${listItem.phoneNumber}'" class="btn btn-info">Info</button>

                            <%--<a href="/customer/${listItem.phoneNumber}/update" class="button green">Update</a>--%>
                            <button onclick="location.href='/all/customers/${listItem.phoneNumber}/update'" class="btn btn-primary">Update</button>

                            <%--<a href="/customer/${listItem.phoneNumber}/delete" class="button red">Delete</a>--%>
                            <sec:authorize access="hasRole('ROLE_ADMIN')">
                                <c:if test="${selectedUser != null}">
                                    <button onclick="location.href='/protected/customers/${listItem.phoneNumber}/delete?login=${selectedUser.login}'" class="btn btn-danger">Delete</button>
                                </c:if>
                            </sec:authorize>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>
</body>
</html>
