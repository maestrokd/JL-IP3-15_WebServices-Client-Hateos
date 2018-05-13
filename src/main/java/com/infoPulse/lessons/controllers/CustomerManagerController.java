package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.dto.AssemblerCustomerDTO;
import com.infoPulse.lessons.model.dto.CustomerDTO;
import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.CustomerStatus;
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
public class CustomerManagerController {

    // Fields
    private CustomerService customerService;
    private CustomerStatusService customerStatusService;
    private ServiceService serviceService;
    private Logger logger;


    // Setters
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

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    // Methods
    @RequestMapping (value = "all/customers/{phonenumber}", method = RequestMethod.GET)
    public ModelAndView getCustomerRoom(
            @PathVariable("phonenumber")
                    String phoneNumber
    ) {
        ModelAndView modelAndView = new ModelAndView();

        Customer selectedCustomer = customerService.findCustomerByPhoneNumber(phoneNumber);
        modelAndView.addObject("selectedCustomer", selectedCustomer);

        List<Service> serviceList = serviceService.findAll();
        modelAndView.addObject("serviceList", serviceList);

        modelAndView.setViewName("all/customers/customerroom");
        return modelAndView;
    }


    @RequestMapping (value = "/all/customers/{phonenumber}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateCustomerForm(
            @PathVariable("phonenumber")
                    String phoneNumber
    ) {
        ModelAndView modelAndView = new ModelAndView();

        Customer customerDB = customerService.findCustomerByPhoneNumber(phoneNumber);
        CustomerDTO customerDTO = new CustomerDTO(customerDB);
        modelAndView.addObject("customerDtoForm", customerDTO);

        List<CustomerStatus> customerStatusList = customerStatusService.findAll();
        modelAndView.addObject("customerStatusList", customerStatusList);

        modelAndView.setViewName("all/customers/customerupdateform");
        return modelAndView;
    }


    @RequestMapping (value = "/all/customers/update", method = RequestMethod.POST)
    public ModelAndView doUpdateCustomerForm(
            @ModelAttribute(name = "customerDtoForm")
            @Valid
                    CustomerDTO customerDtoUpdatedForm
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
    ) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("customerDtoForm", customerDtoUpdatedForm);

            List<CustomerStatus> customerStatusList = customerStatusService.findAll();
            modelAndView.addObject("customerStatusList", customerStatusList);

            modelAndView.setViewName("all/customers/customerupdateform");
            return modelAndView;
        }

        Customer customerUpdatedDB = customerService.updateCustomer(customerDtoUpdatedForm);

        if (customerUpdatedDB != null) {
            redirectAttributes.addFlashAttribute("message", "Customer updated successfully!");
            modelAndView.setViewName("redirect:/all/customers/" + customerUpdatedDB.getPhoneNumber());
            return modelAndView;
        }

        redirectAttributes.addFlashAttribute("messageError", "Customer update failed!");
        modelAndView.setViewName("redirect:/all/customers/" + customerDtoUpdatedForm.getPhoneNumber() + "update");
        return modelAndView;
    }


    @RequestMapping (value = "/protected/customers/{phonenumber}/delete", method = RequestMethod.GET)
    public ModelAndView doDeleteCustomer(
            @PathVariable("phonenumber")
                    String phoneNumber
            , @RequestParam String login
            , RedirectAttributes redirectAttributes
    ) {
        if (customerService.deleteCustomer(phoneNumber)) {
            logger.info("Customer " + phoneNumber + " deleted successfully");
            redirectAttributes.addFlashAttribute("message", "Customer " + phoneNumber + " deleted successfully");
            return new ModelAndView("redirect:/all/users/" + login);
        }

        logger.error("Customer " + phoneNumber + " deleting failed");
        redirectAttributes.addFlashAttribute("message", "Customer " + phoneNumber + " deleting failed");
        return new ModelAndView("redirect:/all/users/" + login);
    }


    // Show add customer form
    @RequestMapping(value = "/protected/customers/add", method = RequestMethod.GET)
    public ModelAndView showAddCustomerForm() {
        ModelAndView modelAndView = new ModelAndView();

        CustomerDTO addCustomerDTO = new CustomerDTO();
        modelAndView.addObject("customerDtoForm", addCustomerDTO);

        List<CustomerStatus> customerStatusList = customerStatusService.findAll();
        modelAndView.addObject("customerStatusList", customerStatusList);

        modelAndView.setViewName("protected/customers/customerform");
        return modelAndView;
    }


    // Do add customer
    @RequestMapping(value = "/protected/customers/add", method = RequestMethod.POST)
    public ModelAndView doAddCustomerForm(
            @ModelAttribute(name = "customerDtoForm")
            @Valid
            CustomerDTO addCustomerDTO
            , BindingResult bindingResult
            , RedirectAttributes redirectAttributes
    ) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("customerDtoForm", addCustomerDTO);

            List<CustomerStatus> customerStatusList = customerStatusService.findAll();
            modelAndView.addObject("customerStatusList", customerStatusList);

            modelAndView.setViewName("protected/customers/customerform");
            return modelAndView;
        }

        Customer customerDB = customerService.saveCustomer(addCustomerDTO);

        if (customerDB != null) {
            modelAndView.setViewName("redirect:/all/users/" + addCustomerDTO.getUserLogin());
            redirectAttributes.addFlashAttribute("message", "Customer " + addCustomerDTO.getPhoneNumber() + " added successfully");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/all/users/" + addCustomerDTO.getUserLogin());
        redirectAttributes.addFlashAttribute("messageError", "Customer " + addCustomerDTO.getPhoneNumber() + " addition failed");
        return modelAndView;
    }


    // Show find customer form
    @RequestMapping (value = "/protected/customers/find", method = RequestMethod.GET)
    public ModelAndView showFindCustomerForm() {

        ModelAndView modelAndView = new ModelAndView();

        CustomerDTO addCustomerDTO = new CustomerDTO();
        modelAndView.addObject("customerDtoForm", addCustomerDTO);

        modelAndView.setViewName("protected/customers/findcustomerform");
        return modelAndView;
    }


    // Do find customers
    @RequestMapping (value = "/protected/customers/find", method = RequestMethod.POST)
    public ModelAndView doFindCustomer(
            @ModelAttribute(name = "customerDtoForm")
//            @Valid
             CustomerDTO customerDtoFindForm
    ) {
        List<Customer> findCustomerList = customerService.findCustomersLikePhoneNumber(customerDtoFindForm.getPhoneNumber());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("findCustomerList", findCustomerList);
        modelAndView.setViewName("protected/customers/customerlist");

        return modelAndView;
    }


    // Do find customers Via Ajax
    @RequestMapping (value = "/protected/customers/findajax", method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> doFindCustomerViaAjax(
            @RequestParam(name = "search")
            String search
    ) {
        if ("".equals(search)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Customer> findCustomerList = customerService.findCustomersLikePhoneNumber(search.trim());

        if (findCustomerList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CustomerDTO> findCustomerListDto = AssemblerCustomerDTO.getInstance().getCustomerDToList(findCustomerList);

        return new ResponseEntity<>(findCustomerListDto, HttpStatus.OK);
    }


}
