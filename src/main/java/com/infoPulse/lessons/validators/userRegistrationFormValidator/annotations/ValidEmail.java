package com.infoPulse.lessons.validators.userRegistrationFormValidator.annotations;

import com.infoPulse.lessons.validators.userRegistrationFormValidator.validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE, FIELD, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

//    String message() default "Invalid Email";
    String message() default "{error.enterInvalidEmail}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
