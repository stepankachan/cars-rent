<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Aренда машин</title>
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
                <h5 class="black-text left-align bolder">Список пользователей</h5>
            </div>
            <div class="col s12 col-border no-top-border no-padding">
                <nav class="grey lighten-1">
                    <div class="nav-wrapper">
                        <ul class="left hide-on-med-and-down">
                            <sec:authorize access="hasRole('ADMIN')">
                                <li>
                                    <a style="height: 100%" href="<c:url value='/newuser' />">
                                        <i class="toolbar-icon material-icons">person_add</i>
                                        <span class="inner">Создать</span>
                                    </a>
                                </li>
                                <li>
                                    <a style="height: 100%" href="<c:url value='/edit-user-${selecteduser.ssoId}' />">
                                        <i class="toolbar-icon material-icons">mode_edit</i>
                                        <span class="inner">Редактировать</span>
                                    </a>
                                </li>
                                <li>
                                    <a style="height: 100%" href="<c:url value='/delete-user-${selecteduser.ssoId}' />">
                                        <i class="toolbar-icon material-icons">delete</i>
                                        <span class="inner">Удалить</span>
                                    </a>
                                </li>
                            </sec:authorize>
                        </ul>
                    </div>
                </nav>
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
        <div class="col s4">
            <div class="col s12">
                <h5 class="black-text left-align bolder" style="font-weight: bolder">Информация</h5>
            </div>
            <div class="col s12">
                <table class="table highlight">
                    <thead>
                    <tr>
                        <th>Номер Заявки</th>
                        <th>Описание</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userRequests}" var="request">
                        <tr>
                            <td>${request.id}</td>
                            <td>${request.description}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/materialize.js"></script>
<script src="/static/js/init.js"></script>
</body>
</html>