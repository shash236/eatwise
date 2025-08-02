package com.eatwise.eatwise_api.infrastructure.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatwise.eatwise_api.infrastructure.auth.dto.AuthResponse;
import com.eatwise.eatwise_api.infrastructure.auth.dto.RequestOtpRequest;
import com.eatwise.eatwise_api.infrastructure.auth.dto.VerifyOtpRequest;
import com.eatwise.eatwise_api.infrastructure.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/request-otp")
    public ResponseEntity<Void> requestOtp(@RequestBody RequestOtpRequest request) {
        authService.sendOtp(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponse> verifyOtp(@RequestBody VerifyOtpRequest request) {
        AuthResponse response = authService.verifyOtpAndGenerateToken(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(response);
    }
}

