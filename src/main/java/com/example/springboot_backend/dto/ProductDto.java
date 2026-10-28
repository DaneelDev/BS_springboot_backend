package com.example.springboot_backend.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String category;
    private double price;
    private int stockQuantity;
    private String description;
}
