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
    <title>Customer Create Form</title>
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
            <sf:form class="form-horizontal" action="/protected/customers/add" modelAttribute="customerDtoForm">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="phoneNumber">
                        <s:message code="property.enterPhoneNumber"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="phoneNumber" placeholder="phoneNumber"/>
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
                    <button type="submit" class="btn btn-default">Create Customer</button>
                </div>
            </sf:form>


            <%--<sf:form action="/protected/customers/add" modelAttribute="customerForm" >--%>
            <%--<table>--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<label for="phoneNumber">--%>
            <%--<s:message code="property.enterPhoneNumber"/>--%>
            <%--</label>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<sf:input path="phoneNumber" placeholder="phoneNumber" />--%>
            <%--<sf:errors path="phoneNumber" cssClass="error"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<label for="customerStatus">--%>
            <%--<s:message code="property.enterCustomerStatus"/>--%>
            <%--</label>--%>
            <%--</td>--%>
            <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;&lt;%&ndash;<sf:input path="status" placeholder="status" />&ndash;%&gt;&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:errors path="customerStatus" cssClass="error"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--<td>--%>
            <%--<sf:select path="customerStatus">--%>
            <%--<c:forEach var="listItem" items="${customerStatusList}">--%>
            <%--&lt;%&ndash;<sf:options items="${customerList}" />&ndash;%&gt;--%>
            <%--<sf:option value="${listItem}">${listItem.name}</sf:option>--%>
            <%--</c:forEach>--%>
            <%--</sf:select>--%>
            <%--<sf:errors path="customerStatus.name" cssClass="error"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<label for="balance">--%>
            <%--<s:message code="property.enterCustomerBalance"/>--%>
            <%--</label>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<sf:input path="balance" placeholder="balance" />--%>
            <%--<sf:errors path="balance" cssClass="error"/>--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td>--%>
            <%--<label for="user.login">--%>
            <%--&lt;%&ndash;<label for="user">&ndash;%&gt;--%>
            <%--<s:message code="property.enterUserLoginForCustomer"/>--%>
            <%--</label>--%>
            <%--</td>--%>
            <%--<td>--%>
            <%--<sf:input path="user.login" placeholder="User Login" />--%>
            <%--&lt;%&ndash;<sf:input path="user.login" value="${sessionScope.loginUser.login}"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:input path="user" value="${sessionScope.loginUser.login}" />&ndash;%&gt;--%>
            <%--<sf:errors path="user.login" cssClass="error"/>--%>
            <%--&lt;%&ndash;<sf:errors path="user" cssClass="error"/>&ndash;%&gt;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td colspan="2">--%>
            <%--<input type="submit" name="Send"/>--%>
            <%--</td>--%>
            <%--</tr>--%>

            <%--</table>--%>
            <%--&lt;%&ndash;<sf:hidden path="user"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:hidden path="customerServiceSet"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:hidden path="eventList"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:hidden path="billList"/>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<sf:hidden path="paymentList"/>&ndash;%&gt;--%>

            <%--&lt;%&ndash;<sf:input path="user" placeholder="user" />&ndash;%&gt;--%>

            <%--</sf:form>--%>

        </div>
    </div>
</div>
</body>
</html>
