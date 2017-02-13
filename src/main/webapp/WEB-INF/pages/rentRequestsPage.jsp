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
                    <sec:authorize access="hasRole('ADMIN')">
                        <th class="no-sorting request-status-icon"></th>
                    </sec:authorize>
                    <th class="align-center">Пользователь</th>
                    <th class="align-center">Автомобиль</th>
                    <th class="align-center">Начало аренды</th>
                    <th class="align-center">Конец аренды</th>
                    <th class="align-center">Описание</th>
                    <th class="align-center">Статус</th>
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
                                <td class="align-center">${request.description}</td>
                                <td class="align-center">
                                    <c:choose>
                                        <c:when test="${request.state == 'CONFIRMED'}">
                                            <i class="material-icons ">done</i>
                                        </c:when>
                                        <c:when test="${request.state == 'DISCARDED'}">
                                            <i class="material-icons ">not_interested</i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="material-icons ">schedule</i>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                            </tr>
                        </c:if>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ADMIN')">
                        <tr>
                            <sec:authorize access="hasRole('ADMIN')">
                                <td style="width: 2%">
                                    <a class="waves-effect waves-teal btn-flat btn-block" style="margin-top: 5px"
                                       href="#request-${request.id}">
                                        <i class="large material-icons request-status-icon">search</i>
                                    </a>
                                </td>
                            </sec:authorize>
                            <td class="align-center">${request.user.ssoId}</td>
                            <td class="align-center">${request.car.name}</td>
                            <td class="align-center">${request.fromDate}</td>
                            <td class="align-center">${request.toDate}</td>
                            <td class="align-center">${request.description}</td>
                            <td class="align-center">
                                <c:choose>
                                    <c:when test="${request.state == 'CONFIRMED'}">
                                        <i class="material-icons green-text request-status-icon">done</i>
                                    </c:when>
                                    <c:when test="${request.state == 'DISCARDED'}">
                                        <i class="material-icons red-text request-status-icon">not_interested</i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="material-icons grey-text request-status-icon">schedule</i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </sec:authorize>
                </c:forEach>
                </tbody>
            </table>
            <c:forEach items="${requests}" var="request">
                <form name="request-form-${request.id}" class="information" method="GET" action="/discard-request-${request.id}">
                    <div id="request-${request.id}" class="modal" style="max-height: 100%">
                        <div class="modal-content">
                            <h4>Заявка пользователя ${request.user.ssoId}</h4>
                            <div class="row">
                                <div class="col s5">
                                    <img class="materialboxed" data-caption=""
                                         width="400px" height="370px" src="${request.car.imageURL}">
                                </div>
                                <div class="col s7">
                                    <div class="input-field col s12">
                                        <i class="material-icons prefix">account_circle</i>
                                        <h5 style="margin-left: 40px; margin-top: 2px">
                                                ${request.user.firstName} ${request.user.lastName}
                                        </h5>
                                    </div>
                                    <div class="input-field col s12">
                                        <input id="request-from" type="text" value="${request.fromDate}"/>
                                        <label for="request-from">Началo аренды</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="text" id="request-to" value="${request.toDate}"/>
                                        <label for="request-to">Окончания аренды</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <textarea name="comment" id="comment" class="materialize-textarea validate" required="" aria-required="true"></textarea>
                                        <label for="comment">Комментарий</label>
                                    </div>
                                </div>
                                <input value="Отказать" type="submit" class="right button-text-center white-text waves-effect waves-teal btn-flat red">
                                <a class="waves-effect waves-light btn right" href="<c:url value='/edit-request-${request.id}' />">Одобрить</a>
                            </div>
                        </div>
                    </div>
                </form>
            </c:forEach>
        </div>

    </div>
</div>
<%@include file="util/scripts.jsp" %>
</body>
</html>
