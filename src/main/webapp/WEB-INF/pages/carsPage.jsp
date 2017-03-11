<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Автомобили</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0"/>
    <%@include file="util/styles.jsp" %>
</head>
<body>

<%@include file="widgets/authheader.jsp" %>

<div class="row">
    <div class="col s12 no-pad-bot no-pad-top">
        <h4 class="blue-grey-text center-align bolder">Автомобили</h4>
    </div>

    <c:forEach items="${cars}" var="car">

        <div class="col s6 m6">
            <div class="card">
                <div class="card-image">
                    <img src="${car.imageURL}">
                    <span class="card-title white-text text-darken-3">${car.name}</span>
                    <a class="waves-effect waves-light btn right" style="margin-top: 5px" href="#${car.name}"><i
                            class="material-icons left">search</i>Забронировать</a>
                </div>
                <div class="card-content">
                    <h6>Цена (сутки) : ${car.price}$</h6>
                    <div class="row car-passenger-icon">
                        <div class="col">
                            <h6>Количество пасажиров :</h6>
                        </div>
                        <div class="col s6">
                           <c:forEach begin="1" end="${car.passengerCount}">
                               <i class="material-icons">person</i>
                           </c:forEach>
                        </div>
                    </div>
                    <h6>Дата выпуска : ${car.releaseDate}</h6>
                    <h6>Трансмиссия : ${car.transmission}</h6>
                    <h6>Топливо : ${car.fuel}</h6>
                    <h6>Обьем двигателя : ${car.engineCapacity}</h6>
                </div>
            </div>
        </div>
        <form:form method="GET" action="/rent-car-${car.carId}" id="car-form-${car.carId}">
            <div id="${car.name}" class="modal" style="max-height: 100%">
                <div class="modal-content row">
                    <h4>${car.name}</h4>
                    <div class="calendar-container">
                        <div class="col s8">
                            <div class="datepicker ll-skin-melon"></div>
                        </div>

                        <div class="col s4" style="margin-bottom: 75px">
                            <div class="input-field">
                                <input type="text" name="from-date" id="from-date" class="from-date-input"/>
                                <label for="from-date">Началo аренды</label>
                            </div>
                            <div class="input-field">
                                <input type="text" name="to-date" id="to-date" class="to-date-input"
                                       onfocus="displaySum(${car.carId}, ${car.price})"/>
                                <label for="to-date">Окончания аренды</label>
                            </div>

                            <div class="input-field col s12">
                                <div class="col s6">
                                    <h6 class="grey-text" style="font-size: 1.5em">Стоимость</h6>
                                </div>
                                <div class="col s6">
                                    <i class="material-icons prefix" style="margin-top: 8px">attach_money</i>
                                    <input type="text" disabled id="car-price-${car.carId}"
                                           class="blue-grey-text car-price">
                                </div>

                            </div>

                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col s12">
                        <input value="Забронировать" type="submit"
                               class="modal-action modal-close waves-effect waves-light btn">
                    </div>
                </div>
            </div>
        </form:form>
    </c:forEach>
</div>
<%@include file="util/scripts.jsp" %>
</body>
</html>
