package com.infoPulse.lessons.model.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "event")

//@Cacheable
//@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL)

public class Event implements Serializable {

    // Fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
//    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_customer_customer_services"))
    private Customer customer;

    @ManyToOne
//    @JoinColumn(name = "service_id", foreignKey = @ForeignKey(name = "fk_service_customer_services"))
    private Service service;

    @CreationTimestamp
    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "duration")
    private int duration;

    @Column(name = "cost")
    private float cost;


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
