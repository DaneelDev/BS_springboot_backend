package com.example.springboot_backend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private boolean isVerified;
    private Instant registeredAt;
}
