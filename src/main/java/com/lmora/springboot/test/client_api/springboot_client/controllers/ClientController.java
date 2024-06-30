package com.lmora.springboot.test.client_api.springboot_client.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.services.ClientService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/client")
public class ClientController {
    
    @Autowired
    private ClientService service;

    @GetMapping("/list")
    public List<Client> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id){
        Optional<Client> clientCurrent = service.findById(id);        
        if(clientCurrent.isPresent()){
            return ResponseEntity.ok( clientCurrent.orElseThrow());
        }
        return  ResponseEntity.notFound().build();

        
    }

    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody Client client, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( service.save(client));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update ( @Valid @RequestBody Client client, BindingResult result, @PathVariable UUID id){
        ///client.setId(id);
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<Client> clientOptional = service.update(id,client);
        if (clientOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CREATED).body(clientOptional.orElseThrow());
        }
        return  ResponseEntity.notFound().build();
    }

    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
      
        Optional<Client> clientCurrent = service.delete(id);        
        if(clientCurrent.isPresent()){
            return ResponseEntity.ok( clientCurrent.orElseThrow());
        }
        return  ResponseEntity.notFound().build();

        
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> erros = new HashMap<>();
        List<String> errores = new ArrayList<String>();

        result.getFieldErrors().forEach(err -> {
            //erros.put(err.getField(), "El campo "+ err.getField() +" " + err.getDefaultMessage());
            errores.add("El campo "+ err.getField() +" " + err.getDefaultMessage());
        }
        );
        erros.put("mensaje", String.join(" ,", errores));

        return ResponseEntity.badRequest().body(erros);
     }

}

