package com.eatwise.eatwise_api.infrastructure.auth.repository;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_otp")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailOtp {

    @Id
    private String email;

    private String otp;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}

