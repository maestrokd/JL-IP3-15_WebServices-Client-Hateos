package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerStatus;
import com.infoPulse.lessons.model.entity.Service;
import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.model.service.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  14.07.2017 for spingSecurityAdv project.
 */

@RestController
public class CustomerManagerController {

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
    Logger logger;


    @RequestMapping (value = "all/customers/{phonenumber}", method = RequestMethod.GET)
    public ModelAndView getCustomerRoom(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,Model model
//            ,@ModelAttribute(name = "selectedCustomer")
//                    Customer selCustomer
    ) {
        System.out.println("Come /customer/{phonenumber}");
//        System.out.println(selCustomer);
        System.out.println(phoneNumber);
//                Customer myCustomer = dao.findEntityForID(Customer.class, selCustomer.getPhoneNumber());
        // TODO Dao method "findCustomerByPhoneNumber"
//                Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
//                Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
        ModelAndView modelAndView = new ModelAndView();
        Customer selectedCustomer = customerServiceImpl.findCustomerByPhoneNumber(phoneNumber);
        modelAndView.addObject("selectedCustomer", selectedCustomer);
//                List<Service> serviceList = dao.findAllEntity(Service.class, "Service");
        List<Service> serviceList = serviceServiceImpl.findAll();
        modelAndView.addObject("serviceList", serviceList);
//        System.out.println(selectedCustomer);
        modelAndView.setViewName("all/customers/customerroom");
        return modelAndView;
    }
//
//
//    @RequestMapping (value = "customer/customerservices/show", headers = {"Accept=application/xml"})
//    public ResponseEntity<CustomerServicesDto> getServicesForCustomer(
//            @RequestParam String selectedPhoneNumber
//    ) {
////                Customer selectedCustomer = dao.findCustomerByPhoneNumber(selectedPhoneNumber);
////        List<CustomerService> customerServiceList = selectedCustomer.getCustomerServiceList();
//        List<CustomerService> customerServiceList = customerServiceServiceImpl.findCustomerServicesForCustomer(selectedPhoneNumber);
//        List<CustomerServiceDto> customerServiceDtoList = new LinkedList<>();
//        for (CustomerService customerService : customerServiceList) {
////            System.out.println(service.getCustomerServiceList().size() + "|" + service.getEventList());
//            customerServiceDtoList.add(new CustomerServiceDto(customerService));
//        }
//        System.out.println(customerServiceList);
//        System.out.println(customerServiceDtoList);
//        return new ResponseEntity<>(new CustomerServicesDto(customerServiceDtoList), HttpStatus.OK);
//    }
//
//
//    @RequestMapping (value = "customer/customerservices/add", method = RequestMethod.GET)
//    public ResponseEntity<String> getServicesForCustomer(
//            @RequestParam String selectedPhoneNumber
//            ,@RequestParam String selectedServiceName
//    ) {
//        customerServiceImpl.addCustomerService(selectedPhoneNumber, selectedServiceName);
//        return new ResponseEntity<>("Fine", HttpStatus.OK);
//    }
//
//
//    @RequestMapping (value = "/customerservice/{phonenumber}/{servicename}/changethestatus", method = RequestMethod.GET, headers = {"Accept=application/xml"})
//    public ResponseEntity<String> doChangeTheStatus(
//            @PathVariable("phonenumber")
//                    String phoneNumber
//            ,@PathVariable("servicename")
//                    String serviceName
////            ,Model model
//    ) {
//        customerServiceServiceImpl.changeTheStatus(phoneNumber, serviceName);
//        return new ResponseEntity<>( HttpStatus.OK);
//    }
//
//
//    @RequestMapping (value = "/customerservice/{phonenumber}/{servicename}/delete", method = RequestMethod.GET, headers = {"Accept=application/xml"})
//    public ResponseEntity<String> doDeleteCustomerService(
//            @PathVariable("phonenumber")
//                    String phoneNumber
//            ,@PathVariable("servicename")
//                    String serviceName
////            ,Model model
//    ) {
////        System.out.println("/customerservice/{phonenumber}/{servicename}/delete: " + phoneNumber + "|" + serviceName);
////        CustomerService customerService = dao.getCustomerServicesByPhoneNumberAndServiceName(phoneNumber, serviceName);
////        System.out.println("Delete CustomerService id: " + customerService.getId());
////        dao.deleteEntity(customerService);
////
////        Customer customer = dao.findCustomerByPhoneNumber(phoneNumber);
////        Service service = dao.getServiceForName(serviceName);
////        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
////        System.out.println("Before:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
////        customer.removeService(service);
////        dao.updateEntity(customer);
////        dao.updateEntity(service);
////        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
////        System.out.println("After:\n" + customer.getCustomerServiceList().size() + "|" + service.getCustomerServiceList().size());
//
//        customerServiceImpl.deleteCustomerService(phoneNumber, serviceName);
//
//
////        // TODO Dao method "findCustomerByPhoneNumber"
//////        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
////        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
////        model.addAttribute("customerForm", myCustomer);
////        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
////        return "customers/customerform";
//        return new ResponseEntity<>( HttpStatus.OK);
//    }
//
//
//
//
//
//
//
//
    @RequestMapping (value = "/all/customers/{phonenumber}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateCustomerForm(
            @PathVariable("phonenumber")
                    String phoneNumber
            ,Model model
    ) {
//        System.out.println("customer/update(phoneNumber): " + phoneNumber);
        // TODO Dao method "findCustomerByPhoneNumber"
//        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
        ModelAndView modelAndView = new ModelAndView();

        Customer myCustomer = customerServiceImpl.findCustomerByPhoneNumber(phoneNumber);
        modelAndView.addObject("customerForm", myCustomer);
//        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
        List<CustomerStatus> customerStatusList = customerStatusServiceImpl.findAll();
        modelAndView.addObject("customerStatusList", customerStatusList);
        modelAndView.setViewName("all/customers/customerupdateform");
        return modelAndView;
    }


