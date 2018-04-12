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
    <title>Customer Update Form</title>
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

            <c:if test="${not empty messageError}">
                <div class="alert alert-danger alert-dismissable">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <strong>Danger!</strong> ${messageError}.
                </div>
            </c:if>

            <%--<c:url value="/all/customers/update" var="customerUpdateUrl"/>--%>
            <sf:form class="form-horizontal" action="/all/customers/update" modelAttribute="customerDtoForm">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phoneNumber">
                        <s:message code="property.enterPhoneNumber"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="phoneNumber" placeholder="phoneNumber" readonly="true"/>
                        <sf:errors path="phoneNumber" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="customerStatusName">
                        <s:message code="property.enterCustomerStatus"/>
                    </label>
                    <div class="col-sm-4">
                            <%--<sf:input class="form-control" path ="phoneNumber" placeholder="phoneNumber"/>--%>
                        <sf:select class="form-control" path="customerStatusName">
                            <c:forEach var="listItem" items="${customerStatusList}">
                                <sf:option value="${listItem.name}">${listItem.name}</sf:option>
                            </c:forEach>
                        </sf:select>
                        <sf:errors path="customerStatusName" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="balance">
                        <s:message code="property.enterCustomerBalance"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="balance" placeholder="balance" readonly="true"/>
                        <sf:errors path="balance" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="balance">
                        <s:message code="property.enterCustomerBillingAddress"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="billingAddress" placeholder="billingAddress"/>
                        <sf:errors path="billingAddress" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="userLogin">
                        <s:message code="property.enterUserLoginForCustomer"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="userLogin" placeholder="User Login"/>
                        <sf:errors path="userLogin" class="text-danger"/>
                    </div>
                </div>

                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default">Update</button>
                </div>
            </sf:form>
        </div>
    </div>
</div>
</body>
</html>
