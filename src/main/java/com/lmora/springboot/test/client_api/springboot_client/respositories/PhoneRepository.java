package com.lmora.springboot.test.client_api.springboot_client.respositories;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.lmora.springboot.test.client_api.springboot_client.entities.Phone;

public interface PhoneRepository extends CrudRepository<Phone , UUID> {

      
}

