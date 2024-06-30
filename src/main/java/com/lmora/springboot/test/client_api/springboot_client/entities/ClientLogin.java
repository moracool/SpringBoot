package com.lmora.springboot.test.client_api.springboot_client.entities;

import java.time.LocalDateTime;
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
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="client_login")
public class ClientLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne (cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name="client_id")    
    private Client client;

    private String login_name;
    private String password_hash;
    private String password_salt;
    
    @Lob
    private String token_acces;
    
    private LocalDateTime token_generation_time;    
    private boolean isactive;
    private LocalDateTime last_login;
    
    @CreationTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private LocalDateTime modifiedAt;

    
    public ClientLogin() {
    }

    public ClientLogin(Client client, String password_hash, boolean isactive) {
        this.client = client;
        this.password_hash = password_hash;
        this.isactive = isactive;
    }

    public ClientLogin(Client client, String password_hash, String token_acces, LocalDateTime token_generation_time,
            boolean isactive, LocalDateTime last_login, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.client = client;
        this.password_hash = password_hash;
        this.token_acces = token_acces;
        this.token_generation_time = token_generation_time;
        this.isactive = isactive;
        this.last_login = last_login;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public ClientLogin(String login_name, String password_hash, String password_salt,
            String token_acces, LocalDateTime token_generation_time, boolean isactive, LocalDateTime createdAt,
            LocalDateTime modifiedAt) {
        this.login_name = login_name;
        this.password_hash = password_hash;
        this.password_salt = password_salt;
        this.token_acces = token_acces;
        this.token_generation_time = token_generation_time;
        this.isactive = isactive;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }   
    public String getLogin_name() {
        return login_name;
    }
    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }
    public String getPassword_hash() {
        return password_hash;
    }
    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    public String getPassword_salt() {
        return password_salt;
    }
    public void setPassword_salt(String password_salt) {
        this.password_salt = password_salt;
    }
    public String getToken_acces() {
        return token_acces;
    }
    public void setToken_acces(String token_acces) {
        this.token_acces = token_acces;
    }
    public LocalDateTime getToken_generation_time() {
        return token_generation_time;
    }
    public void setToken_generation_time(LocalDateTime token_generation_time) {
        this.token_generation_time = token_generation_time;
    }
    public boolean getIsactive() {
        return isactive;
    }
    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDateTime last_login) {
        this.last_login = last_login;
    }

    @Override
    public String toString() {
        return "{id=" + id + ", client=" + client + ", login_name=" + login_name + ", password_hash="
                + password_hash + ", password_salt=" + password_salt + ", token_acces=" + token_acces
                + ", token_generation_time=" + token_generation_time + ", isactive=" + isactive + ", last_login="
                + last_login + ", createdAt=" + createdAt + ", modifiedAt=" + modifiedAt + "}";
    }



    
    
}
