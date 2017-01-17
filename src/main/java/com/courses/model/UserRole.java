package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="USER_PROFILE")
public class UserRole implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;

    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = UserRoleType.USER.getUserRoleType();

    @Override
    public String toString() {
        return "UserRole [id=" + id + ", type=" + type + "]";
    }

}
