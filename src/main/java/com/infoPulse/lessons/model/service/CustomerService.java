package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.CustomerDTO;
import com.infoPulse.lessons.model.entity.Customer;

import java.util.List;

public interface CustomerService {

    Customer findCustomerByPhoneNumber(String phoneNumber);

    List<Customer> findCustomersForUser(String login);

    List<Customer> findCustomersLikePhoneNumber(String phoneNumber);

    Customer saveCustomer(CustomerDTO customerDTO);

    Customer updateCustomer(CustomerDTO newDtoCustomer);

    boolean deleteCustomer(String customerPhoneNumber);

}