    @RequestMapping (value = "/all/customers/update", method = RequestMethod.POST)
    public ModelAndView doUpdateCustomerForm(
//            @PathVariable("phonenumber")
//                    String phoneNumber
            @ModelAttribute(name = "customerForm")
                    Customer updatedCustomer
//            ,Model model
    ) {
        customerServiceImpl.updateCustomer(updatedCustomer.getUser().getLogin()
                , updatedCustomer.getPhoneNumber()
                , updatedCustomer.getCustomerStatus().getName()
                , updatedCustomer.getBalance());

        ModelAndView modelAndView = new ModelAndView();

//        Customer myCustomer = customerServiceImpl.findCustomerByPhoneNumber(phoneNumber);
//        modelAndView.addObject("customerForm", myCustomer);
////        System.out.println("customer/update(myCustomer from DB): " + myCustomer);
//        List<CustomerStatus> customerStatusList = customerStatusServiceImpl.findAll();
//        modelAndView.addObject("customerStatusList", customerStatusList);
        modelAndView.setViewName("redirect:/all/customers/" + updatedCustomer.getPhoneNumber());
        return modelAndView;
    }

//
//
    @RequestMapping (value = "/protected/customers/{phonenumber}/delete", method = RequestMethod.GET)
    public ModelAndView doDeleteCustomer(
            @PathVariable("phonenumber")
                    String phoneNumber
            , @RequestParam String login
            , Model model
    ) {
        System.out.println("customer/delete(phoneNumber): " + phoneNumber);

//        User user = (User) httpSession.getAttribute("loginUser");
//        System.out.println("User Login: " + user.getLogin());

//        boolean resultDel = userServiceImpl.deleteCustomer(user, phoneNumber);
        boolean resultDel = customerServiceImpl.deleteCustomer(phoneNumber);
        System.out.println(resultDel);

        // TODO Dao method "findCustomerByPhoneNumber"
////        Customer myCustomer = dao.findEntityForID(Customer.class, phoneNumber);
//        Customer myCustomer = dao.findCustomerByPhoneNumber(phoneNumber);
//        dao.deleteEntity(myCustomer);

        return new ModelAndView("redirect:/protected/users/" + login);
    }
//
//
//    // save or update customer
//    @RequestMapping(value = "/customers", method = RequestMethod.POST)
//    public String saveOrUpdateCustomer(
//            Model model
//            , RedirectAttributes redirectAttributes
//            ,@ModelAttribute(name = "customerForm")
//                    Customer addCustomer
//            ,@ModelAttribute(name = "loginUser")
//                    User user
//    ) {
//        System.out.println("COME :/customers");
//        System.out.println(addCustomer.getUser());
//        System.out.println(addCustomer.getCustomerStatus());
//
//        String userLoginForSet = addCustomer.getUser().getLogin();
//        System.out.println(addCustomer.getPhoneNumber());
//        System.out.println(userLoginForSet);
//
//        // TODO Dao method "findUserByLogin"
////        User userForSet = dao.findEntityForID(User.class, userLoginForSet);
////        User userForSet = dao.findUserByLogin(userLoginForSet);
////        boolean isCustomerExists = dao.isEntityExist(Customer.class, addCustomer.getPhoneNumber());
//        boolean isCustomerExists = dao.isCustomerExist(addCustomer.getPhoneNumber());
//        System.out.println(isCustomerExists);
//        if (isCustomerExists) {
//            System.out.println("isCustomerExists true" + isCustomerExists);
////            errors.rejectValue("login", "error.loginExists");
////            userService.updateCustomer(user, addCustomer);
////            dao.updateEntity(addCustomer);
//            customerServiceImpl.updateCustomer(addCustomer.getUser().getLogin(), addCustomer.getCustomerStatus().getName(), addCustomer);
//            redirectAttributes.addFlashAttribute("message", "Customer update successfully!");
////            addCustomer.setUser(userForSet);
//        } else {
//            System.out.println("isCustomerExists false" + isCustomerExists);
//            userServiceImpl.addCustomer(addCustomer.getUser().getLogin(), addCustomer.getCustomerStatus().getName(), addCustomer);
//            redirectAttributes.addFlashAttribute("message", "Customer added successfully!");
////            System.out.println(userForSet.getLogin());
////            addCustomer.setUser(userForSet);
////            dao.addEntity(addCustomer);
//        }
//        System.out.println("END :/customers");
//        return "redirect:/customer/" + addCustomer.getPhoneNumber();
//    }


