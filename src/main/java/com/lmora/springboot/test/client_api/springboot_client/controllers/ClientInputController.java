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
import com.lmora.springboot.test.client_api.springboot_client.models.ClientInput;
import com.lmora.springboot.test.client_api.springboot_client.models.dto.ClientDto;
import com.lmora.springboot.test.client_api.springboot_client.services.ClientInputService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ClientInputController {

    @Autowired
    private ClientInputService service;

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable UUID id){
        Optional<ClientDto> clientCurrent = service.findById(id);  
        
        if(clientCurrent.isPresent()){
            return ResponseEntity.ok( clientCurrent.orElseThrow());
        }
        return  ResponseEntity.notFound().build();        
    }

    @GetMapping("/list")
    public List<ClientDto> list(){       
        return service.findAll();
    }
    
    @PostMapping
    public ResponseEntity<?> create (@Valid @RequestBody ClientInput client, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( service.save(client));
       
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update (@Valid @RequestBody ClientInput client, BindingResult result, @PathVariable UUID id){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<ClientDto> clientOptional = service.update(id,client);
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

