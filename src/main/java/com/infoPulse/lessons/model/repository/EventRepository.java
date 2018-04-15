package com.infoPulse.lessons.model.repository;

import com.infoPulse.lessons.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findEventsByCustomerPhoneNumber(String phoneNumber);

    List<Event> findEventsByCustomerPhoneNumberAndServiceName(String phoneNumber, String name);

    Page<Event> findEventsByCustomerPhoneNumber(String phoneNumber, Pageable pageable);

}