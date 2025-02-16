package com.ecomerce.product_repository.Exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {

    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
