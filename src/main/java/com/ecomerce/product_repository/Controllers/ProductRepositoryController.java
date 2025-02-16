package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Service.FakeStoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductRepositoryController {

    private FakeStoreService fakeService;
    public ProductRepositoryController(FakeStoreService fakeStoreService) {
        this.fakeService = fakeStoreService;
    }

    @GetMapping("/product/{id}")
    public Products getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        if(id==1000){
            throw new IllegalArgumentException("id cannot be 1000");
        }
        Products product=fakeService.getProductsById(id);
        if(product==null){
            throw new ProductNotFoundException("products cannot be null");
        }
        return product;
    }

    @GetMapping("/product/")
    public ResponseEntity<List<Products>> getAllProducts() throws ProductNotFoundException {
        List<Products> products= fakeService.getAllProducts();
        if(products.size()==0){
            throw new IllegalArgumentException("No products found");
        }
        if(products==null){
            throw new ProductNotFoundException("products cannot be null");
        }
        return ResponseEntity.ok(products);//http status code is 200
    }

    @PostMapping("/product")
    public Products createProduct(@RequestBody CreateProductRequestDTO request) {
        if(request.getDescription()==null){
            throw new IllegalArgumentException("description cannot be null");
        }
        if(request.getTitle()==null){
            throw new IllegalArgumentException("title cannot be null");
        }
        return fakeService.createProducts(request.getTitle(), request.getDescription(), request.getCategory().getTitle(),
                request.getImageUrl());

    }
    @PutMapping("/product/{id}")
    public void updateProducts(@PathVariable("id") Integer id){

    }

    @DeleteMapping("/product/{id}")
    public void deleteProducts(@PathVariable("id") Integer id){

    }
}
