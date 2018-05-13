package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.model.service.CustomerService;
import com.infoPulse.lessons.model.service.RoleService;
import com.infoPulse.lessons.model.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@RestController
public class UserManagerController {

    // Fields
    private UserService userService;
    private RoleService roleService;
    private CustomerService customerService;
    private Logger logger;


    // Setters
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    // Methods
    @RequestMapping(value = "/newlogin", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        return modelAndView;
    }


    @RequestMapping(value = "/all/userhome", method = RequestMethod.GET)
    public ModelAndView getUserHome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserByLogin(auth.getName());
        modelAndView.addObject("selectedUser", user);

        List<Customer> customerList = customerService.findCustomersForUser(auth.getName());
        modelAndView.addObject("customerList", customerList);

        Customer customer = new Customer();
        modelAndView.addObject("selectedCustomer", customer);

        modelAndView.setViewName("all/users/userhome");
        return modelAndView;
    }


    @RequestMapping(value = "/all/users/{login}", method = RequestMethod.GET)
    public ModelAndView getUserHomeByLogin(
            @PathVariable("login")
                    String login
    ) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userService.findUserByLogin(login);
        modelAndView.addObject("selectedUser", user);

        List<Customer> customerList = customerService.findCustomersForUser(login);
        modelAndView.addObject("customerList", customerList);

        Customer customer = new Customer();
        modelAndView.addObject("selectedCustomer", customer);

        modelAndView.setViewName("all/users/userhome");
        return modelAndView;
    }


    @RequestMapping(value = "/protected/users/userlist", method = RequestMethod.GET)
    public ModelAndView getUserList(ModelAndView modelAndView
    ) {

        // Section for training
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("/protected/users/userlist:" +
                    "\nUser Credentials: " + auth.getCredentials() +
                    "\nUser Authorities: " + auth.getAuthorities() +
                    "\nUser Details: " + auth.getDetails() +
                    "\nUser: " + auth.getName() + auth.getPrincipal().toString()
            );

            logger.info("auth.getAuthorities().contains(new SimpleGrantedAuthority(\"ROLE_USER\")): " + auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
            logger.info("auth.getAuthorities().contains(\"ROLE_ADMIN\")): " + auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")));
        }


        modelAndView.addObject("userList", userService.findAll());
        modelAndView.setViewName("protected/users/userlist");
        return modelAndView;
    }


    @RequestMapping(value = "/all/users/{login}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateUserForm(
            @PathVariable("login")
                    String login
    ) {
        ModelAndView modelAndView = new ModelAndView();

        // If user has role ROLE_ADMIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.addObject("roleList", roleService.findAll());
        }

        modelAndView.addObject("updateUser", userService.findUserByLogin(login));
        modelAndView.setViewName("protected/users/userupdateform");
        return modelAndView;
    }


    @RequestMapping(value = "/all/users/{login}/update", method = RequestMethod.POST)
    public ModelAndView doUpdateUser(RedirectAttributes redirectAttributes,
                                     @ModelAttribute("userDto")
//            @Valid
                                             User user
            , BindingResult bindingResult
            , WebRequest webRequest
            , @PathVariable("login")
                                             String login
    ) {
        try {
//            userService.updateRoles(login, user.getRoleList());
            userService.updateUser(user);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "User update was failed with ");
            return new ModelAndView("redirect:/all/users/" + login);
        }
        redirectAttributes.addFlashAttribute("message", "User updated successfully ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/all/users/" + login);
        return modelAndView;
    }


    @RequestMapping(value = "/protected/users/{login}/delete", method = RequestMethod.GET)
    public ModelAndView doDeleteUser(
            RedirectAttributes redirectAttributes
            , @PathVariable("login")
                    String login
    ) {
        userService.deleteUser(login);
        redirectAttributes.addFlashAttribute("message", "User was deleted ");
        return new ModelAndView("redirect:/protected/users/userlist");
    }

}
