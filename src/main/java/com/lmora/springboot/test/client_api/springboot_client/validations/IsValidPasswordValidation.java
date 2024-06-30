package com.lmora.springboot.test.client_api.springboot_client.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsValidPasswordValidation implements ConstraintValidator<IsValidPassword, String>{
    
    @Value("${config.data.client.password.regexp}")
    private String patternString;
    //@Value("${config.data.client.password.message}")
    //String patternStringMessage;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(value);
        try {  
            if (!matcher.matches()) {              
                return false;
            } 
        } catch (Exception e) {
            return false;
        }
       return true;
    }

}
