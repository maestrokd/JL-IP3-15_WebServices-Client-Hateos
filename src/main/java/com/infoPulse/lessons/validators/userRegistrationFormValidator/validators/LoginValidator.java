package com.infoPulse.lessons.validators.userRegistrationFormValidator.validators;

import com.infoPulse.lessons.model.service.UserService;
import com.infoPulse.lessons.validators.userRegistrationFormValidator.annotations.ValidLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@PropertySource("classpath:propertyFile.properties")
public class LoginValidator implements ConstraintValidator<ValidLogin, String> {

    // Fields
    private UserService userService;


    // Setters
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // Methods
    @Override
    public void initialize(ValidLogin validLogin) {
    }


    @Override
    public boolean isValid(String login, ConstraintValidatorContext constraintValidatorContext) {
        if (userService.isUserExist(login)) {
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
