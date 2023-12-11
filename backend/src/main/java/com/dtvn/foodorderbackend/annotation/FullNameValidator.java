package com.dtvn.foodorderbackend.annotation;

import com.dtvn.foodorderbackend.ulti.StringUtil;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<ValidFullName,String> {
    @Override
    public void initialize(ValidFullName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        for (int i = 0; i < s.length(); i++) {
            if (!StringUtil.CHARSET_FULL_NAME.contains(s.charAt(i) + "")) {
                constraintValidatorContext.disableDefaultConstraintViolation();
                constraintValidatorContext.buildConstraintViolationWithTemplate("Name not valid").addConstraintViolation();
                return false;
            }
        }
        return true;
    }
}
