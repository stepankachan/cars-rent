<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Заявки</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <%@include file="util/styles.jsp" %>
</head>
<body>

<div class="row">

    <%@include file="widgets/authheader.jsp" %>
    <div class="col s12 no-padding">

        <div class="col s12">
            <h4 class="blue-grey-text center-align bolder">
                <sec:authorize access="hasRole('USER')">
                    Мои заявки
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    Заявки пользователей
                </sec:authorize>
            </h4>
        </div>
        <div class="col s12">
            <table class="table highlight tablesorter" id="requests-table">
                <thead>
                <tr>
                    <th class="align-center">Пользователь</th>
                    <th class="align-center">Автомобиль</th>
                    <th class="align-center">Начало аренды</th>
                    <th class="align-center">Конец аренды</th>
                    <th class="align-center">Подтверждено</th>
                    <sec:authorize access="hasRole('ADMIN')">
                        <th class="no-sorting"></th>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requests}" var="request">
                    <sec:authorize access="hasRole('USER')">
                        <c:if test="${loggedinuser.ssoId.equals(request.user.ssoId)}">
                            <tr>
                                <td class="align-center">${request.user.ssoId}</td>
                                <td class="align-center">${request.car.name}</td>
                                <td class="align-center">${request.fromDate}</td>
                                <td class="align-center">${request.toDate}</td>
                                <td class="align-center">
                                    <c:if test="${request.confirmed.equals(true)}">
                                        <i class="material-icons ">done</i>
                                    </c:if>
                                    <c:if test="${request.confirmed.equals(false)}">
                                        <i class="material-icons ">remove</i>
                                    </c:if>
                                </td>
                                <sec:authorize access="hasRole('ADMIN')">
                                    <td style="width: 2%">
                                        <a class="waves-effect waves-teal btn-flat btn-block" style="margin-top: 5px"
                                           href="#request-${request.id}">
                                            <i class="large material-icons">search</i>
                                        </a>
                                    </td>
                                </sec:authorize>
                            </tr>
                        </c:if>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <tr>
                            <td class="align-center">${request.user.ssoId}</td>
                            <td class="align-center">${request.car.name}</td>
                            <td class="align-center">${request.fromDate}</td>
                            <td class="align-center">${request.toDate}</td>
                            <td class="align-center">
                                <c:if test="${request.confirmed.equals(true)}">
                                    <i class="material-icons ">done</i>
                                </c:if>
                                <c:if test="${request.confirmed.equals(false)}">
                                    <i class="material-icons ">remove</i>
                                </c:if>
                            </td>
                            <td style="width: 2%">
                                <a class="waves-effect waves-teal btn-flat btn-block" style="margin-top: 5px"
                                   href="#request-${request.id}">
                                    <i class="large material-icons">search</i>
                                </a>
                            </td>
                        </tr>
                    </sec:authorize>
                </c:forEach>
                </tbody>
            </table>
            <c:forEach items="${requests}" var="request">
                <form:form method="GET" action="/edit-request-${request.id}">
                    <div id="request-${request.id}" class="modal" style="max-height: 100%">
                        <div class="modal-content row">
                            <h4>${request.user.ssoId}</h4>
                            <%@include file="widgets/calendar.jsp" %>
                            <input value="Подтвердить" type="submit"
                                   class="modal-action modal-close waves-effect waves-teal btn-flat right">
                        </div>
                    </div>
                </form:form>
            </c:forEach>
        </div>

    </div>
</div>
<%@include file="util/scripts.jsp" %>
</body>
</html>
