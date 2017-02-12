<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="teal darken-3">
    <div class="nav-wrapper">
        <ul class="left">
            <li><a href="<c:url value='/list'/>"><i class="material-icons left">supervisor_account</i>Пользователи</a>
            </li>
            <li><a href="<c:url value='/cars'/>"><i class="material-icons left">pageview</i>Автомобили</a></li>
            <li>
                <a href="<c:url value='/requests'/>">
                    <i class="material-icons left">assignment</i>
                    <sec:authorize access="hasRole('ADMIN')">
                        Заявки
                    </sec:authorize>
                    <sec:authorize access="hasRole('USER')">
                        Мои заявки
                    </sec:authorize>
                </a>
            </li>
            <sec:authorize access="hasRole('ADMIN')">
                <li><a href="<c:url value='/activities'/>"><i class="material-icons left">schedule</i>Журнал</a></li>
            </sec:authorize>
        </ul>

        <ul class="right">
            <li>
                <div class="row" style="margin-bottom: 0 !important;">
                    <div class="col s9">
                        <p style="margin-top: 2%"> Привет ${loggedinuser.ssoId}</p>
                    </div>
                    <div class="col s3">
                        <a href="<c:url value="/logout" />" style="margin-left: -20px">
                            <i class="material-icons">power_settings_new</i>
                        </a>
                    </div>
                </div>
            </li>
        </ul>
        <ul class="right">
            <li>
                <a href="#fillBalance"><i class="material-icons left">credit_card</i>$ ${loggedinuser.money}</a>
            </li>
        </ul>
    </div>
</nav>

<div id="fillBalance" class="modal modal-fixed-footer">
    <div class="modal-content">
        <form action="<c:url value='/fillBalance' />">
            <h4>Пополнить баланс</h4>
            <div class="input-field col s6">
                <input id="icon_prefix" type="text" class="validate" name="amount"/>
                <label for="icon_prefix">Сумма</label>
            </div>
            <input value="Пополнить" type="submit"
                   class="modal-action modal-close waves-effect waves-teal btn-flat right">
        </form>
    </div>
</div>


