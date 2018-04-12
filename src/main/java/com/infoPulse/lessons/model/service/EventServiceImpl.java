package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.Event;
import com.infoPulse.lessons.model.entity.Service;
import com.infoPulse.lessons.model.repository.CustomerRepository;
import com.infoPulse.lessons.model.repository.EventRepository;
import com.infoPulse.lessons.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

@org.springframework.stereotype.Service
public class EventServiceImpl implements EventService {

    // Fields
    private EventRepository eventRepository;
    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;


    // Getters and Setters
    @Autowired
    public void setEventRepository(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setServiceRepository(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }


    // Methods
    @Override
    public Event createEvent(EventDTO eventDTO) {
        Random random = new Random();
        Customer customer = customerRepository.findCustomerByPhoneNumber(eventDTO.getCustomerPhoneNumber());
        Service service = serviceRepository.findServiceByName(eventDTO.getServiceName());
        Event event = new Event();
        event.setCustomer(customer);
        event.setService(service);

        event.setDuration(random.nextInt(5)+1);
        event.setCost(service.getPayroll() * event.getDuration());
        return eventRepository.saveAndFlush(event);
    }

}
