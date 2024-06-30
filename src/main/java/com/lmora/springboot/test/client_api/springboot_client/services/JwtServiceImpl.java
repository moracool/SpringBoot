package com.lmora.springboot.test.client_api.springboot_client.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.lmora.springboot.test.client_api.springboot_client.entities.Client;

@Service
public class JwtServiceImpl implements JwtService{

   public static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    @Override
    public String generateToken(Client client) {

         Claims claims = Jwts.claims()
                .add("email", client.getEmail())
                .add("username", client.getId())
        .build();


        String token = Jwts.builder()
                .subject(client.getId().toString())
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();

        return token;        
    }

}