package com.example.springboot_backend.exception;

public class BusinessRuleViolationException extends ApiException {
    public BusinessRuleViolationException(String message) {
        super(message, 400); // чаще всего это 400, но можно и 422, если хочешь
    }
}
