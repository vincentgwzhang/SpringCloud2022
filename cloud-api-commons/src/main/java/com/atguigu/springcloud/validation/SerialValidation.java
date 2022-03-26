package com.atguigu.springcloud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SerialValidation implements ConstraintValidator<SerialConstraint, String> {

    @Override
    public void initialize(SerialConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.startsWith("Error")) {
            return false;
        }
        return true;
    }

}
