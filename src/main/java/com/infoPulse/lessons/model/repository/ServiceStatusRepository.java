package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Service;
import com.infoPulse.lessons.model.entity.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServiceStatusRepository extends JpaRepository<ServiceStatus, Integer> {

    ServiceStatus findServiceStatusByName(String name);

}



