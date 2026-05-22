package com.ecomerce.product_repository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductRepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductRepositoryApplication.class, args);
    }

}
