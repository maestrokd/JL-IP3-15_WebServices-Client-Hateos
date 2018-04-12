package com.infoPulse.lessons.validators.userRegistrationFormValidator.validators;

import com.infoPulse.lessons.validators.userRegistrationFormValidator.annotations.ValidLogin;
import com.infoPulse.lessons.model.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@PropertySource("classpath:propertyFile.properties")
public class LoginValidator implements ConstraintValidator<ValidLogin, String> {

    // Fields
    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void initialize(ValidLogin validLogin) {}

    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if (userServiceImpl.isUserExist(login)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "{error.loginExists}")
//                    messageSource.getMessage("error.loginExists", null, LocaleContextHolder.getLocale()))
//                    .addPropertyNode("login")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
