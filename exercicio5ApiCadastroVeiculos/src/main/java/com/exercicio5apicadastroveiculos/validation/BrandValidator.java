package com.exercicio5apicadastroveiculos.validation;

import com.exercicio5apicadastroveiculos.entity.Brand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BrandValidator implements ConstraintValidator<ValidateBrand, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        try {
            Brand.valueOf(value.toUpperCase());  // ignora case se quiser
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}