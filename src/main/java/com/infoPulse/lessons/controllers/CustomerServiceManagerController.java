package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.CustomerServiceDto;
import com.infoPulse.lessons.model.dto.CustomerServicesDto;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerService;
import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.entity.Service;
import com.infoPulse.lessons.model.service.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  14.07.2017 for spingSecurityAdv project.
 */

@RestController
public class CustomerServiceManagerController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    CustomerServiceImpl customerServiceImpl;
    @Autowired
    CustomerStatusServiceImpl customerStatusServiceImpl;
    @Autowired
    ServiceServiceImpl serviceServiceImpl;
    @Autowired
    CustomerServiceServiceImpl customerServiceServiceImpl;
    @Autowired
    Logger logger;





    @RequestMapping (value = "customer/customerservices/show", headers = {"Accept=application/xml"})
    public ResponseEntity<CustomerServicesDto> getServicesForCustomer(
            @RequestParam String selectedPhoneNumber
    ) {
//                Customer selectedCustomer = dao.findCustomerByPhoneNumber(selectedPhoneNumber);
//        List<CustomerService> customerServiceList = selectedCustomer.getCustomerServiceList();
        List<CustomerService> customerServiceList = customerServiceServiceImpl.findCustomerServicesForCustomer(selectedPhoneNumber);
        List<CustomerServiceDto> customerServiceDtoList = new LinkedList<>();
        for (CustomerService customerService : customerServiceList) {
//            System.out.println(service.getCustomerServiceList().size() + "|" + service.getEventList());
            customerServiceDtoList.add(new CustomerServiceDto(customerService));
        }
        System.out.println(customerServiceList);
        System.out.println(customerServiceDtoList);
        return new ResponseEntity<>(new CustomerServicesDto(customerServiceDtoList), HttpStatus.OK);
    }
//
//
    @RequestMapping (value = "customer/customerservices/add", method = RequestMethod.GET)
    public ResponseEntity<String> getServicesForCustomer(
            @RequestParam String selectedPhoneNumber
            ,@RequestParam String selectedServiceName
    ) {
        customerServiceImpl.addCustomerService(selectedPhoneNumber, selectedServiceName);
        return new ResponseEntity<>("Fine", HttpStatus.OK);
    }
//
//
    @RequestMapping (value = "/customerservice/{phonenumber}/{servicename}/changethestatus", method = RequestMethod.GET, headers = {"Accept=application/xml"})
    public ResponseEntity<String> doChangeTheStatus(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
//            ,Model model
    ) {
        customerServiceServiceImpl.changeTheStatus(phoneNumber, serviceName);
        return new ResponseEntity<>( HttpStatus.OK);
    }
//
//
    @RequestMapping (value = "/customerservice/{phonenumber}/{servicename}/delete", method = RequestMethod.GET, headers = {"Accept=application/xml"})
    public ResponseEntity<String> doDeleteCustomerService(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,@PathVariable("servicename")
                    String serviceName
//            ,Model model
    ) {
//        System.out.println("/customerservice/{phonenumber}/{servicename}/delete: " + phoneNumber + "|" + serviceName);
//        CustomerService customerService = dao.getCustomerServicesByPhoneNumberAndServiceName(phoneNumber, serviceName);
//        System.out.println("Delete CustomerService id: " + customerService.getId());
//        dao.deleteEntity(customerService);
//
//        Customer customer = dao.findCustomerByPhoneNumber(phoneNumber);
//        Service service = dao.getServiceForName(serviceName);
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        customer.removeService(service);
//        dao.updateEntity(customer);
//        dao.updateEntity(service);
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());

        customerServiceImpl.deleteCustomerService(phoneNumber, serviceName);


//        // TODO Dao method "findCustomerByPhoneNumber"
////        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
//        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
//        model.addAttribute("customerForm", myCustomer);
//        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
//        return "customers/customerform";
        return new ResponseEntity<>( HttpStatus.OK);
    }













}
