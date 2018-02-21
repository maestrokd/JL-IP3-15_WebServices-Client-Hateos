package com.infoPulse.lessons.model.repository;


import com.infoPulse.lessons.model.entity.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer> {

    List<CustomerService> findCustomerServicesByCustomerPhoneNumber(String phoneNumber);

    CustomerService findCustomerServiceByCustomerPhoneNumberAndServiceName(String phoneNumber, String name);
}
