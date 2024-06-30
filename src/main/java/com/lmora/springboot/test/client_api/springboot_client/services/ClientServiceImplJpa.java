package com.lmora.springboot.test.client_api.springboot_client.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.respositories.ClientRepository;

@Service
public class ClientServiceImplJpa implements ClientService{

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<Client> findAll() {        
        return (List<Client>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Client> findById(UUID id) {        
        return repository.findById(id);
    }

    @Override
    public Client save(Client client) {        
        return repository.save(client);
    }

    @Transactional
    @Override
    public Optional<Client> update(UUID id, Client client) {
        Optional<Client> clientCurrent = repository.findById(id);                
        if (clientCurrent.isPresent()) {
            Client cli = clientCurrent.orElseThrow();

            cli.setName(client.getName());
            cli.setEmail(client.getEmail());   
            return Optional.of(repository.save(cli));      
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<Client> delete(UUID id) {
        Optional<Client> client = repository.findById(id);                
        client.ifPresent(cli-> {            
            repository.delete(cli);
        });        
        return client;        
        
    }

    @Transactional (readOnly = true)
    @Override
    public boolean existsByEmail (String email){
        return repository.existsByEmail(email);
    }
    
}