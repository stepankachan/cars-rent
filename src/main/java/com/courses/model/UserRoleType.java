package com.courses.model;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author Stepan.Kachan
 */
@Getter
public enum UserRoleType implements Serializable {
    USER("USER"),
    ADMIN("ADMIN");

    String userRoleType;

    UserRoleType(String userProfileType) {
        this.userRoleType = userProfileType;
    }

}