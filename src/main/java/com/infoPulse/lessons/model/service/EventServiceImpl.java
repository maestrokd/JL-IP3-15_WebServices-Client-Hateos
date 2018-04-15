package com.infoPulse.lessons.model.service;

import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.entity.*;
import com.infoPulse.lessons.model.entity.CustomerService;
import com.infoPulse.lessons.model.repository.CustomerRepository;
import com.infoPulse.lessons.model.repository.CustomerServiceRepository;
import com.infoPulse.lessons.model.repository.EventRepository;
import com.infoPulse.lessons.model.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Random;

@org.springframework.stereotype.Service
public class EventServiceImpl implements EventService {

    // Fields
    private EventRepository eventRepository;
    private CustomerRepository customerRepository;
    private ServiceRepository serviceRepository;
    private CustomerServiceRepository customerServiceRepository;


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

    @Autowired
    public void setCustomerServiceRepository(CustomerServiceRepository customerServiceRepository) {
        this.customerServiceRepository = customerServiceRepository;
    }

    // Methods
    @Override
    public Event createEvent(EventDTO eventDTO) {

        CustomerService customerService = customerServiceRepository.findCustomerServiceByCustomerPhoneNumberAndServiceName(eventDTO.getCustomerPhoneNumber(), eventDTO.getServiceName());
        if (customerService == null) {
            return null;
        }
        if (ServiceStatus.ServiceStatusNames.DISABLED.getServiceStatusNames().equals(customerService.getServiceStatus().getName())) {
            return null;
        }

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


    @Override
    public List<Event> findEventsByCustomerPhoneNumber(String phoneNumber) {
        return eventRepository.findEventsByCustomerPhoneNumber(phoneNumber);
    }


    @Override
    public Page<Event> findEventsByCustomerPhoneNumber(String phoneNumber, Pageable pageable) {
        return eventRepository.findEventsByCustomerPhoneNumber(phoneNumber, pageable);
    }


    @Override
    public List<Event> findEventsByCustomerPhoneNumberAndServiceName(String customerPhoneNumber, String serviceName) {
        return eventRepository.findEventsByCustomerPhoneNumberAndServiceName(customerPhoneNumber, serviceName);
    }

}
