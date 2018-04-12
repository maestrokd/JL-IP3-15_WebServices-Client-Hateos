package com.infoPulse.lessons.validators.userLoginValidatorAtCustomerUpdateForm.annotations;


import com.infoPulse.lessons.validators.userLoginValidatorAtCustomerUpdateForm.validatorClasses.UserLoginExistsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserLoginExistsValidator.class)
@Documented
public @interface ValidLoginIsExist {

    String message() default "{error.loginNotExists}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
