package com.ecommerce.user.exception;

public class ProductNOTFoundException extends RuntimeException{
    public ProductNOTFoundException(String message) {
        super(message);
    }
}
