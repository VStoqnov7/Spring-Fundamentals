package com.example.pathfinder.validations.loginValidation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = UserLoginValidator.class)
public @interface UserValidateLoginForm {

    String message() default "Invalid username or password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
