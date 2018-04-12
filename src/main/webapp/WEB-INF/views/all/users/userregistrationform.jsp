<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Registration jsp</title>
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

            <sf:form class="form-horizontal" action="/userregistration" modelAttribute="userDto">
                <div class="form-group">
                    <label class="control-label col-sm-2" for="login"><s:message
                            code="property.enterYourLogin"/></label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="login" placeholder="YourLogin" pattern="[A-Za-z0-9]{4,}"/>
                        <sf:errors path="login" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="password"><s:message
                            code="property.enterYourPassword"/></label>
                    <div class="col-sm-4">
                        <sf:password class="form-control" path="password" placeholder="YourPassword"
                                     pattern="[A-Za-z0-9]{4,}"/>
                        <sf:errors path="password" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="name"><s:message code="property.enterYourName"/></label>
                    <div class="col-sm-4">
                        <sf:input class="form-control" path="name" placeholder="YourName"/>
                        <sf:errors path="name" class="text-danger"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="email"><s:message
                            code="property.enterYourEmail"/></label>
                    <div class="col-sm-4">
                        <sf:input path="email" class="form-control" placeholder="YourEmail"/>
                        <sf:errors path="email" class="text-danger"/>
                            <%--<sf:errors path="email" class="text-danger" cssClass="error"/>--%>
                    </div>
                </div>

                <div class="col-sm-offset-2 col-sm-4">
                        <%--<input type="submit" name="property.sendToRegister"/>--%>
                    <button type="submit" class="btn btn-default"><s:message
                            code="property.button.sendToRegister"/></button>
                </div>
            </sf:form>
        </div>
    </div>
</div>
</body>
</html>
