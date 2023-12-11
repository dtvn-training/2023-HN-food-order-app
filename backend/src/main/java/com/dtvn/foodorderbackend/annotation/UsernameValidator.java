package com.dtvn.foodorderbackend.annotation;

import com.dtvn.foodorderbackend.ulti.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {
    @Override
    public void initialize(ValidUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        // TODO : check max,min length
        for (int i = 0; i < name.length(); i++) {
            if (!StringUtil.CHARSET_USERNAME.contains(name.charAt(i) + "")) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Name not valid").addConstraintViolation();
                return false;
            }
        }
        return true;
    }

}
