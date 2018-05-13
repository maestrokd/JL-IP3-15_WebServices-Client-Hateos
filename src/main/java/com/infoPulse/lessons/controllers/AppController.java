package com.infoPulse.lessons.controllers;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

import java.util.Enumeration;


@RestController
public class AppController {

    // Fields
    private Logger logger;


    // Setters
    @Autowired
    public void setLogger(Logger logger) {
        this.logger = logger;
    }


    // Methods
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getIndex(HttpSession httpSession) {

        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            logger.info(attributeNames.nextElement());
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        return modelAndView;
    }


    @RequestMapping(value = {"/user**"}, method = {RequestMethod.GET})
    public ModelAndView welcomePage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("User: " + auth.getName()
                    + " Principal: " + auth.getPrincipal().toString());
        }

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security - user page");
        model.addObject("message", "User Page !");
        model.setViewName("user");
        return model;
    }

    @RequestMapping(value = "/protected**", method = RequestMethod.GET)
    public ModelAndView protectedPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security - Admin page");
        model.addObject("message", "This is protected page - Only for Admin Users!");
        model.setViewName("protected/protected");
        return model;

    }

    @RequestMapping(value = "/confidential**", method = RequestMethod.GET)
    public ModelAndView confidentialPage() {


        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            logger.info("User: " + auth.getName()
                    + " Principal: " + auth.getPrincipal().toString());
        }


        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security - SuperAdmin page");
        model.addObject("message", "This is confidential page - Need Super Admin Role!");
        model.setViewName("confidential");

        return model;

    }


    // for 403 access denied page
    @RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
    public ModelAndView accessDenied(Principal user) {

        ModelAndView model = new ModelAndView();

        if (user != null) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg","You do not have permission to access this page!");
        }

        model.setViewName("accessDenied");
        return model;

    }















}
