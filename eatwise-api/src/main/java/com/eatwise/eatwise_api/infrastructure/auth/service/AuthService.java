package com.eatwise.eatwise_api.infrastructure.auth.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.eatwise.eatwise_api.infrastructure.auth.dto.AuthResponse;
import com.eatwise.eatwise_api.infrastructure.auth.repository.EmailOtp;
import com.eatwise.eatwise_api.infrastructure.auth.repository.EmailOtpRepository;
import com.eatwise.eatwise_api.user.repository.User;
import com.eatwise.eatwise_api.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmailOtpRepository emailOtpRepo;
    private final UserRepository userRepo;
    private final JwtService jwtService;
    private final JavaMailSender mailSender;

    public void sendOtp(String email) {
        String otp = generateOtp();
        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        emailOtpRepo.save(new EmailOtp(email, otp, expiry));

        sendOtpEmail(email, otp);
    }

    public AuthResponse verifyOtpAndGenerateToken(String email, String otp) {
        EmailOtp record = emailOtpRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (!record.getOtp().equals(otp) || record.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        boolean newUser = false;
        
        User user = userRepo.findByEmail(email).orElse(null);

        if (user == null) {
            user = new User();
            user.setEmail(email);
            newUser = true;
            userRepo.save(user);
        }

        return new AuthResponse(newUser, jwtService.generateToken(user));
    }

    private String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    private void sendOtpEmail(String to, String otp) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("noreply@eatwise.shashwatik.me");
        msg.setTo(to);
        msg.setSubject("Your OTP Code");
        msg.setText("Your OTP is: " + otp);
        mailSender.send(msg);
    }
}

