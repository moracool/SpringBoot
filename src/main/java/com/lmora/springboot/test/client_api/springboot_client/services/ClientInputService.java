package com.lmora.springboot.test.client_api.springboot_client.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.models.ClientInput;
import com.lmora.springboot.test.client_api.springboot_client.models.dto.ClientDto;

public interface ClientInputService {

    ClientDto save (ClientInput client);
    
    List<ClientDto> findAll();

    Optional<ClientDto> findById(UUID id);

    Optional<ClientDto> update (UUID id, ClientInput client);
    
    Optional<Client> delete (UUID id);

    boolean existsByEmail(String email);
}
