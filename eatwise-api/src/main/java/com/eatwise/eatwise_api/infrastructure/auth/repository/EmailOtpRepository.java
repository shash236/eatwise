package com.eatwise.eatwise_api.infrastructure.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, String> {
    Optional<EmailOtp> findByEmail(String email);
    void deleteByEmail(String email);
    
}
