package com.lmora.springboot.test.client_api.springboot_client.respositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.lmora.springboot.test.client_api.springboot_client.entities.ClientLogin;

import jakarta.transaction.Transactional;

public interface ClientLoginRepository extends CrudRepository<ClientLogin, UUID> {

    ClientLogin findByClientId(UUID clientId);    
 
    @Transactional
    void deleteByClientId(UUID clientId);
    
    @Transactional
    boolean existsByClientId(UUID clientId);
   
}
