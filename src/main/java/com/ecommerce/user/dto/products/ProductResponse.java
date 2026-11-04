package com.ecommerce.user.dto.products;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String image;
    private boolean active;
}
