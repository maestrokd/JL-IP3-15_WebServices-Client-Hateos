package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.entity.Event;

public interface EventService {

    Event createEvent(EventDTO eventDTO);

}
