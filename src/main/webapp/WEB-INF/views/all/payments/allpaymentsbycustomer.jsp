<%--<!DOCTYPE html>--%>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 21.12.2017
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script type="text/javascript" src="../../resources/static/js/servicesforcustomer.js"></script>

    <title>All Payment By Customer</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
<%@ include file="../../include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
            <%--<button onclick="location.href='/all/payments/add/${selectedCustomer.phoneNumber}'" class="btn btn-primary">Create payment</button>--%>
        </div>

        <div class="col-sm-9">

            <c:if test="${not empty message}">
                <strong>${message}</strong>
            </c:if>

            <c:if test="${not empty paymentList}">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>ID</th>
                        <%--<th>Phone number</th>--%>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Channel</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="listItem" items="${paymentList}">
                        <tr>
                            <td>${listItem.paymentId}</td>
                            <%--<td>${listItem.customer.id}</td>--%>
                            <td>${listItem.amount}</td>
                            <td>${listItem.date}</td>
                            <td>${listItem.channel}</td>
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
