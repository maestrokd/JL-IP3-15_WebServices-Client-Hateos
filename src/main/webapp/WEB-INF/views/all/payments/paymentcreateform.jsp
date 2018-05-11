<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration jsp</title>
    <%--<style>--%>
        <%--.error{--%>
            <%--color:#ff0000;--%>
        <%--}--%>
    <%--</style>--%>
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
                <sf:form class="form-horizontal" action="/all/payments/add" modelAttribute="paymentForm" >
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="customer.phoneNumber">Enter phone number:</label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path="customer.phoneNumber" value="${phoneNumber}" readonly="true" />
                            <sf:errors path="customer.phoneNumber" class="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                    <label class="control-label col-sm-2" for="channel">Enter channel:</label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path ="channel" placeholder="Channel" />
                            <sf:errors path="channel" class="text-danger"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="amount">Enter amount:</label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path ="amount" placeholder="Amount" />
                            <sf:errors path="amount" class="text-danger"/>
                        </div>
                    </div>

                    <div class="col-sm-offset-2 col-sm-4">
                        <%--<input type="submit" name="property.sendToRegister"/>--%>
                        <button type="submit" class="btn btn-default" >Pay</button>
                    </div>
                </sf:form>
            </div>
        </div>
    </div>
</body>
</html>
