package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findCustomerByPhoneNumber(String phoneNumber);

    List<Customer> findCustomersByUserLogin(String login);

    List<Customer> findCustomersByPhoneNumberContaining(String phoneNumber);

}



