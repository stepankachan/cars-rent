package com.courses.interceptor;

import lombok.Getter;

/**
 * @author Stepan.Kachan
 */
public enum ActivityType {

    LOGIN("Вход в приложение"),
    LOGOUT("Выход из приложения"),
    CAR_RENT_REQUEST("Отправление заявки на аренду авто"),
    RENT_REQUEST_APPROVE("Подтверждение заявки на авто");

    @Getter
    private final String activity;

    ActivityType(String activity) {
        this.activity = activity;
    }
}
