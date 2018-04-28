package com.infoPulse.lessons.core.registration.listeners;

import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.core.registration.events.OnRegistrationCompleteEvent;
import com.infoPulse.lessons.model.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private String domain;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

//        System.out.println("S1: " + token);

        userServiceImpl.createVerificationToken(user, token);

//        System.out.println("S2: ");

        String recipientAddres = user.getEmail();
//        System.out.println("S3: " + recipientAddres);
        String subject = "Registration Confirmation";
//        System.out.println("S4: " + subject);
        String confirmationUrl = event.getAppUrl() + "/userregistrationConfirm?token=" + token;
//        System.out.println("S5: " + confirmationUrl);
        String message = messageSource.getMessage("message.regSuccess", null, event.getLocale());
//        System.out.println("S6: " + message);

//        String domain = messageSource.getMessage("property.domain", null, event.getLocale());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddres);
        simpleMailMessage.setSubject(subject);
//        simpleMailMessage.setText(message + " \r\n " + "http://localhost:8080" + confirmationUrl);
        simpleMailMessage.setText(message + " \r\n " + domain + confirmationUrl);


//        System.out.println(simpleMailMessage.getTo().toString());
//        System.out.println(simpleMailMessage.getSubject());
//        System.out.println(simpleMailMessage.getText());

        javaMailSender.send(simpleMailMessage);
    }
}
