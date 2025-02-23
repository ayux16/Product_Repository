package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ProductRepositoryController {

    private ProductService service;
    public ProductRepositoryController(@Qualifier("selfProductService") ProductService serve) {
        this.service = serve;
    }

    @GetMapping("/product/{id}")
    public Products getProductById(@PathVariable("id") Integer id) throws ProductNotFoundException {
        if(id==1000){
            throw new IllegalArgumentException("id cannot be 1000");
        }
        Products product=service.getProductsById(id);
        if(product==null){
            throw new ProductNotFoundException("products cannot be null");
        }
        return product;
    }

    @GetMapping("/product/")
    public ResponseEntity<List<Products>> getAllProducts() throws ProductNotFoundException {
        List<Products> products= service.getAllProducts();
        if(products.size()==0){
            throw new IllegalArgumentException("No products found");
        }
        if(products==null){
            throw new ProductNotFoundException("products cannot be null");
        }
        return ResponseEntity.ok(products);//http status code is 200
    }

    @PostMapping("/product")
    public Products createProduct(@RequestBody CreateProductRequestDTO request) throws ProductNotFoundException {
        if(request.getDescription()==null){
            throw new IllegalArgumentException("description cannot be null");
        }
        if(request.getTitle()==null || request.getCategory().getTitle()==null){
            throw new IllegalArgumentException("title cannot be null");
        }
        return service.createProducts(
                request.getTitle(),
                request.getDescription(),
                request.getImageUrl(),
                request.getCategory().getTitle()
                );
    }
//    @PutMapping("/product/{id}")
//    public ResponseEntity<Products> updateProducts(@RequestBody CreateProductRequestDTO request) Integer id){
//        //service.updateProducts()
//        return ResponseEntity.ok(service.getProductsById(id));
//    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProducts(@PathVariable("id") Integer id) {
        // Check if the product exists
        if (service.getProductsById(id) != null) {
            // Call the service to delete the product
            service.deleteProducts(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Product not found with ID: " + id); // 404 Not Found
        }
    }
}
