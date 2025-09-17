package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Commons.AuthUtils;
import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.FakeStoreResponseDTO.UserDTO;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Service.ProductService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductRepositoryController {

    private ProductService service;
    private AuthUtils authUtils;

    public ProductRepositoryController(@Qualifier("selfProductService") ProductService serve, AuthUtils authUtils) {
        this.service = serve;
        this.authUtils = authUtils;
    }

    //get product by id working
    @GetMapping("/{id}")
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


    //working get all product
    @GetMapping("/all/{tokenValue}")
    public ResponseEntity<List<Products>> getAllProducts(@PathVariable String tokenValue) throws ProductNotFoundException {

        UserDTO userDTO=authUtils.ValidateToken(tokenValue);
        ResponseEntity<List<Products>> responseEntity=null;
        if(userDTO==null){
           responseEntity = new ResponseEntity<>
                   (HttpStatus.UNAUTHORIZED);
        }
        List<Products> products = service.getAllProducts();
        responseEntity=new ResponseEntity<>(service.getAllProducts(),
                HttpStatus.OK);
        return responseEntity;
    }


    //create propducts working
    @PostMapping()
    public ResponseEntity<?> createProduct(@RequestBody List<CreateProductRequestDTO> createProductRequestDTO) throws ProductNotFoundException {
        for(CreateProductRequestDTO dto : createProductRequestDTO){
            service.createProducts(dto.getTitle(),
                    dto.getDescription(),
                    dto.getImageUrl(),
                    dto.getCategory().getTitle());
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    @PutMapping("/product/{id}")
//    public ResponseEntity<Products> updateProducts(@RequestBody CreateProductRequestDTO request) Integer id){
//        //service.updateProducts()
//        return ResponseEntity.ok(service.getProductsById(id));
//    }

    //working delete product
    @DeleteMapping("/{id}")
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


    //update working fine
    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody CreateProductRequestDTO request) throws ProductNotFoundException {

        Products updatedProduct = service.updateProducts(id, request);
        return ResponseEntity.ok(updatedProduct);
    }
    //working by page numebr and size
    @GetMapping("/{pageNo}/{pageSize}")
    public ResponseEntity<Page<Products>> getProductByPage(@PathVariable("pageNo") int pageNo,
                                            @PathVariable("pageSize") int pageSize) {
        Page<Products> products= service.getProductByPage(pageNo,pageSize);
        return ResponseEntity.ok(products);
    }
}
