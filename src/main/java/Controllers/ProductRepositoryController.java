package Controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class ProductRepositoryController {

    @GetMapping("/products/{id}")
    public void getProductById(@PathVariable("id") Integer id) {

    }
    @PostMapping("/products")
    public void createProduct(){

    }
    @GetMapping("/products")
    public void getAllProducts(){

    }
    @PutMapping("/products")
    public void updateProducts(){

    }

    @DeleteMapping("/products")
    public void deleteProducts(){

    }
}
