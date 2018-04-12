package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.AssemblerCustomerDTO;
import com.infoPulse.lessons.model.dto.CustomerDTO;
import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.entity.Event;
import com.infoPulse.lessons.model.entity.Service;
import com.infoPulse.lessons.model.service.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@RestController
public class EventManagerController {

    // Fields
    private EventService eventService;
    private UserService userService;
    private CustomerService customerService;
    private CustomerStatusService customerStatusService;
    private ServiceService serviceService;


    @Autowired
    Logger logger;


    // Getters and Setters
    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setCustomerStatusService(CustomerStatusService customerStatusService) {
        this.customerStatusService = customerStatusService;
    }

    @Autowired
    public void setServiceService(ServiceService serviceService) {
        this.serviceService = serviceService;
    }


    // Methods
    @RequestMapping (value = "all/events/{phonenumber}/{servicename}/create", method = RequestMethod.GET)
    public ModelAndView getCustomerRoom(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
            , RedirectAttributes redirectAttributes
    ) {
        ModelAndView modelAndView = new ModelAndView();

        EventDTO eventDTO = new EventDTO(phoneNumber, serviceName);

        Event event = eventService.createEvent(eventDTO);

        if (event != null) {
            redirectAttributes.addFlashAttribute("message", "Event created successfully!");
            modelAndView.setViewName("redirect:/all/customers/" + phoneNumber);
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("messageError", "Event create failed!");
        modelAndView.setViewName("redirect:/all/customers/" + phoneNumber);
        return modelAndView;
    }





}
