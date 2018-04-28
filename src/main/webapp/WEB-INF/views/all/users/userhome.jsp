<!DOCTYPE html>
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
<%@ include file="../../include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <button onclick="location.href='/protected/customers/add'" class="btn btn-primary btn-block">Add New
                    Customer
                </button>
            </sec:authorize>
        </div>

        <div class="col-sm-9">

            <c:if test="${selectedUser == null}">
                <p>Numbers for ${pageContext.request.userPrincipal.name}  </p>
            </c:if>

            <c:if test="${not empty message}">
                <div class="alert alert-success alert-dismissable">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Success!</strong> ${message}.
                </div>
            </c:if>

            <c:if test="${not empty messageError}">
                <div class="alert alert-danger alert-dismissable">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong> ${messageError}.
                </div>
            </c:if>

            <c:if test="${selectedUser != null}">
                <table class="table table-condensed">
                    <tbody>
                    <tr>
                        <td><strong>User name:</strong></td>
                        <td><strong>${selectedUser.name}</strong></td>
                    </tr>
                    <tr>
                        <td>User login:</td>
                        <td>${selectedUser.login}</td>
                    </tr>
                    <tr>
                        <td>User email:</td>
                        <td>${selectedUser.email}</td>
                    </tr>

                    <tr>
                        <td>Update User:</td>
                        <td>
                            <button onclick="location.href='/all/users/${selectedUser.login}/update'"
                                    class="btn btn-primary">Update
                            </button>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </c:if>


            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Panel with phone numbers</h4>
                </div>
                <div class="panel-body">
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
                        <c:if test="${!empty customerList}">
                            <c:forEach var="listItem" items="${customerList}">
                                <tr>
                                    <td value="${listItem}">${listItem.phoneNumber}</td>
                                    <td value="${listItem}">${listItem.customerStatus.name}</td>
                                    <td value="${listItem}">${listItem.balance}</td>
                                    <td>
                                        <button onclick="location.href='/all/customers/${listItem.phoneNumber}'"
                                                class="btn btn-info">Info
                                        </button>
                                        <button onclick="location.href='/all/customers/${listItem.phoneNumber}/update'"
                                                class="btn btn-primary">Update
                                        </button>
                                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                                            <c:if test="${selectedUser != null}">
                                                <button onclick="location.href='/protected/customers/${listItem.phoneNumber}/delete?login=${selectedUser.login}'"
                                                        class="btn btn-danger">Delete
                                                </button>
                                            </c:if>
                                        </sec:authorize>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
