package com.lmora.springboot.test.client_api.springboot_client.models;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lmora.springboot.test.client_api.springboot_client.validations.IsExistsDB;
import com.lmora.springboot.test.client_api.springboot_client.validations.IsValidPassword;

import jakarta.validation.constraints.Email;

//import com.lmora.springboot.test.client_api.springboot_client.validations.IsExistsDB;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class ClientInput {
    
    @NotEmpty
    @Size(min=3, max=45)
    private String name;   
    
    @NotEmpty
    @Email
    @Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$" , message = "debe tener un formato valido example@dominio.cl")
    @IsExistsDB
    private String email;
    
    @IsValidPassword //(message="${config.data.client.password.message}")
    private String password;
    
    @Autowired    
    private List<Phones> phones;

    
    public ClientInput() {
        phones = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Phones> getPhones() {
        return phones;
    }

    public void setPhones(List<Phones> phones) {
        this.phones = phones;
    }

   

}
