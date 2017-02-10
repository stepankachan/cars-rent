package com.courses.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Stepan.Kachan
 */
@Getter
@Setter
@Entity
@Table(name="activity_log")
public class LogActivity implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    @Column(name = "log_id")
    private Integer logId;

    @Temporal(TemporalType.TIMESTAMP)
    private Date time;

    private String activity;

    @ManyToOne
    @JoinColumn(name="SSO_ID")
    private AppUser user;

    @Transient
    public String getFormattedTime(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return formatter.format(time);
    }
}
