package com.lmora.springboot.test.client_api.springboot_client.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;

@Service
public interface ClientService {

    List<Client> findAll();

    Optional<Client> findById(UUID id);

    Client save (Client client);
    
    Optional<Client> update (UUID id, Client client);

    Optional<Client> delete (UUID id);

    boolean existsByEmail(String email);
    
}

