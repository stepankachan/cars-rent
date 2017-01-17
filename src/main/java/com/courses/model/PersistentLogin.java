package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Getter
@Setter
@Table(name="PERSISTENT_LOGINS")
public class PersistentLogin implements Serializable{

    @Id
    private String series;

    @Column(name="USERNAME", unique=true, nullable=false)
    private String username;

    @Column(name="TOKEN", unique=true, nullable=false)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date last_used;

}
