<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Журнал</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <%@include file="util/styles.jsp" %>
</head>
<body>
<div class="row">
    <%@include file="widgets/authheader.jsp" %>
    <div class="col s12 no-padding">

        <div class="col s12">
            <h4 class="blue-grey-text center-align bolder">Журнал активности</h4>
        </div>

        <div class="col s12">
            <table class="table highlight tablesorter" id="requests-table">
                <thead>
                <tr>
                    <th class="align-center">Пользователь</th>
                    <th class="align-center">Действие</th>
                    <th class="align-center">Время</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${activities}" var="activity">
                    <tr>
                        <td class="align-center">${activity.user.ssoId}</td>
                        <td class="align-center">${activity.activity}</td>
                        <td class="align-center">${activity.formattedTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</div>

<%@include file="util/scripts.jsp" %>
</body>
</html>
