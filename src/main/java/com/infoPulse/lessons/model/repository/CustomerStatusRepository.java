package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerStatusRepository extends JpaRepository<CustomerStatus, Integer> {

    CustomerStatus findByName(String name);
    CustomerStatus findCustomerStatusByName(String name);

}



