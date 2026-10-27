package com.example.springboot_backend.controller;

import com.example.springboot_backend.dto.UserRequest;
import com.example.springboot_backend.dto.UserResponse;
import com.example.springboot_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getUser(@RequestParam String email) {
        Optional<UserResponse> user = userService.getUserByEmail(email);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserResponse> createOrUpdateUser(@Valid @RequestBody UserRequest request) {
        UserResponse saved = userService.createOrUpdateUser(request);
        // Если это создание — 201, если обновление — 200. Для простоты возвращаем 200,
        // можно добавить флаг isNew в сервис.
        return ResponseEntity.ok(saved);
    }
}
