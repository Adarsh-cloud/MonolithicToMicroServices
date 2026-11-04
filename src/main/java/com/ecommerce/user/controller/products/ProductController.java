package com.ecommerce.user.controller.products;

import com.ecommerce.user.dto.products.ProductResponse;
import com.ecommerce.user.dto.products.ProductRequest;
import com.ecommerce.user.services.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World";
    }

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductResponse>createProduct(@RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.createProduct(productRequest), HttpStatus.CREATED);

    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        Optional<ProductResponse>productResponse=productService.getProductById(id);
        return productResponse.map(response -> new ResponseEntity<>(response, HttpStatus.OK)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductResponse>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return  new ResponseEntity<>("Product delete successfully",HttpStatus.OK);
    }

}
