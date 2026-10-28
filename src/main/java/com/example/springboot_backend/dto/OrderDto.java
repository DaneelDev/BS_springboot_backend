package com.example.springboot_backend.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrderDto {
    private Long id;
    private String orderNumber;
    private Instant createdAt;
    private String status; // например: PENDING, PAID, SHIPPED
    private ProductSummaryDto summary;
}

// Вспомогательный DTO для краткой сводки по товарам в заказе
@Data
class ProductSummaryDto {
    private String productName;
    private int quantity;
    private double unitPrice;
}
