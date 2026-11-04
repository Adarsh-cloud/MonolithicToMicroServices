package com.ecommerce.user.services.products;

import com.ecommerce.user.dto.products.ProductRequest;
import com.ecommerce.user.dto.products.ProductResponse;
import com.ecommerce.user.exception.ProductNOTFoundException;
import com.ecommerce.user.model.products.Product;
import com.ecommerce.user.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private Logger logger=LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
       return createProductDTO(productRepository.save(createProductEntity(productRequest)));

    }

    @Override
    public Optional<ProductResponse> getProductById(Long id) {
        logger.info("Fetching product with id :: "+id);
        return Optional.ofNullable(createProductDTO(productRepository.findById(id).
                orElseThrow(()->new ProductNOTFoundException("Product with id :: "+id +" doesn't exists"))));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        logger.info("Fetching the all products list");
        return productRepository.findAll().stream().map(this::createProductDTO).collect(Collectors.toList());

    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting product with id :: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNOTFoundException("Product with id :: " + id + " doesn't exists"));
        productRepository.delete(product);
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductRequest productRequest) {
       logger.info("Updating product with id :: {}", id);
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNOTFoundException("Product with id :: " + id + " not exists"));
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setDescription(productRequest.getDescription());
        return createProductDTO(product);
    }

    private Product createProductEntity(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setDescription(productRequest.getDescription());
        return  product;
    }
    private ProductResponse createProductDTO(Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setPrice(product.getPrice());
        productResponse.setActive(product.isActive());
        productResponse.setCategory(product.getCategory());
        productResponse.setDescription(product.getDescription());
        productResponse.setImage(product.getImage());
        productResponse.setStockQuantity(product.getStockQuantity());
        productResponse.setName(product.getName());
        return productResponse;
    }
}
