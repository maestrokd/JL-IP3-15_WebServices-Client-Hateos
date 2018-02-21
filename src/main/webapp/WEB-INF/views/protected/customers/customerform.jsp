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
</head>
<body>
    <table>
        <tr>
            <td><a href="/userlogin">|Return to home|</a></td>
            <td>|Selected ${selectedCustomer.phoneNumber}|</td>
            <td>|Hello ${sessionScope.loginUser.login}|</td>
            <td><a href="/logout">|Logout|</a></td>
        </tr>
    </table>
    <br>
    <br>
    <sf:form action="/protected/customers/add" modelAttribute="customerForm" >
        <table>
            <tr>
                <td>
                    <label for="phoneNumber">
                        <s:message code="property.enterPhoneNumber"/>
                    </label>
                </td>
                <td>
                    <sf:input path="phoneNumber" placeholder="phoneNumber" />
                    <sf:errors path="phoneNumber" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="customerStatus">
                        <s:message code="property.enterCustomerStatus"/>
                    </label>
                </td>
                <%--<td>--%>
                    <%--&lt;%&ndash;<sf:input path="status" placeholder="status" />&ndash;%&gt;--%>
                    <%--<sf:errors path="customerStatus" cssClass="error"/>--%>
                <%--</td>--%>
                <td>
                        <sf:select path="customerStatus">
                        <c:forEach var="listItem" items="${customerStatusList}">
                        <%--<sf:options items="${customerList}" />--%>
                        <sf:option value="${listItem}">${listItem.name}</sf:option>
                        </c:forEach>
                        </sf:select>
                    <sf:errors path="customerStatus.name" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="balance">
                        <s:message code="property.enterCustomerBalance"/>
                    </label>
                </td>
                <td>
                    <sf:input path="balance" placeholder="balance" />
                    <sf:errors path="balance" cssClass="error"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="user.login">
                    <%--<label for="user">--%>
                        <s:message code="property.enterUserLoginForCustomer"/>
                    </label>
                </td>
                <td>
                    <sf:input path="user.login" placeholder="User Login" />
                    <%--<sf:input path="user.login" value="${sessionScope.loginUser.login}"/>--%>
                        <%--<sf:input path="user" value="${sessionScope.loginUser.login}" />--%>
                    <sf:errors path="user.login" cssClass="error"/>
                    <%--<sf:errors path="user" cssClass="error"/>--%>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" name="Send"/>
                </td>
            </tr>

        </table>
        <%--<sf:hidden path="user"/>--%>
        <%--<sf:hidden path="customerServiceList"/>--%>
        <%--<sf:hidden path="eventList"/>--%>
        <%--<sf:hidden path="billList"/>--%>
        <%--<sf:hidden path="paymentList"/>--%>

        <%--<sf:input path="user" placeholder="user" />--%>

    </sf:form>

</body>
</html>
