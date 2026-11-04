package com.ecommerce.user.services.products;

import com.ecommerce.user.dto.products.ProductRequest;
import com.ecommerce.user.dto.products.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);

    Optional<ProductResponse> getProductById(Long id);

    List<ProductResponse> getAllProducts();

    void deleteProduct(Long id);

    ProductResponse updateProduct(Long id, ProductRequest productRequest);
}
