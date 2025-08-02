package com.eatwise.eatwise_api.infrastructure.auth.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String email;
    private String otp;
}

