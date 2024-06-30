package com.lmora.springboot.test.client_api.springboot_client.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmora.springboot.test.client_api.springboot_client.entities.ClientLogin;
import com.lmora.springboot.test.client_api.springboot_client.respositories.ClientLoginRepository;

@Service
public class ClientLoginServiceImpl implements ClientLoginService{

    @Autowired
    private ClientLoginRepository repository;

    @Override
    public ClientLogin save (ClientLogin client) {        
        return repository.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientLogin> findAll() {        
        return (List<ClientLogin>) repository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ClientLogin> findById(UUID id) {        
        return repository.findById(id);
    }

    @Override
    @Transactional
    public ClientLogin findByClientId(UUID id) {
        return repository.findByClientId(id);
    }

    @Override
    @Transactional 
    public void deleteByClientId(UUID id) {
      repository.deleteByClientId(id);
    }

    @Transactional (readOnly = true)
    @Override
    public boolean existsByClientId (UUID id){
        return repository.existsByClientId(id);
    }

}
