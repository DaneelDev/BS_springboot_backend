package com.example.springboot_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    @NotBlank(message = "Recipient name is required")
    private String recipientName;

    @NotBlank(message = "Recipient phone is required")
    private String recipientPhone;

    @NotBlank(message = "Address is required")
    private String recipientAddress;

    // Если у тебя есть отдельный DTO ProductItem, используй его. Пока оставим
    // String для простоты.
    private List<String> productIds;

    @Positive(message = "Total quantity must be positive")
    private Integer totalQuantity;

    @Positive(message = "Total price must be positive")
    private Double totalPrice;
}
