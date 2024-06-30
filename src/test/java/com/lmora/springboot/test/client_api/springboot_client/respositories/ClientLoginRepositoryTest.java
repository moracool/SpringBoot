package com.lmora.springboot.test.client_api.springboot_client.respositories;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.entities.ClientLogin;
import com.lmora.springboot.test.client_api.springboot_client.entities.Phone;


public class ClientLoginRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    ClientLoginRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp(){

        Client client = new Client("Luis","test1@gmail.com");

        Phone phone1 = new Phone("1234567","1","57");
        Phone phone2 = new Phone("1234567","1","57");

        client.getPhones().add(phone1);
        client.getPhones().add(phone2);

        clientRepository.save(client);

        String generico = passwordEncoder.encode("123467454");

        // public ClientLogin(Client client, String password_hash, String token_acces, LocalDateTime token_generation_time,
        // boolean isactive, LocalDateTime last_login, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        ClientLogin clientLogin = new ClientLogin(client, generico, generico, LocalDateTime.now(), true, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
                
        repository.save(clientLogin);

        Client client2 = new Client("Pedro","test1@gmail.com");

        Phone phone3 = new Phone("23499","99","999");
        
        client2.getPhones().add(phone3);
        
        clientRepository.save(client2);

        generico = passwordEncoder.encode("123467454");

        // public ClientLogin(Client client, String password_hash, String token_acces, LocalDateTime token_generation_time,
        // boolean isactive, LocalDateTime last_login, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        ClientLogin clientLogin2 = new ClientLogin(client, generico, generico, LocalDateTime.now(), true, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
                
        repository.save(clientLogin2);


    }

    @Test
    void testDeleteByClientId() {

    }

    @Test
    void testExistsByClientId() {

    }

    @Test
    void testFindByClientId() {

    }
}
