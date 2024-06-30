package com.lmora.springboot.test.client_api.springboot_client.models.dto;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseDTO {
    private Map<String, String> error;

    public ErrorResponseDTO() {
        error = new HashMap<>();
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(String errorIn) {
        this.error.put("mensaje", String.join("mensaje", errorIn));        
    }

}
