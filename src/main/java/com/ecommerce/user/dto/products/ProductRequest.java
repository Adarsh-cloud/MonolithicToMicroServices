package com.ecommerce.user.dto.products;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private double price;
    private int stockQuantity;
    private String category;
    private String image;
}
