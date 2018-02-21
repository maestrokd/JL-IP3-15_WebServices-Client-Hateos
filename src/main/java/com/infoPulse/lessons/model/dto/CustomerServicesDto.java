package com.infoPulse.lessons.model.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "customer_services")
public class CustomerServicesDto {

    // Fields
    private List<CustomerServiceDto> customerServiceDtoList;


    // Constructors
    public CustomerServicesDto() {
    }

    public CustomerServicesDto(List<CustomerServiceDto> customerServiceDtoList) {
        this.customerServiceDtoList = customerServiceDtoList;
    }

    // Getters and Setters
    @XmlElement(name = "customer_service")
    public List<CustomerServiceDto> getCustomerServiceDtoList() {
        return customerServiceDtoList;
    }

    public void setCustomerServiceDtoList(List<CustomerServiceDto> customerServiceDtoList) {
        this.customerServiceDtoList = customerServiceDtoList;
    }
}
