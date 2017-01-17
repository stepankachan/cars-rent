<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Заявки</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link href="<c:url value="/static/css/login.css"/>" rel="stylesheet">
    <link href="<c:url value="/static/css/materialize.css"/>" rel="stylesheet">
    <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet">
</head>
<body>

<div class="row">

    <%@include file="authheader.jsp" %>
    <div class="col s12 no-padding">
        <div class="col s8 no-padding">
            <div class="col s12 col-border">
                <h5 class="black-text left-align bolder">Заявки пользователей</h5>
            </div>
            <div class="col s12 col-border no-top-border">
                <table class="table highlight">
                    <thead>
                    <tr>
                        <th></th>
                        <th>Имя</th>
                        <th>Фамилия</th>
                        <th>Почта</th>
                        <th>Логин</th>
                        <th>Роль</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>
                                <form id="infoForm">
                                    <input onchange="getUserDetails('${user.ssoId}')" type="checkbox"
                                           <c:if test="${selecteduser.ssoId.equals(user.ssoId)}">checked</c:if>
                                           id="${user.ssoId}" name="${user.ssoId}" value="${user.ssoId}"
                                           class="filled-in"/>
                                    <label for="${user.ssoId}"></label>
                                </form>
                            </td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.ssoId}</td>
                            <td>
                                <c:if test="${user.admin.equals(true)}">Пользователь</c:if>
                                <c:if test="${user.admin.equals(false)}">Администратор</c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
    </div>
</div>

</body>
</html>
