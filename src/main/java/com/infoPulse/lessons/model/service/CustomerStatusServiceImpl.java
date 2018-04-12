package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.repository.CustomerStatusRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerStatusServiceImpl implements CustomerStatusService {

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
    @Override
    public List<CustomerStatus> findAll() {
        return customerStatusRepository.findAll();
    }

}
