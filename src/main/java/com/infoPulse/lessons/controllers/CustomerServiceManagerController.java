package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.CustomerServiceDto;
import com.infoPulse.lessons.model.dto.CustomerServicesDto;
import com.infoPulse.lessons.model.entity.CustomerService;
import com.infoPulse.lessons.model.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class CustomerServiceManagerController {

    // Fields
    private CustomerServiceService customerServiceService;


    // Setters
    @Autowired
    public void setCustomerServiceService(CustomerServiceService customerServiceService) {
        this.customerServiceService = customerServiceService;
    }


    // Methods
    @RequestMapping(value = "customer/customerservices/show", headers = {"Accept=application/xml"})
    public ResponseEntity<CustomerServicesDto> getServicesForCustomerViaAjax(
            @RequestParam String selectedPhoneNumber
    ) {
        List<CustomerService> customerServiceList = customerServiceService.findCustomerServicesForCustomer(selectedPhoneNumber);
        List<CustomerServiceDto> customerServiceDtoList = new ArrayList<>();
        for (CustomerService customerService : customerServiceList) {
            customerServiceDtoList.add(new CustomerServiceDto(customerService));
        }
        return new ResponseEntity<>(new CustomerServicesDto(customerServiceDtoList), HttpStatus.OK);
    }


    @RequestMapping(value = "customer/customerservices/add", method = RequestMethod.GET)
    public ResponseEntity<String> doAddServicesForCustomerViaAjax(
            @RequestParam String selectedPhoneNumber
            , @RequestParam String selectedServiceName
    ) {
        CustomerService customerService = new CustomerService(selectedPhoneNumber, selectedServiceName);
        CustomerService customerServiceFromDB = customerServiceService.addCustomerService(customerService);

        if (customerServiceFromDB != null) {
            return new ResponseEntity<>("Fine", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
    }


    @RequestMapping(value = "/customerservice/{phonenumber}/{servicename}/changethestatus"
            , method = RequestMethod.GET, headers = {"Accept=application/xml"})
    public ResponseEntity<String> doChangeTheStatusViaAjax(
            @PathVariable("phonenumber")
                    String phoneNumber
            , @PathVariable("servicename")
                    String serviceName
    ) {
        customerServiceService.changeTheStatus(phoneNumber, serviceName);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/customerservice/{phonenumber}/{servicename}/delete"
            , method = RequestMethod.GET, headers = {"Accept=application/xml"})
    public ResponseEntity<String> doDeleteCustomerServiceViaAjax(
            @PathVariable("phonenumber")
                    String phoneNumber
            , @PathVariable("servicename")
                    String serviceName
    ) {
        CustomerService customerService = new CustomerService(phoneNumber, serviceName);
        if (customerServiceService.deleteCustomerService(customerService)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
