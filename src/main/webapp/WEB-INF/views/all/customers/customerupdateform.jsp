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
    <title>Customer Form</title>
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
                <%--<c:url value="/all/customers/update" var="customerUpdateUrl"/>--%>
                <sf:form class="form-horizontal" action="/all/customers/update" modelAttribute="customerForm" >
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="phoneNumber">
                            <s:message code="property.enterPhoneNumber"/>
                        </label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path ="phoneNumber" placeholder="phoneNumber"/>
                            <sf:errors path="phoneNumber" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="customerStatus">
                            <s:message code="property.enterCustomerStatus"/>
                        </label>
                        <div class="col-sm-4">
                        <%--<sf:input class="form-control" path ="phoneNumber" placeholder="phoneNumber"/>--%>
                            <sf:select class="form-control" path="customerStatus">
                                <c:forEach var="listItem" items="${customerStatusList}">
                                    <sf:option value="${listItem}">${listItem.name}</sf:option>
                                </c:forEach>
                            </sf:select>
                            <sf:errors path="customerStatus" cssClass="error"/>
                        </div>
                    </div>

                    <%--<table>--%>
                            <%--<tr>--%>
                            <%--<td>--%>
                            <%--<label for="customerStatus">--%>
                            <%--<s:message code="property.enterCustomerStatus"/>--%>
                            <%--</label>--%>
                            <%--</td>                --%>
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
                    <%--</table>--%>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="balance">
                            <s:message code="property.enterCustomerBalance"/>
                        </label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path ="balance" placeholder="balance"/>
                            <sf:errors path="balance" cssClass="error"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="user.login">
                            <s:message code="property.enterUserLoginForCustomer"/>
                        </label>
                        <div class="col-sm-4">
                            <sf:input class="form-control" path ="user.login" placeholder="User Login"/>
                            <sf:errors path="user.login" cssClass="error"/>
                        </div>
                    </div>

                    <div class="col-sm-offset-2 col-sm-4">
                        <button type="submit" class="btn btn-default">Update</button>
                    </div>
                </sf:form>
            </div>
    </div>
</body>
</html>
