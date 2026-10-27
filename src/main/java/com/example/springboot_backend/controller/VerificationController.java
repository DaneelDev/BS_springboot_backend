package com.example.springboot_backend.controller;

import com.example.springboot_backend.dto.SendVerificationRequest;
import com.example.springboot_backend.dto.VerifyVerificationRequest;
import com.example.springboot_backend.service.VerificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/verification")
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationService verificationService;

    @PostMapping("/send-email")
    public ResponseEntity<?> sendEmail(@Valid @RequestBody SendVerificationRequest request) {
        verificationService.sendVerificationCode(request.getEmail());
        return ResponseEntity.ok().body("{\"message\":\"Код отправлен на ваш email\"}");
    }

    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@Valid @RequestBody VerifyVerificationRequest request) {
        boolean verified = verificationService.verifyCode(request.getEmail(), request.getCode());
        if (verified) {
            return ResponseEntity.ok().body("{\"message\":\"Код успешно проверен.\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"Код не найден.\"}");
        }
    }
}
