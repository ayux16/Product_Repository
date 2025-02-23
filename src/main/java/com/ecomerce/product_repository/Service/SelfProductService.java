package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Repository.CategoryRepos;
import com.ecomerce.product_repository.Repository.ProductRepo;
import com.ecomerce.product_repository.Repository.Projections.ProductsProjections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    //Inject Repository as service wont directly intercat with database
    private ProductRepo productRepo;
    private CategoryRepos categoryRepo;
    SelfProductService(ProductRepo productRepo,CategoryRepos categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Products getProductsById(Integer id) {
        Products products = productRepo.findById(id).get();
        return products;
    }

    @Override
    public List<Products> getAllProducts() {
        ProductsProjections res= productRepo.findByTitle("Google");
        System.out.println("Hello brother this is the response hahaha"+res.getTitle()+" "+res.getImage()+"  "+res.getDescription()+"\n");
       List<Products> products = productRepo.findAll();
       return products;
    }

    @Override
    public Products createProducts(String title, String description, String imageUrl,String catTitle) throws ProductNotFoundException {

        Category category=new Category();
        Products products = new Products();

        //step 1 validations
        validations(title,description,catTitle,imageUrl);
        //Set products details

        Optional<Category> optionalCategory = categoryRepo.findByTitle(catTitle);
        if (optionalCategory.isPresent()) {
            // If the category exists, use it
            category = optionalCategory.get();

            products.setTitle(title);
            products.setDescription(description);
            products.setImageUrl(imageUrl);
            products.setCreated_at(new Date());
            products.setUpdated_at(new Date());

        } else {
            // If the category does not exist, create a new one
            category = new Category();
            category.setTitle(catTitle);
            categoryRepo.save(category); // Save the new category to the database
        }

        // Set the category for the product
        products.setCategory(category);

        //step 3: call database;
        Products response=productRepo.save(products);

        return response;
    }

    @Override
    public List<Products> updateProducts(Integer id) {
//        Category category=new Category();
//        Products products = new Products();
//
//        //step 1 validations
//        validations(title,description,catTitle,imageUrl);
//        //Set products details
//
//
//        products.setTitle(title);
//        products.setDescription(description);
//        products.setImageUrl(imageUrl);
//        products.setCreated_at(new Date());
//        products.setUpdated_at(new Date());
//
//        Optional<Category> optionalCategory = categoryRepo.findByTitle(catTitle);
//        if (optionalCategory.isPresent()) {
//            // If the category exists, use it
//            category = optionalCategory.get();
//        } else {
//            // If the category does not exist, create a new one
//            category = new Category();
//            category.setTitle(catTitle);
//            categoryRepo.save(category); // Save the new category to the database
//        }
//
//        // Set the category for the product
//        products.setCategory(category);
//
//        //step 3: call database;
//        Products response=productRepo.save(products);
//
//        return response;
        return null;
    }

    @Override
    public ResponseEntity<String> deleteProducts(Integer id) {
       if(productRepo.existsById(id)) {
           productRepo.deleteById(id);
           return ResponseEntity.ok("Product deleted successfully");
       }

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Product not found with ID: " + id); // 404 Not Found
    }

    private void validations(String title, String description, String catTitle, String imageUrl) throws ProductNotFoundException {
        if(title.isEmpty() || description.isEmpty() || catTitle.isEmpty() || imageUrl.isEmpty()) {
            throw new ProductNotFoundException();
        }
    }
}

