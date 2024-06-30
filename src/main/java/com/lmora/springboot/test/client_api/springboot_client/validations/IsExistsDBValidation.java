package com.lmora.springboot.test.client_api.springboot_client.validations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmora.springboot.test.client_api.springboot_client.services.ClientService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDBValidation implements ConstraintValidator<IsExistsDB, String> {

    @Autowired
    private ClientService service;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(service != null){
            return !service.existsByEmail(value);
        }
        
        return true;
      //return value != null && !service.existsByEmail(value);
    }

}
