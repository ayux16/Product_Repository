package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Repository.CategoryRepos;
import com.ecomerce.product_repository.Repository.ProductRepo;
import com.ecomerce.product_repository.Repository.Projections.ProductsProjections;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        ProductsProjections res = productRepo.findByTitle("Google");

        if (res != null) {
            System.out.println("Projection Result:");
            System.out.println("Title: " + res.getTitle());
            System.out.println("Image: " + res.getImage());
            System.out.println("Description: " + res.getDescription());
        } else {
            System.out.println("No product found with title 'Google'");
        }

        List<Products> products = productRepo.findAll();
        return products;
    }

    @Override
    public Products createProducts(String title, String description, String imageUrl,String catTitle) throws ProductNotFoundException {
        Category category=new Category();
        Products products = new Products();
        validations(title,description,catTitle,imageUrl);

        Optional<Category> optionalCategory = categoryRepo.findByTitle(catTitle);
        if (optionalCategory.isPresent()) {
            // If the category exists, use it
            category = optionalCategory.get();
        } else {
            // If the category does not exist, create a new one
            category = new Category();
            category.setTitle(catTitle);
            categoryRepo.save(category); // Save the new category to the database
        }

        // ✅ Always set product details (moved outside the if/else)
        products.setTitle(title);
        products.setDescription(description);
        products.setImageUrl(imageUrl);
        products.setCreated_at(new Date());
        products.setUpdated_at(new Date());
        products.setCategory(category);

        // step 3: save product
        return productRepo.save(products);
    }

    @Override
    public Products updateProducts(Integer id, CreateProductRequestDTO request) throws ProductNotFoundException {
        Products existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            existingProduct.setTitle(request.getTitle());
        }
        if (request.getDescription() != null && !request.getDescription().isEmpty()) {
            existingProduct.setDescription(request.getDescription());
        }
        if (request.getImageUrl() != null && !request.getImageUrl().isEmpty()) {
            existingProduct.setImageUrl(request.getImageUrl());
        }

        if (request.getCategory() != null && request.getCategory().getTitle() != null) {
            String catTitle = request.getCategory().getTitle();
            Category category = categoryRepo.findByTitle(catTitle)
                    .orElseGet(() -> {
                        Category newCategory = new Category();
                        newCategory.setTitle(catTitle);
                        return categoryRepo.save(newCategory);
                    });
            existingProduct.setCategory(category);
        }

        existingProduct.setUpdated_at(new Date());
        return productRepo.save(existingProduct);
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

    @Override
    public Page<Products> getProductByPage(int pageNo, int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        Page<Products>pageProduct= productRepo.findAll(pageable);
        pageProduct.getTotalPages();
        pageProduct.getTotalElements();
        pageProduct.getContent();
        return pageProduct;
    }
}

