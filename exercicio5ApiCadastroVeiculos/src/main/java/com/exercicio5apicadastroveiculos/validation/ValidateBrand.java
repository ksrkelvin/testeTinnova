package com.exercicio5apicadastroveiculos.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = BrandValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateBrand {
    String message() default "Marca inválida. Valores permitidos: TOYOTA,HONDA,FORD,CHEVROLET,VOLKSWAGEN,BMW,FIAT,HYUNDAI,NISSAN,RENAULT";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}