package com.example.springboot_backend.exception;

public class ResourceNotFoundException extends ApiException {
    public ResourceNotFoundException(String resource, Long id) {
        super(resource + " with id " + id + " not found", 404);
    }

    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
