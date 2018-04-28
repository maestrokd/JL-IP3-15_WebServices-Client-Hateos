<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: maestro
  Date: 20.12.2017
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Login</title>
    <style>
        .error {
            color: #ff0000;
        }
    </style>
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

            <sf:form class="form-horizontal" action="/all/users/${updateUser.login}/update" modelAttribute="updateUser">

                <div class="form-group">
                    <label class="control-label col-sm-2" for="login">
                        <s:message code="property.enterYourLogin"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="login" value="${sessionScope.updateUser.login} "
                                  placeholder="YourLogin" pattern="[A-Za-z0-9]{4,}" readonly="true"/>
                        <sf:errors path="login" cssClass="error"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="password">
                        <s:message code="property.enterYourPassword"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:password class="form-control" path="password"
                                     placeholder="YourPassword"
                                     pattern="[A-Za-z0-9]{4,}"/>
                        <sf:errors path="password" cssClass="error"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="name">
                        <s:message code="property.enterYourName"/>
                    </label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="name" value="${sessionScope.updateUser.name}"
                                  placeholder="YourName" pattern="[A-Za-z0-9]{4,}"/>
                        <sf:errors path="name" cssClass="error"/>
                    </div>
                </div>

                <c:if test="${!empty roleList}">
                    <div class="form-group">
                        <label class="control-label col-sm-2" for="roleList">
                            <s:message code="property.enterYourName"/>
                        </label>
                        <div class="col-sm-6">
                            <label><sf:checkboxes path="roleList" items="${roleList}" itemLabel="name"/></label>
                        </div>
                    </div>
                    <%--<sf:checkboxes path="roleList" items="${roleList}" itemLabel="name" />--%>
                </c:if>

                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default">Update</button>
                </div>
            </sf:form>

        </div>
    </div>
</div>
</body>
</html>
