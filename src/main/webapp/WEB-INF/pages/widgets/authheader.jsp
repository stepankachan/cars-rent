<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="teal darken-3">
    <div class="nav-wrapper">
        <ul class="left">
            <li><a href="<c:url value='/list'/>"><i class="material-icons left">supervisor_account</i>Пользователи</a></li>
            <li><a href="<c:url value='/cars'/>"><i class="material-icons left">pageview</i>Автомобили</a></li>
            <li><a href="<c:url value='/requests'/>"><i class="material-icons left">assignment</i>Заявки</a></li>
            <li><a href="<c:url value='/activities'/>"><i class="material-icons left">schedule</i>Журнал</a></li>
        </ul>
        <ul class="right">
            <li>
                <div class="row" style="margin-bottom: 0 !important;">
                    <div class="col s10">
                        <p style="margin-top: 2%"> Добро пожаловать, ${loggedinuser}</p>
                    </div>
                    <div class="col s2">
                        <a href="<c:url value="/logout" />" style="margin-left: -20px">
                            <i class="material-icons">power_settings_new</i>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</nav>


