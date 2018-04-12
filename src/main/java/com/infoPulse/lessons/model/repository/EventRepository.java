package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {



}



