package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.CustomerService;

import java.util.List;

public interface CustomerServiceService {

    List<CustomerService> findCustomerServicesForCustomer(String phoneNumber);

    void changeTheStatus(String phoneNumber, String serviceName);

    CustomerService addCustomerService(CustomerService customerService);

    boolean deleteCustomerService(CustomerService customerService);

}
