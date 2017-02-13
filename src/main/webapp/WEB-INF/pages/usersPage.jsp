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
                <sec:authorize access="hasRole('ADMIN')">
                    <div class="fixed-action-btn toolbar">
                        <a class="waves-effect waves-light btn-floating blue-grey btn-large">
                            <i class="material-icons">edit</i>
                        </a>
                        <ul>
                            <li>
                                <a class="waves-effect waves-light btn normal-text" href="<c:url value='/newuser'/>">
                                    <i class="material-icons " >person_add</i>Создать
                                </a>
                            </li>
                            <li>
                                <a class="waves-effect waves-light btn normal-text"
                                   href="<c:url value='/edit-user-${selecteduser.ssoId}' />">
                                    <i class="material-icons ">mode_edit</i>
                                    Изменить
                                </a>
                            </li>
                            <li>
                                <a class="waves-effect waves-light btn normal-text"
                                   href="<c:url value='/delete-user-${selecteduser.ssoId}'/>">
                                    <i class="material-icons" >delete</i>
                                    Удалить
                                </a>
                            </li>
                            <li>
                                <a class="waves-effect waves-light btn normal-text"
                                   href="<c:url value='/user-requests-${selecteduser.ssoId}'/>">
                                <i class="material-icons ">description</i>
                                <c:if test="${selecteduser.notConfirmedRequests.size() > 0}">
                                    <span class="new badge user-badge"
                                          data-badge-caption="Новых">${selecteduser.notConfirmedRequests.size()}</span>
                                </c:if>
                                Заявки
                            </a></li>
                        </ul>
                    </div>

                </sec:authorize>
                <table class="table highlight tablesorter" id="users-table">
                    <thead>
                    <tr>
                        <th class="no-sorting"></th>
                        <th class="align-center">Логин</th>
                        <th class="align-center">Имя</th>
                        <th class="align-center">Фамилия</th>
                        <th class="align-center">Почта</th>
                        <th class="align-center">Роль</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td class="align-center">
                                <form id="infoForm">
                                    <input onchange="getUserDetails('${user.ssoId}')" type="checkbox"
                                           <c:if test="${selecteduser.ssoId.equals(user.ssoId)}">checked</c:if>
                                           id="${user.ssoId}" name="${user.ssoId}" value="${user.ssoId}" class="filled-in"/>
                                    <label for="${user.ssoId}"></label>
                                </form>
                            </td>
                            <td class="align-center">${user.ssoId} </td>
                            <td class="align-center">${user.firstName}</td>
                            <td class="align-center">${user.lastName}</td>
                            <td class="align-center">${user.email}</td>
                            <td class="align-center">
                                <c:if test="${user.admin.equals(true)}">Администратор</c:if>
                                <c:if test="${user.admin.equals(false)}">Пользователь</c:if>
                            </td>
                        </tr>

                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="col s4">
            <div class="col s12">
                <h4 class="blue-grey-text center-align bolder" style="padding-bottom: 10px">Последняя активность
                    - ${selecteduser.ssoId}</h4>
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