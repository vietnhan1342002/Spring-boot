package com.example.demo.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(
   validatedBy = {DoBValidator.class}
)
public @interface DoBContraint {
    String message() default "Invalid date of birth";

    
   int min();

    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
}
