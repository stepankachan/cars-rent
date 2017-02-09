<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Aренда машин</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <link href="<c:url value="css/login.css"/>" rel="stylesheet">
    <link href="<c:url value="css/materialize.css"/>" rel="stylesheet">
    <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet">
</head>
<body>

<div class="row" style="margin-bottom: 0 !important;">
    <div class="col s9">
        <img src="images/LoginBackground.jpg" width="100%" height="100%">
    </div>

    <div class="col s3">
        <div class="row" style="float: right; padding-right: 20px">
            <div class="col s12">
                <a style="width:100%; text-transform: none" class="waves-effect waves-teal btn-flat btn col s6"
                   href="${pageContext.request.contextPath}/registration"> <i
                        class="material-icons right">perm_identity</i>Регистрация</a>
            </div>
        </div>
        <c:if test="${not empty error}">
            <div class="col s11">
                <i class="small material-icons deep-orange-text" style="float: right">error</i>
                <h6 class="deep-orange-text" style="text-align: right">${error}</h6>
            </div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="col s11">
                <i class="small material-icons deep-orange-text" style="float: right">error</i>
                <h6 class="deep-orange-text" style="text-align: right">${message}</h6>
            </div>
        </c:if>

        <div class="container">
            <div class="section" style="margin-bottom: -40px; margin-top: 30%">
                <h4 class="teal-text center-align" style="font-weight: bolder">Добро пожаловать</h4>
            </div>
            <div class="section">
                <h6 class="grey-text center-align">Введите свой логин и пароль</h6>
            </div>
        </div>

        <div class="container">

            <form class="row" id="login" name='login' action="<c:url value='/loginPage'/>" method='POST'>

                <div class="col s12">
                    <div class="input-field">
                        <input id="name" type="text" class="validate" name='username'>
                        <label for="name">Логин</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input class='validate' type='password' name='password' id='pass'/>
                        <label for='pass'>Пароль</label>
                    </div>
                </div>

                <div class='col s12'>
                    <button type='submit' name="submit" value="submit" class='col s12 waves-effect waves-teal btn'
                            style="text-transform: none">
                        <i class="material-icons left">lock_open</i>
                        Войти
                    </button>
                </div>

                <div class="col s12">
                    <div class="checkbox">
                        <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
                    </div>
                </div>

                <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"/>
            </form>
        </div>
        <div class="row footer-copyright copyright">
            <div class="row grey-text">© 2017 Home Assignment 'Cars Rent project'</div>
        </div>
    </div>
</div>

<%@include file="util/scripts.jsp" %>

</body>
</html>