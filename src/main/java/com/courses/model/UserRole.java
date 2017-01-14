package com.courses.model;

/**
 * @author Stepan.Kachan
 */
public enum UserRole {

    ROLE_ADMIN,
    ROLE_USER;


    @Override
    public String toString() {
        return this == ROLE_ADMIN ? "ROLE_ADMIN" : "ROLE_USER";
    }
}
