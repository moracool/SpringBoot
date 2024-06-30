package com.lmora.springboot.test.client_api.springboot_client.respositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;

public interface ClientRepository extends CrudRepository<Client , UUID>{

    boolean existsByEmail(String email);    

} 

