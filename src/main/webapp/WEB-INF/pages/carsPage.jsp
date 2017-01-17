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
<div class="col s12">
    <div class="section">
        <h4 class="teal-text center-align" style="font-weight: bolder">Автомобили</h4>
    </div>
    <div class="col s12 col-border no-top-border">
        <c:forEach items="${cars}" var="car">

            <div class="col s12 m8 offset-m2 l6 offset-l3">
                <div class="card-panel grey lighten-5 z-depth-1">
                    <div class="row valign-wrapper">
                        <div class="col s2">
                            <img src="${car.imageURL}" alt="" class="circle responsive-img">
                            <!-- notice the "circle" class -->
                        </div>
                        <div class="col s10">
              <span class="black-text">
                      ${car.name}
              </span>
                        </div>
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
