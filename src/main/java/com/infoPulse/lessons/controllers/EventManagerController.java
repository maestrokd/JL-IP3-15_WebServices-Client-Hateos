package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.EventDTO;
import com.infoPulse.lessons.model.dto.PageResource;
import com.infoPulse.lessons.model.entity.Event;
import com.infoPulse.lessons.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@RestController
public class EventManagerController {

    // Fields
    private EventService eventService;


    // Setters
    @Autowired
    public void setEventService(
            @Qualifier("eventService01")
                                            EventService eventService) {
        this.eventService = eventService;
    }


    // Methods
    @RequestMapping (value = "all/events/{phonenumber}/{servicename}/createAjax", method = RequestMethod.GET)
    public ResponseEntity doCreateEventViaAjax(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
            , RedirectAttributes redirectAttributes
    ) {
        EventDTO eventDTO = new EventDTO(phoneNumber, serviceName);

        Event event = eventService.createEvent(eventDTO);

        if (event != null) {
            redirectAttributes.addFlashAttribute("message", "Event created successfully!");
            return new ResponseEntity(HttpStatus.OK);
        }

        redirectAttributes.addFlashAttribute("messageError", "Event creating failed!");
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "all/events/{phonenumber}", method = RequestMethod.GET)
    public PageResource<Event> getEventsByCustomerPhoneNumberViaAjax(
            @PathVariable("phonenumber")
                    String phoneNumber
            , Pageable pageable
    ) {
        Page<Event> events = eventService.findEventsByCustomerPhoneNumber(phoneNumber, pageable);
        return new PageResource<>(events, "", "");
    }


    @RequestMapping(value = "all/events/{phonenumber}/{servicename}", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsByCustomerPhoneNumberAndServiceNameViaAjax(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
    ) {
        List<Event> events = eventService.findEventsByCustomerPhoneNumberAndServiceName(phoneNumber, serviceName);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }





//    @RequestMapping(value = "all/events/{phonenumber}", method = RequestMethod.GET)
//    public ResponseEntity<List<Event>> getEventsByCustomerPhoneNumberViaAjax(
//            @PathVariable("phonenumber")
//                    String phoneNumber
//    ) {
//        List<Event> events = eventService.findEventsByCustomerPhoneNumber(phoneNumber);
//        return new ResponseEntity<>(events, HttpStatus.OK);
//    }


}
