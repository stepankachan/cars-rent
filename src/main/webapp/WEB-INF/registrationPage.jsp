<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <meta name="_csrf" value="dummy" th:value="${_csrf.token}" />
    <link href="<c:url value="css/login.css"/>" rel="stylesheet">
    <link href="<c:url value="css/materialize.css"/>" rel="stylesheet">
    <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet">
    <title>Регистрация</title>
</head>
<body>
<div class="row" style="margin-bottom: 0 !important;">

    <div class="col s9">
        <img src="images/RegistrationBackground.jpg" width="100%" height="100%">
    </div>
    <div class="col s3">

        <div class="container">
            <div class="section" style="margin-bottom: -40px; margin-top: 30%">
                <h4 class="teal-text center-align" style="font-weight: bolder">Регистарция</h4>
            </div>
            <div class="section">
                <h6 class="grey-text center-align">Введите свои данные</h6>
            </div>
        </div>

        <div class="container">

            <form class="row" id="registerUser" name='registerUser' action="<c:url value='registerUser'/>" method='POST'>

                <div class="col s12">
                    <div class="input-field">
                        <input id="first_name" type="text" class="validate" name='first_name'>
                        <label for="first_name">Имя</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input id="last_name" type="text" class="validate" name='last_name'>
                        <label for="last_name">Фамилия</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input id="login" type="text" class="validate" name='login'>
                        <label for="login">Логин</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input id="email" type="email" class="validate" name='email'>
                        <label for="email">Почтовый адрес</label>
                    </div>
                </div>

                <div class="col s12">
                    <div class="input-field">
                        <input class='validate' type='password' name='password' id='pass'/>
                        <label for='pass'>Пароль</label>
                    </div>
                </div>

                <div class='col s12'>
                    <button type='submit' name="registerUser" value="submit" class='col s12 waves-effect waves-teal btn'
                            style="text-transform: none">
                        <i class="material-icons left">perm_identity</i>
                        Зарегистрироваться
                    </button>
                </div>
                <span class="error">${msg}</span>
                <input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"/>
            </form>

        </div>
        <div class="row footer-copyright copyright">
            <div class="row grey-text">© 2017 Home Assignment 'Cars Rent project'</div>
        </div>
    </div>

</div>

<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>

</body>
</html>
