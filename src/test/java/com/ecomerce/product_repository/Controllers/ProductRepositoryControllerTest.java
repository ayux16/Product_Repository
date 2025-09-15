package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.Repository.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryControllerTest {

    @Autowired
    private ProductRepositoryController pc;
    @Test
    void getProductById() {
        assertThrows(ProductNotFoundException.class,
                ()->pc.getProductById(-1));

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void deleteProducts() {
    }

    @Test
    void getProductByPage() {
    }
}