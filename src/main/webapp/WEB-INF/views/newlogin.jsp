<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>New login</title>
    <%--<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/base.css" />">--%>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%@ include file="include/headnav.jsp" %>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3">
        </div>

        <div class="col-sm-9">

            <c:url value="/newlogin" var="loginUrl"/>
            <form class="form-horizontal" action="${loginUrl}" method="post">
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Danger!</strong> <s:message code="message.invalidUsernameAndPassword"/>
                    </div>
                </c:if>

                <c:if test="${param.logout != null}">
                    <div class="alert alert-info alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Info!</strong> <s:message code="message.youHaveBeenLoggedOut"/>
                    </div>
                </c:if>

                <c:if test="${not empty customMessage}">
                    <div class="alert alert-info alert-dismissable">
                        <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                        <strong>Info!</strong> ${customMessage}.
                    </div>
                </c:if>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="username"><s:message
                            code="property.enterYourLogin"/></label>
                    <div class="col-sm-4">
                        <input type="text" class="form-control" id="username" name="username"/>
                    </div>
                </div>
                <%--<br>--%>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="password"><s:message
                            code="property.enterYourPassword"/></label>
                    <div class="col-sm-4">
                        <input type="password" class="form-control" id="password" name="password"/>
                    </div>
                </div>
                <input type="hidden"
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-default"><s:message code="property.button.login"/></button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
