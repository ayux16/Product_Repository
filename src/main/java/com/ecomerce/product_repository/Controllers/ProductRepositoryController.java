package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Service.ProductService;
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
    public ProductRepositoryController(@Qualifier("selfProductService") ProductService serve) {
        this.service = serve;
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
    @GetMapping()
    public ResponseEntity<List<Products>> getAllProducts() throws ProductNotFoundException {
        List<Products> products = service.getAllProducts();

        if (products == null) {
            throw new ProductNotFoundException("Products cannot be null");
        }

        if (products.isEmpty()) {
            throw new IllegalArgumentException("No products found");
        }

        return ResponseEntity.ok(products); // 200 OK
    }

 /***   @PostMapping("/product")
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
     @PostMapping("/category")
        public ResponseEntity<?> createCategory(@RequestBody List<CategoryRequestDTO> categoryRequestDTO) {
           for(CategoryRequestDTO dto : categoryRequestDTO) {
               categoryService.createCategory(dto.getTitle());
           }
            return ResponseEntity.ok("Created SuccessFully");
        }
    ***/


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
