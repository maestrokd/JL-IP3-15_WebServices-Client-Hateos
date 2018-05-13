package com.infoPulse.lessons.validators.userLoginValidatorAtCustomerUpdateForm.validatorClasses;

import com.infoPulse.lessons.model.service.UserService;
import com.infoPulse.lessons.validators.userLoginValidatorAtCustomerUpdateForm.annotations.ValidLoginIsExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


@PropertySource("classpath:propertyFile.properties")
public class UserLoginExistsValidator implements ConstraintValidator<ValidLoginIsExist, String> {

    // Fields
    private UserService userService;


    // Setters
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    // Methods
    @Override
    public void initialize(ValidLoginIsExist validLoginIsExist) {}

    @Override
    public boolean isValid(String userLogin, ConstraintValidatorContext constraintValidatorContext) {
        if (!userService.isUserExist(userLogin)) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(
                    "{error.loginNotExists}")

//                    messageSource.getMessage("error.loginExists", null, LocaleContextHolder.getLocale()))
//                    .addPropertyNode("login")

                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
