package com.example.springboot_backend.exception;

public class VerificationCodeExpiredException extends ApiException {
    public VerificationCodeExpiredException() {
        super("Verification code has expired", 400);
    }
}
