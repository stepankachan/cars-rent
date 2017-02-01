package com.courses.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
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

    private BigDecimal price;

    @Column(name = "engine_capacity")
    private BigDecimal engineCapacity;

    private String fuel;

    private String transmission;

    @Column(name = "passenger_count")
    private Integer passengerCount;

    private Boolean free;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<RentRequest> rentRequests;




}
