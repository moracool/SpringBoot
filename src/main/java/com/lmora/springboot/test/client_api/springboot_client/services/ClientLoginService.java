package com.lmora.springboot.test.client_api.springboot_client.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.lmora.springboot.test.client_api.springboot_client.entities.ClientLogin;

@Service
public interface ClientLoginService {

    ClientLogin save( ClientLogin clientLogin);

    List<ClientLogin> findAll();

    Optional<ClientLogin> findById(UUID id);
    
    ClientLogin findByClientId(UUID id);

    void deleteByClientId (UUID id);

    boolean existsByClientId (UUID id);
}
