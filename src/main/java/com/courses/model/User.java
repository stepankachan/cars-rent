package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * @author Stepan.Kachan
 */
@Entity(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String login;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "creationDate", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @Column(name = "lastonline", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastOnlineDate;
}
