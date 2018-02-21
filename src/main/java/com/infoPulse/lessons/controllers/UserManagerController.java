package com.infoPulse.lessons.controllers;

import com.infoPulse.lessons.model.entity.Customer;
import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.model.service.CustomerServiceImpl;
import com.infoPulse.lessons.model.service.RoleServiceImpl;
import com.infoPulse.lessons.model.service.UserServiceImpl;
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

/**
 * @author Danny Briskin (sql.coach.kiev@gmail.com)
 *         on  14.07.2017 for spingSecurityAdv project.
 */

@RestController
public class UserManagerController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    RoleServiceImpl roleServiceImpl;
    @Autowired
    CustomerServiceImpl customerServiceImpl;

    @Autowired
    Logger logger;



    @RequestMapping(value = "/newlogin", method = RequestMethod.GET)
    public ModelAndView getLogin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("newlogin");
        return modelAndView;
    }


//    @RequestMapping(value = "/protected/loginhome", method = RequestMethod.GET)
//    public ModelAndView getLoginHome() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("protected/loginhome");
//        return modelAndView;
//    }


    @RequestMapping(value = "/all/userhome", method = RequestMethod.GET)
    public ModelAndView getUserHome() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        ModelAndView modelAndView = new ModelAndView();

        List<Customer> customerList = customerServiceImpl.findCustomersForUser(auth.getName());
        modelAndView.addObject("customerList", customerList);

        Customer customer = new Customer();
        modelAndView.addObject("selectedCustomer", customer);

        modelAndView.setViewName("all/users/userhome");
        return modelAndView;
    }


    @RequestMapping(value = "/protected/users/{login}", method = RequestMethod.GET)
    public ModelAndView getUserHomeByLogin(
            @PathVariable("login")
                    String login
    ) {

        ModelAndView modelAndView = new ModelAndView();

        User user = userServiceImpl.findUserByLogin(login);
        modelAndView.addObject("selectedUser", user);

        List<Customer> customerList = customerServiceImpl.findCustomersForUser(login);
        modelAndView.addObject("customerList", customerList);

        Customer customer = new Customer();
        modelAndView.addObject("selectedCustomer", customer);

        modelAndView.setViewName("all/users/userhome");
        return modelAndView;
    }


    @RequestMapping (value = "/protected/users/userlist", method = RequestMethod.GET)
    public ModelAndView getUserList (ModelAndView modelAndView
//    , @RequestParam(name = "customMessage2") String customMessage
    ) {

//        System.out.println("@@@@Custom Message: " + customMessage);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("/protected/users/userlist:" +
                    "\nUser Credentials: " + auth.getCredentials() +
                    "\nUser Authorities: " + auth.getAuthorities() +
                    "\nUser Details: " + auth.getDetails() +
                    "\nUser: " + auth.getName() + auth.getPrincipal().toString()
                    );

            logger.info("auth.getAuthorities().contains(new SimpleGrantedAuthority(\"ROLE_USER\")): " + auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
            logger.info("auth.getAuthorities().contains(\"ROLE_ADMIN\")): " + auth.getAuthorities().contains("ROLE_ADMIN"));
        }

//        System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER")));
//        System.out.println(auth.getAuthorities().contains("ROLE_ADMIN"));

        modelAndView.addObject("userList", userServiceImpl.findAll());
        modelAndView.setViewName("protected/users/userlist");
        return modelAndView;
    }


    @RequestMapping (value = "/users/{login}/update", method = RequestMethod.GET)
    public ModelAndView showUpdateUserForm(
            @PathVariable("login")
                    String login
    ) {
        ModelAndView modelAndView = new ModelAndView();

        // If user has role ROLE_ADMIN
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            modelAndView.addObject("roleList", roleServiceImpl.findAll());
            System.out.println("Contoller.showUpdateUserForm roleServiceImpl.findAll() : " + roleServiceImpl.findAll());
        }

        modelAndView.addObject("updateUser", userServiceImpl.findUserByLogin(login));
        modelAndView.setViewName("protected/users/userupdateform");
        return modelAndView;
    }


    @RequestMapping (value = "/users/{login}/update", method = RequestMethod.POST)
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
        userServiceImpl.updateRoles(login, user.getRoleList());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("customMessage2", "User update was failed with ");
            return new ModelAndView("redirect:/protected/users/userlist");
        }
        redirectAttributes.addFlashAttribute("customMessage2", "User updated successfully ");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/protected/users/userlist");
        return modelAndView;
    }


    @RequestMapping (value = "/users/{login}/delete", method = RequestMethod.GET)
    public ModelAndView doDeleteUser(
            RedirectAttributes redirectAttributes
            ,@PathVariable("login")
                    String login
    ) {
        userServiceImpl.deleteUser(login);
        redirectAttributes.addFlashAttribute("customMessage2", "User was deleted ");
        return new ModelAndView("redirect:/protected/users/userlist");
    }

}
