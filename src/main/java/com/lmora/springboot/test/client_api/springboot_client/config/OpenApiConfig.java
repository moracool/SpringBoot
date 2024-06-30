package com.lmora.springboot.test.client_api.springboot_client.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Test Api Resful Client",
        version = "1",
        description = "Desarrolle una aplicación que exponga una API RESTful de creación de usuarios CRUD"
    
    )
)
public class OpenApiConfig {

}
