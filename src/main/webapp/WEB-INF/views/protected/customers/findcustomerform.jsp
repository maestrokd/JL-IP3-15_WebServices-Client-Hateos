<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 22.12.2017
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <script type="text/javascript" src="../../resources/static/js/findcustomers.js"></script>
    <title>Customer Find Form</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="../../include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
        </div>

        <div class="col-sm-9">
            <sf:form class="form-horizontal" action="/protected/customers/find" modelAttribute="customerDtoForm">
                <div class="form-group">

                    <s:message code="property.enterPhoneNumber" var="enterPhoneNumber"/>

                    <div class="col-sm-offset-2 col-sm-4">
                        <sf:input class="form-control" path="phoneNumber" placeholder="${enterPhoneNumber}"
                                  id="complete-field" onkeyup="loadCustomers()" />
                        <sf:errors path="phoneNumber" class="text-danger"/>
                    </div>
                    <div >
                        <button type="submit" class="btn btn-default">Find Customers</button>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-4" id="completeDiv"></div>
                </div>

            </sf:form>



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
                        <tbody id="completeTable">

                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>
