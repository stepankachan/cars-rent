<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Aренда машин</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <%@include file="util/styles.jsp" %>
</head>

<body>

<div class="row">
    <%@include file="widgets/authheader.jsp" %>
    <div class="col s12 no-padding">
        <div class="col s8 no-padding">
            <div class="col s12 ">
                <h4 class="blue-grey-text center-align bolder">Список пользователей</h4>
            </div>
            <div class="col s12">
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
                    <sec:authorize access="hasRole('ADMIN')">
                        <tr style="background-color: transparent">
                            <td colspan="6" class="row">
                                <div class="col s12 m6 l3">
                                    <a class="waves-effect waves-light btn full-width blue-grey"
                                       href="<c:url value='/newuser'/>">
                                        <i class="material-icons left">person_add</i>
                                        Создать
                                    </a>
                                </div>
                                <div class="col s12 m6 l3">
                                    <a class="waves-effect waves-light btn full-width blue-grey"
                                       href="<c:url value='/edit-user-${selecteduser.ssoId}' />">
                                        <i class="material-icons left">mode_edit</i>
                                        Изменить
                                    </a>
                                </div>
                                <div class="col s12 m6 l3">
                                    <a class="waves-effect waves-light btn full-width blue-grey"
                                       href="<c:url value='/delete-user-${selecteduser.ssoId}'/>">
                                        <i class="material-icons left">delete</i>
                                        Удалить
                                    </a>
                                </div>
                                <div class="col s12 m6 l3">
                                    <a class="waves-effect waves-light btn full-width blue-grey"
                                       href="<c:url value='/edit-user-${selecteduser.ssoId}'/>">
                                        <i class="material-icons left">delete</i>
                                        Заявки
                                    </a>
                                </div>
                            </td>
                        </tr>
                    </sec:authorize>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col s4">
            <div class="col s12">
                <h4 class="blue-grey-text center-align bolder">Последняя активность - ${selecteduser.ssoId}</h4>
            </div>
            <div class="col s12">
                <table class="table highlight">
                    <thead>
                    <tr>
                        <th class="align-center">Время</th>
                        <th class="align-center">Действие</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userActivities}" var="activity">
                        <tr>
                            <td class="align-center">${activity.formattedTime}</td>
                            <td class="align-center">${activity.activity}</td>
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