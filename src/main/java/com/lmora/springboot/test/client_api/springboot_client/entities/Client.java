package com.lmora.springboot.test.client_api.springboot_client.entities;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //@NotEmpty
    //@Size(min=3, max=45)
    private String name;

    //@NotEmpty
    //@Pattern(regexp = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$" , message = "${config.data.client.passwors.regexp}") //debe tener un formato valido example@dominio.cl")
    //@IsExistsDB  
    private String email;   

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="client_id")    
    private List<Phone> phone;
    
    //private Date createdAt;
    //private Date modifiedAt;
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;
    
    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;
        
    public Client() {
        phone = new ArrayList<>();
    }

    public Client(String name, String email) {
        this.phone = new ArrayList<>();
        this.name = name;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
     public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime created) {
        this.createdAt = created;
    }
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modified) {
        this.modifiedAt = modified;
    }
    
    public List<Phone> getPhones() {
        return phone;
    }

    public void setPhones(List<Phone> phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", name=" + name + ", email=" + email + ", phones=" + phone + ", createdAt="
                + createdAt + ", modifiedAt=" + modifiedAt + "}";
    }

    
      
}

