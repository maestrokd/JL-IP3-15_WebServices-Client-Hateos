package com.infoPulse.lessons.core.registration.listeners;

import com.infoPulse.lessons.model.entity.User;
import com.infoPulse.lessons.core.registration.events.OnRegistrationCompleteEvent;
import com.infoPulse.lessons.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import java.util.UUID;


@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    // Fields
    private UserService userService;
    private MessageSource messageSource;
    private String domain;
    private JavaMailSender javaMailSender;


    // Setters
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    // Methods
    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent onRegistrationCompleteEvent) {
        this.confirmRegistration(onRegistrationCompleteEvent);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();

        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();

        String subject = messageSource.getMessage("message.regConfirmation.subjectEmail", null, event.getLocale());

        String confirmationUrl = event.getAppUrl() + "/userregistrationConfirm?token=" + token;

        String message = messageSource.getMessage("message.regSuccess", null, event.getLocale());

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(recipientAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(message + " \r\n " + domain + confirmationUrl);

        javaMailSender.send(simpleMailMessage);
    }
}
