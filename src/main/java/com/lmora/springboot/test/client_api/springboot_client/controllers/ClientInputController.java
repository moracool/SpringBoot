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
import com.lmora.springboot.test.client_api.springboot_client.models.dto.ErrorResponseDTO;
import com.lmora.springboot.test.client_api.springboot_client.services.ClientInputService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name= "ClientInput")
public class ClientInputController {

    @Autowired
    private ClientInputService service;

    private ErrorResponseDTO errorDTO = new ErrorResponseDTO();

    @GetMapping("/{id}")
    @Operation(summary = "Get a Specific Client from DataBase")
    public ResponseEntity<?> get(@PathVariable UUID id){
        Optional<ClientDto> clientCurrent = service.findById(id);  
        
        if(clientCurrent.isPresent()){
            return ResponseEntity.ok( clientCurrent.orElseThrow());
        }
        //return  ResponseEntity.notFound().build();        
        errorDTO.setError("Usuario no Encontrado");     
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO.getError());
    }

    @GetMapping("/list")
    @Operation(summary = "Get a List of Client from DataBase")
    public List<ClientDto> list(){       
        return service.findAll();
    }
    
    @PostMapping
    @Operation(summary = "Save a Client in DataBase and return Data User Login")
    public ResponseEntity<?> create (@Valid @RequestBody ClientInput client, BindingResult result){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        if (service.existsByEmail(client.getEmail()) ) {
            //Personal Note: I tried to do it with Custom @Annotation on the entity but I can't catch the error in the put request
            errorDTO.setError("El correo ya registrado");     
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO.getError());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body( service.save(client));
       
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Client in DataBase and return Data User Login")
    public ResponseEntity<?> update (@Valid @RequestBody ClientInput client, BindingResult result, @PathVariable UUID id){
        if (result.hasFieldErrors()){
            return validation(result);
        }
        Optional<ClientDto> clientOptional = service.update(id,client);
        if ( (clientOptional != null) && (clientOptional.isPresent() ) ){
            return ResponseEntity.status(HttpStatus.CREATED).body(clientOptional.orElseThrow());
        }
        //return  ResponseEntity.notFound().build();       
        errorDTO.setError("Usuario no Encontrado");     
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO.getError());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Client in DataBase and return the Last Data User Login")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        
        Optional<Client> clientCurrent = service.delete(id);        
        if ( (clientCurrent != null) && clientCurrent.isPresent()){
            return ResponseEntity.ok( clientCurrent.orElseThrow());
        }
        //return  ResponseEntity.notFound().build();   
        errorDTO.setError("Usuario no Encontrado");     
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO.getError());
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

