package com.lmora.springboot.test.client_api.springboot_client.respositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.entities.Phone;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp(){

        Client client = new Client("Luis","test1@gmail.com");

        Phone phone1 = new Phone("1234567","1","57");
        Phone phone2 = new Phone("1234567","1","57");

        client.getPhones().add(phone1);
        client.getPhones().add(phone2);

        clientRepository.save(client);

        Client client2 = new Client("Mauel","test2@gmail.com");

        Phone phone4 = new Phone("3333","1","57");

        client2.getPhones().add(phone4);

        clientRepository.save(client2);

        Client client3 = new Client("Marcos","test3@gmail.com");

        Phone phone5 = new Phone("12344444567","1","57");

        client3.getPhones().add(phone5);

        clientRepository.save(client3);

    }

    @Test
    public void testFind(){

        System.out.println("Test Entity - Client & Phone");

        Iterable<Client> clients = clientRepository.findAll();

        System.out.println(clients.toString());

    } 

    @Test
    public void existsByEmailFound(){

        System.out.println("Test Entity - existsByEmail");

        String email = "test2@gmail.com";
        
        boolean exist = clientRepository.existsByEmail(email);

        assertEquals(true, exist);

        System.out.println("User mail: "+ email+ "  Exist? R=" + exist);

    } 
    @Test
    public void existsByEmailNotFound(){

        String email = "lmora@gmail.com";

        System.out.println("Test Entity - existsByEmail");

        boolean exist = clientRepository.existsByEmail(email);

        assertEquals(false, exist);

        System.out.println("User mail: "+ email+ "  Exist? R=" + exist);

    } 

}
