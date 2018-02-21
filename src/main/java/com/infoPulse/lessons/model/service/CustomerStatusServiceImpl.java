package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.repository.CustomerRepository;
import com.infoPulse.lessons.model.repository.CustomerStatusRepository;
import com.infoPulse.lessons.model.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStatusServiceImpl {

    // Fields

        private CustomerStatusRepository customerStatusRepository;


    @Autowired
    Logger logger;


    // Setters
    @Autowired
    public void setCustomerStatusRepository(CustomerStatusRepository customerStatusRepository) {
        this.customerStatusRepository = customerStatusRepository;
    }


    // Methods
    public List<CustomerStatus> findAll() {
        return customerStatusRepository.findAll();
    }





}
