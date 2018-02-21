package com.infoPulse.lessons.model.dto;



import com.infoPulse.lessons.model.entity.CustomerService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "customer_service")
public class CustomerServiceDto {

    // Fields
    private int id;
    private String phoneNumber;
    private String serviceName;
    private float servicePayroll;
    private String serviceStatus;


    // Constructors
    public CustomerServiceDto() {
    }

    public CustomerServiceDto(CustomerService customerService) {
        this.id = customerService.getId();
        this.phoneNumber = customerService.getCustomer().getPhoneNumber();
        this.serviceName = customerService.getService().getName();
        this.servicePayroll = customerService.getService().getPayroll();
        this.serviceStatus = customerService.getServiceStatus().getName();
    }


    // Getters and Setters
    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @XmlElement
    public float getServicePayroll() {
        return servicePayroll;
    }

    public void setServicePayroll(float servicePayroll) {
        this.servicePayroll = servicePayroll;
    }

    @XmlElement
    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }
}