    // Show add customer form
    @RequestMapping(value = "/protected/customers/add", method = RequestMethod.GET)
    public ModelAndView showAddCustomerForm(Model model) {
        Customer addCustomer = new Customer();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customerForm", addCustomer);
        List<CustomerStatus> customerStatusList = customerStatusServiceImpl.findAll();
        modelAndView.addObject("customerStatusList", customerStatusList);
        modelAndView.setViewName("protected/customers/customerform");
        return modelAndView;
    }


    // ShowDo add customer
    @RequestMapping(value = "/protected/customers/add", method = RequestMethod.POST)
    public ModelAndView doAddCustomerForm(Model model
            , RedirectAttributes redirectAttributes
            ,@ModelAttribute(name = "customerForm")
            Customer addCustomer
    ) {
        customerServiceImpl.saveCustomer(addCustomer);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/protected/users/" + addCustomer.getUser().getLogin());
        return modelAndView;
    }




//    // Show find customer form
//    @RequestMapping (value = "/customers/find", method = RequestMethod.GET)
//    public String showFindCustomerForm(Model model) {
////                List<Customer> findCustomerList = new LinkedList<>();
////                model.addAttribute("findCustomerList", findCustomerList);
//        return "customers/findcustomerform";
//    }
//
//    // Find customers
//    @RequestMapping (value = "/findcustomer", method = RequestMethod.GET)
//    public String doFindCustomer(
//            Model model,
////            @ModelAttribute(name = "findCustomerPhoneNumber")
//            @RequestParam(name = "findCustomerPhoneNumber")
//                    String findCustomerPhoneNumber
////            @ModelAttribute(name = "findCustomerList")
////                    List<Customer> findCustomerList
//    ) {
//        System.out.println("findCustomerPhoneNumber: " + findCustomerPhoneNumber);
////        Customer findCustomer = dao.findEntityForID(Customer.class, findCustomerPhoneNumber);
//        List<Customer> findCustomerList = dao.findCustomersLikePhoneNumber(findCustomerPhoneNumber);
////        System.out.println(findCustomerList);
////        List<Customer> findCustomerList = new LinkedList<>();
////        findCustomerList.add(findCustomer);
//
//        Customer selCustomer = new Customer();
//        model.addAttribute("selectedCustomer", selCustomer);
//        model.addAttribute("findCustomerList", findCustomerList);
//        return "customers/findcustomerssful";
//    }





}
