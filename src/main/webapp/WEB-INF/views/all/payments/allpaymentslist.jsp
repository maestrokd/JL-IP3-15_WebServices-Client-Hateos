<!DOCTYPE html>
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
    <%--<script type="text/javascript" src="../../resources/static/js/paymentspager.js"></script>--%>
    <script type="text/javascript" src="../../resources/static/js/pager.js"></script>

    <title>All Payments List</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>--%>
    <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        var page = location.search.split('page=')[1] ? location.search.split('page=')[1] : 0;
        var size = location.search.split('size=')[1] ? location.search.split('size=')[1] : 20;
    </script>

</head>
<body onload="loadPayments(page, size)">
    <%@ include file="../../include/headnav.jsp" %>
    <div class="container-fluid">
        <div class="row content">
            <div class="col-sm-3">
                <%--<button onclick="location.href='/all/payments/add/${selectedCustomer.phoneNumber}'" class="btn btn-primary">Create payment</button>--%>
            </div>

            <div class="col-sm-9">
                <hr>
                <a href="payments">Payments</a>
                <div class="panel panel-default">
                    <div class="panel-body" id="div1">All Payments</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
