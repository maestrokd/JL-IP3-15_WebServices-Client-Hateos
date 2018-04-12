package com.infoPulse.lessons.model.dto;

import com.infoPulse.lessons.model.entity.Event;


public class EventDTO {

    // Fields
    private int id;
    private String customerPhoneNumber;
    private String serviceName;
    private int duration;
    private float cost;


    // Constructors
    public EventDTO() {
    }

    public EventDTO(String customerPhoneNumber, String serviceName) {
        this.customerPhoneNumber = customerPhoneNumber;
        this.serviceName = serviceName;
    }

    public EventDTO(Event event) {
        this.id = event.getId();
        this.customerPhoneNumber = event.getCustomer().getPhoneNumber();
        this.serviceName = event.getService().getName();
        this.duration = event.getDuration();
        this.cost = event.getCost();
    }


    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
