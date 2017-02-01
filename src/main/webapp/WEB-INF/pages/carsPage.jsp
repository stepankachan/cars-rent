<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <head>
        <title>Автомобили</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
        <link href="<c:url value="/static/css/login.css"/>" rel="stylesheet">
        <link href="<c:url value="/static/css/materialize.css"/>" rel="stylesheet">
        <link href="<c:url value="https://fonts.googleapis.com/icon?family=Material+Icons"/>" rel="stylesheet">
    </head>
</head>
<body>

<%@include file="authheader.jsp" %>
<div class="row">
    <div class="section">
        <h4 class="teal-text center-align" style="font-weight: bolder">Автомобили</h4>
    </div>

        <c:forEach items="${cars}" var="car">

            <div class="col s6 m6">
                <div class="card">
                    <div class="card-image">
                        <img src="${car.imageURL}">
                        <span class="card-title white-text text-darken-3">${car.name}</span>
                        <a class="waves-effect waves-light btn right" style="margin-top: 5px"><i class="material-icons left">search</i>Забронировать</a>
                    </div>
                    <div class="card-content">
                        <h6>Цена (сутки) : ${car.price}$</h6>
                        <h6>Количество пасажиров : ${car.passengerCount}</h6>
                        <h6>Дата выпуска : ${car.releaseDate}</h6>
                        <h6>Трансмиссия : ${car.transmission}</h6>
                        <h6>Топливо : ${car.fuel}</h6>
                        <h6>Обьем двигателя : ${car.engineCapacity}</h6>
                    </div>
                </div>
            </div>


    </c:forEach>
</div>

<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/materialize.js"></script>
<script src="/static/js/init.js"></script>
</body>
</html>
