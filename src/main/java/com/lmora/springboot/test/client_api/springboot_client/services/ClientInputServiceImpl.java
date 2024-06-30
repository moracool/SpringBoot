package com.lmora.springboot.test.client_api.springboot_client.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;
import com.lmora.springboot.test.client_api.springboot_client.entities.ClientLogin;
import com.lmora.springboot.test.client_api.springboot_client.entities.Phone;
import com.lmora.springboot.test.client_api.springboot_client.models.ClientInput;
import com.lmora.springboot.test.client_api.springboot_client.models.dto.ClientDto;

@Service
public class ClientInputServiceImpl implements ClientInputService {

    @Autowired
    private ClientService serviceClient; 
    
    @Autowired
    private JwtService jwtService;

    @Autowired
    private ClientLoginService serviceClientLogin;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //private 
    @Transactional
    @Override
    public ClientDto save(ClientInput client) {

        //Set Client Data
        Client newClient = new Client();

        newClient.setName(client.getName());
        newClient.setEmail(client.getEmail());

        List<Phone>  phonesCurrent = new ArrayList<>();
        
        //set Client Phones
        client.getPhones().forEach(p-> {
            Phone ph = new Phone(p.getNumber(), p.getCitycode(),p.getContrycode());
            phonesCurrent.add(ph);             
        });

        newClient.setPhones(phonesCurrent);

        Client saveClient = serviceClient.save(newClient);
        //System.out.println(saveClient.toString()); 

        //Firts time - set Login Data
        ClientLogin loginData = new ClientLogin();
         
        loginData.setClient(saveClient);
        loginData.setPassword_hash(passwordEncoder.encode(client.getPassword()));
        loginData.setLast_login(LocalDateTime.now()); // It is established at login or when the user is created.        
        loginData.setIsactive(true);
        String token = jwtService.generateToken(saveClient);
        loginData.setToken_acces(token) ; //jwtService.generateToken(saveClient));
        loginData.setToken_generation_time(LocalDateTime.now());        
        //System.out.println(token);
        serviceClientLogin.save(loginData);
        
        //set the response with only the required data - DTO
        ClientDto clientRespose = getClientDtoCliet(saveClient);

        return clientRespose;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ClientDto> findAll() { 
        
        List<Client> listClient = serviceClient.findAll();

        List<ClientDto> responseClientDto = new ArrayList<>();

        listClient.forEach(c -> { 
            
            responseClientDto.add(getClientDtoCliet(c));
        
        });
        return responseClientDto;
    }
    
    public ClientDto getClientDtoCliet (Client client){
        //transfor Client to ClientoDto

        ClientLogin clientLogin = serviceClientLogin.findByClientId(client.getId());
        
        ClientDto clientRespose = new ClientDto();
        clientRespose.setClient(client);
        clientRespose.setId(client.getId());
        clientRespose.setIsactive(clientLogin.getIsactive()); 
        clientRespose.setCreated(client.getCreatedAt());
        clientRespose.setModified(client.getModifiedAt());
        clientRespose.setLast_login(clientLogin.getLast_login());
        clientRespose.setToken(clientLogin.getToken_acces());

        System.out.println(clientRespose);
        return clientRespose;           
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<ClientDto> findById(UUID id) { 
        Optional<Client> clientCurrent = serviceClient.findById(id);        
        if(clientCurrent.isPresent()){
            Client c = clientCurrent.orElseThrow();
            return  Optional.of(getClientDtoCliet(c)); 
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<ClientDto> update(UUID id, ClientInput client) {
        Optional<Client> clientCurrent = serviceClient.findById(id);                
        if (clientCurrent.isPresent()) {
            Client clientUpdate = clientCurrent.orElseThrow();

            //Client Data Update
            clientUpdate.setName(client.getName());
            clientUpdate.setEmail(client.getEmail());   

            clientUpdate.getPhones().clear();

            List<Phone>  phonesCurrent = new ArrayList<>();            
            //set Client Phones
            client.getPhones().forEach(p-> {
                Phone ph = new Phone(p.getNumber(), p.getCitycode(),p.getContrycode());
                phonesCurrent.add(ph);             
            });
            
            clientUpdate.getPhones().addAll(phonesCurrent);            
            serviceClient.save(clientUpdate);
            //Client Login Data Update
            ClientLogin loginData = serviceClientLogin.findByClientId(clientUpdate.getId());
                     
            loginData.setPassword_hash(passwordEncoder.encode(client.getPassword()));
            loginData.setToken_acces(jwtService.generateToken(clientUpdate)) ;             
            
            serviceClientLogin.save(loginData);
            
            //set the response with only the required data - DTO
            ClientDto clientRespose = getClientDtoCliet(clientUpdate);

            return Optional.of(clientRespose);      
        }
        return null;
    }

    @Transactional
    @Override
    public Optional<Client> delete(UUID id) {        
       
        Optional<Client> clientCurrent = serviceClient.findById(id);                          
        clientCurrent.ifPresent(cli-> {

            if (serviceClientLogin.existsByClientId(id)) {           
                serviceClientLogin.deleteByClientId(id);
            }
            serviceClient.delete(id);
        });
        
        return clientCurrent;     
    }


    @Transactional (readOnly = true)
    @Override
    public boolean existsByEmail (String email){
        return serviceClient.existsByEmail(email);
    }

}
