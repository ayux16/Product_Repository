package com.ecomerce.product_repository.Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRepositoryController {

    @GetMapping("/product/{id}")
    public void getProductById(@PathVariable("id") Integer id) {

    }
    @PostMapping("/product")
    public void createProduct(){

    }
    @GetMapping("/product")
    public void getAllProducts(){

    }
    @PutMapping("/product/{id}")
    public void updateProducts(@PathVariable("id") Integer id){

    }

    @DeleteMapping("/product/{id}")
    public void deleteProducts(@PathVariable("id") Integer id){

    }
}
