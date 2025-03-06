package com.example.demo.validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DoBValidator implements ConstraintValidator<DoBContraint,LocalDate> {
    private int min;

    @Override
    public void initialize(DoBContraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        min = constraintAnnotation.min();
    }


    @Override
    public boolean isValid(LocalDate arg0, ConstraintValidatorContext arg1) {
        long years = ChronoUnit.YEARS.between(arg0, LocalDate.now());
        return years>=min;
    }
    
}
