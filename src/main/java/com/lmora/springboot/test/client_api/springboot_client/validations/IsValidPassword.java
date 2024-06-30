package com.lmora.springboot.test.client_api.springboot_client.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsValidPasswordValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidPassword {
		
    String message() default "tiene formato Incorrecto";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}