package com.lmora.springboot.test.client_api.springboot_client.services;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;

public interface JwtService {
    String generateToken(Client client);
}
