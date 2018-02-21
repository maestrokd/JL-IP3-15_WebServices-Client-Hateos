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
                <button onclick="location.href='/all/payments/add/${selectedCustomer.phoneNumber}'" class="btn btn-primary">Create payment</button>
            </div>

            <div class="col-sm-9">

                <c:if test="${not empty message}">
                    <strong>${message}</strong>
                </c:if>

                 <table class="table table-striped">
                     <%--<caption>Selected Customer ${selectedCustomer.phoneNumber}:</caption>--%>
                     <h2>Selected Customer ${selectedCustomer.phoneNumber}:</h2>
                     <thead>
                         <tr>
                             <th>Phone number</th>
                             <th>Customer Status</th>
                             <th>Balance</th>
                             <th>Action</th>
                         </tr>
                     </thead>
                     <tbody>
                         <tr>
                             <td>${selectedCustomer.phoneNumber}</td>
                             <td>${selectedCustomer.customerStatus.name}</td>
                             <td>${selectedCustomer.balance}</td>
                             <td>
                                 <%--<button onclick="location.href='/customer/${selectedCustomer.phoneNumber}'">Info</button>--%>
                                 <button onclick="location.href='/all/customers/${selectedCustomer.phoneNumber}/update'" class="btn btn-primary">Update</button>
                                 <%--<button onclick="location.href='/all/customers/${selectedCustomer.phoneNumber}/delete'" class="btn btn-danger">Delete</button>--%>
                             </td>
                         </tr>
                     </tbody>
                 </table>

                <table class="table table-striped">
                    <tr>
                        <td>
                            <label for="complete-field">Select Service for Phone Number</label>
                        </td>
                        <td>
                            <%--<input id="selected-phone-number" value="${selectedCustomer.phoneNumber}" />--%>
                            <div id="selected-phone-number" >${selectedCustomer.phoneNumber}</div>
                        </td>
                        <td>
                            <select id="complete-field">
                                <c:forEach var="listItem" items="${serviceList}">
                                    <option value="${listItem.name}">${listItem.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="submit" value="Add Service" onclick="doAddService()" >
                        </td>
                        <td>
                            <input type="submit" value="Show Services" onclick="doShowServices()" >
                        </td>
                    </tr>
                </table>

                <table id="rounded-corner" class="table table-striped">
                    <%--<caption>Services for ${selectedCustomer.phoneNumber}</caption>--%>
                    <h2>Services for ${selectedCustomer.phoneNumber}:</h2>
                    <thead>
                        <tr>
                            <th>Service</th>
                            <th>Payroll</th>
                            <th>Customer Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                <div id="auto-row" >
                    <%--<table class="table table-striped" >--%>
                        <tbody id="complete-table">

                        </tbody>
                    <%--</table>--%>
                </div>
                </table>

            </div>
    </div>
</body>
</html>
