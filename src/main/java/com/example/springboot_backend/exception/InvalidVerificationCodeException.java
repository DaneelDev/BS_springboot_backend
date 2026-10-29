package com.example.springboot_backend.exception;

public class InvalidVerificationCodeException extends ApiException {
    public InvalidVerificationCodeException() {
        super("Invalid verification code", 400);
    }
}
