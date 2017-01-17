package com.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name="SSO_ID")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

}
