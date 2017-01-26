package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Stepan.Kachan
 */
@Entity
@Getter
@Setter
@Table(name="RENT_REQUEST")
public class RentRequest implements Serializable{

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String description;

    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;

    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne
    @JoinColumn(name="SSO_ID")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
