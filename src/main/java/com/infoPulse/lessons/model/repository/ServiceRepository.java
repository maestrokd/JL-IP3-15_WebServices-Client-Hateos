package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceRepository extends JpaRepository<Service, Integer> {

    Service findServiceByName(String name);

}



