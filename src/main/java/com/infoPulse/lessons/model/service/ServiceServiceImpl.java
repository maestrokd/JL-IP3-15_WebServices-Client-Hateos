package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.repository.CustomerStatusRepository;
import com.infoPulse.lessons.model.repository.ServiceRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceServiceImpl {

    // Fields

        private ServiceRepository serviceRepository;


    @Autowired
    Logger logger;


    // Setters
    @Autowired
    public void setServiceRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    // Methods
    public List<com.infoPulse.lessons.model.entity.Service> findAll() {
        return serviceRepository.findAll();
    }





}
