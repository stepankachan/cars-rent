package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author Stepan.Kachan
 */
@Getter
@Setter
@Entity(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Column(name = "url")
    private String imageURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;




}
