<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <%@include file="util/styles.jsp" %>
</head>

<body>

<div class="row" style="margin-bottom: 0 !important;">
    <div class="col s9">
        <img src="/static/images/LoginBackground.jpg" width="100%" height="100%">
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

            <form class="row" id="login" name='login' action="<c:url value='/login'/>" method='POST'>

                <div class="col s12">
                    <div class="input-field">
                        <input id="username" name="ssoId" type="text" class="validate">
                        <label for="username">Логин</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input class='validate' id="password" name="password" type="password"/>
                        <label for='password'>Пароль</label>
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
                    <p>
                        <input type="checkbox" id="rememberme" name="remember-me" onclick="show()"/>
                        <label for="rememberme">Запомнить</label>
                    </p>
                </div>
                <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"/>
            </form>
        </div>
        <div class="row footer-copyright copyright">
            <div class="row grey-text">© 2017 Home Assignment 'Cars Rent project'</div>
        </div>
    </div>
</div>
<script>
    function show() {
        Materialize.toast('I am a toast', 4000,'',function(){alert('Your toast was dismissed')})
    }
</script>
<%@include file="util/scripts.jsp" %>
</body>
</html>