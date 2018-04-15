package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    Event createEvent(EventDTO eventDTO);

    List<Event> findEventsByCustomerPhoneNumber(String phoneNumber);

    List<Event> findEventsByCustomerPhoneNumberAndServiceName(String phoneNumber, String name);

    Page<Event> findEventsByCustomerPhoneNumber(String phoneNumber, Pageable pageable);

}
