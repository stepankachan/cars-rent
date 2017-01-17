<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="teal darken-3">
    <div class="nav-wrapper">
        <ul class="left">
            <li><a href="<c:url value='/list' />"><i class="material-icons left">supervisor_account</i>Пользователи</a>
            </li>
            <li><a href="<c:url value='/cars' />"><i class="material-icons left">pageview</i>Автомобили</a></li>
            <li><a href="<c:url value='/' />"><i class="material-icons left">assignment</i>Заявки</a></li>
        </ul>
        <ul class="right hide-on-med-and-down">

            <li>
                <div class="col s8">
                    <a> Hello, ${loggedinuser}</a>
                </div>
                <div class="col s4">
                    <span class="floatRight"><a href="<c:url value="/logout" />"><i class="material-icons">power_settings_new</i></a></span>
                </div>
            </li>
        </ul>
    </div>
</nav>


