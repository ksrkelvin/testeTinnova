package com.exercicio5apicadastroveiculos.validation;

import com.exercicio5apicadastroveiculos.entity.Brand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class BrandValidator implements ConstraintValidator<ValidateBrand, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true;
        try {
            Brand.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
