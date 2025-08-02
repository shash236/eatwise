package com.eatwise.eatwise_api.infrastructure.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Boolean isNewUser;
    private String jwtToken;
}

