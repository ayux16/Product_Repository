package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Service.FakeStoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRepositoryController {

    private FakeStoreService fakeService;
    public ProductRepositoryController(FakeStoreService fakeStoreService) {
        this.fakeService = fakeStoreService;
    }
//    @Autowired
//    private FakeProductServices fakeProductServices;

    @GetMapping("/product/{id}")
    public Products getProductById(@PathVariable("id") Integer id) {
        if(id==null){
            throw new IllegalArgumentException("id cannot be null");
        }
        return fakeService.getProductsById(id);
    }
    @PostMapping("/product")
    public void createProduct(){

    }
//    @GetMapping("/product/")
//    public List<Products> getAllProducts(){
//        return fakeProductServices.getAllProducts();
//    }
    @PutMapping("/product/{id}")
    public void updateProducts(@PathVariable("id") Integer id){

    }

    @DeleteMapping("/product/{id}")
    public void deleteProducts(@PathVariable("id") Integer id){

    }
}
