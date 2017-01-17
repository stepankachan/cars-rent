package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Set;

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
    @Column(name = "car_id")
    private Integer carId;

    private String name;

    @Column(name = "url")
    private String imageURL;

    @Temporal(TemporalType.TIMESTAMP)
    private Date releaseDate;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<RentRequest> rentRequests;


}
