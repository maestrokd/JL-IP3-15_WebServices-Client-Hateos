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

    <title>Customer Room</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body onload="init()">
<%@ include file="../../include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
            <div class="col-sm-12">
                <button onclick="location.href='/all/payments/add/${selectedCustomer.phoneNumber}'"
                        class="btn btn-default btn-block">Create payment
                </button>
            </div>
            <div class="col-sm-12">
                <button onclick="location.href='/getallpayments/${selectedCustomer.id}'" class="btn btn-default btn-block">Show
                    all payments By Customer
                </button>
            </div>
            <div class="col-sm-12">
                <button onclick="location.href='/getallpayments'" class="btn btn-default btn-block">Show all payments</button>
            </div>

            <div class="col-sm-12">
                <button onclick="location.href='/all/events/${selectedCustomer.phoneNumber}/sms/create'" class="btn btn-default btn-block">Create SMS</button>
            </div>

            <div class="col-sm-12">
                <button onclick="location.href='/all/events/${selectedCustomer.phoneNumber}/mms/create'" class="btn btn-default btn-block">Create MMS</button>
            </div>

        </div>

        <div class="col-sm-9">

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


            <table class="table table-condensed">
                <tbody>
                <tr>
                    <td><strong>Phone number:</strong></td>
                    <td id="selected-phone-number"><strong>${selectedCustomer.phoneNumber}</strong></td>
                </tr>
                <tr>
                    <td>Customer Status:</td>
                    <td>${selectedCustomer.customerStatus.name}</td>
                </tr>
                <tr>
                    <td>Balance:</td>
                    <td>${selectedCustomer.balance}</td>
                </tr>
                <tr>
                    <td>Billing Address:</td>
                    <td>${selectedCustomer.billingAddress}</td>
                </tr>
                <tr>
                    <td>Activated Date:</td>
                    <td>${selectedCustomer.activatedDate}</td>
                </tr>
                <tr>
                    <td>Deactivated Date:</td>
                    <td>${selectedCustomer.deactivatedDate}</td>
                </tr>
                <tr>
                    <td>Update Customer:</td>
                    <td>
                        <%--<button onclick="location.href='/customer/${selectedCustomer.phoneNumber}'">Info</button>--%>
                        <button onclick="location.href='/all/customers/${selectedCustomer.phoneNumber}/update'"
                                class="btn btn-primary btn-md">Update
                        </button>
                        <%--<button onclick="location.href='/all/customers/${selectedCustomer.phoneNumber}/delete'" class="btn btn-danger">Delete</button>--%>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="panel panel-default">
                <div class="panel-heading">
                    <%--<h4>Panel with services</h4>--%>
                        <strong>Panel with services</strong>
                </div>
                <div class="panel-body">
                    <%--Panel Content--%>
                    <table id="rounded-corner" class="table table-striped">
                        <thead>
                        <tr>
                            <th>Service</th>
                            <th>Payroll</th>
                            <th>Service Status</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <div id="auto-row">
                            <%--<table class="table table-striped" >--%>
                            <tbody id="complete-table">

                            </tbody>
                            <%--</table>--%>
                        </div>
                    </table>
                </div>
                <div class="panel-footer">
                    <%--Panel Footer--%>
                    <div class="form-group">
                        <div class="blockquote-reverse">
                            <label for="complete-field" class="control-label col-sm-4" >Select Service to Add:</label>
                        </div>
                        <div class="col-sm-4">
                            <select id="complete-field" class="form-control">
                                <c:forEach var="listItem" items="${serviceList}">
                                    <option value="${listItem.name}">${listItem.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <input type="submit" value="Add Service" onclick="doAddService()"
                               class="btn btn-primary btn-md">
                    </div>
                </div>
            </div>


        </div>
    </div>
</div>
</body>
</html>
