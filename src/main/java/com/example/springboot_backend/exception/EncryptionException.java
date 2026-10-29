package com.example.springboot_backend.exception;

public class EncryptionException extends ApiException {
    public EncryptionException(String message) {
        super(message, 500);
    }
}
