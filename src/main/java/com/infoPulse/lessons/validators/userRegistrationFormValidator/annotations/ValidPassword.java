package com.infoPulse.lessons.validators.userRegistrationFormValidator.annotations;


import com.infoPulse.lessons.validators.userRegistrationFormValidator.validators.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Documented
public @interface ValidPassword {

    String message() default "{error.enterInvalidPassword}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
