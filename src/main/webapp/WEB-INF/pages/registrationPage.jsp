<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <title>Регистрация</title>
    <%@include file="util/styles.jsp" %>
</head>
<body>

<div class="row" style="margin-bottom: 0 !important;">

    <c:if test="${edit}">
        <%@include file="widgets/authheader.jsp" %>
    </c:if>
    <div class="col s9">
        <img src="static/images/RegistrationBackground.jpg" width="100%" height="100%">
    </div>
    <div class="col s3">

        <div class="container">
            <div class="section" style="margin-bottom: -40px; margin-top: 30%">
                <c:choose>
                    <c:when test="${edit}">
                        <h4 class="teal-text center-align" style="font-weight: bolder">Регистарция</h4>
                    </c:when>
                    <c:otherwise>
                        <h4 class="teal-text center-align" style="font-weight: bolder">Редактировать</h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="section">
                <h6 class="grey-text center-align">Введите свои данные</h6>
            </div>
        </div>

        <div class="container">
            <form:form method="POST" modelAttribute="user" class="form-horizontal">
                <form:input type="hidden" path="id" id="id"/>

                <div class="col s12">
                    <div class="input-field">
                        <form:input path="firstName" id="firstName" class="validate"/>
                        <label for="firstName">Имя</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <form:input path="lastName" id="lastName" type="text" class="validate"/>
                        <label for="lastName">Фамилия</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="col-md-7">
                        <c:choose>
                            <c:when test="${edit}">
                                <div class="input-field">
                                    <form:input type="text" path="ssoId" id="ssoId" class="validate" disabled="true"/>
                                    <label for="ssoId">Логин</label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="input-field">
                                    <form:input type="text" path="ssoId" id="ssoId" class="validate"/>
                                    <label for="ssoId">Логин</label>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <form:input class='validate' type="password" path="password" id="password"/>
                        <label for='password'>Пароль</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <form:input path="email" id="email" type="email" class="validate"/>
                        <label for="email">Почтовый адрес</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col s12">
                        <label class="col s3 control-lable" for="userRoles">Roles</label>
                        <div class="col s11 select-dropdown">
                            <form:select path="userRoles" items="${roles}" multiple="false" itemValue="id"
                                         itemLabel="type" class="initialized"/>
                        </div>
                    </div>
                </div>

                <div class='col s12'>
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Изменить" class="col s6 waves-effect waves-teal btn button-text-center"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Регистрация" class="col s6 waves-effect waves-teal btn button-text-center"/>
                        </c:otherwise>
                    </c:choose>
                    <a class="waves-effect waves-teal btn-flat btn col s6" href="<c:url value='/list' />">Отменить</a>
                </div>
            </form:form>
        </div>
    </div>
</div>

<%@include file="util/scripts.jsp" %>
</body>
</html>
