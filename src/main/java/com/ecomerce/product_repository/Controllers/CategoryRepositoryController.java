package com.ecomerce.product_repository.Controllers;

import com.ecomerce.product_repository.FakeStoreResponseDTO.CategoryRequestDTO;
import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Service.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryRepositoryController {
    CategoryService categoryService;
    public CategoryRepositoryController(@Qualifier("selfcategoryservice") CategoryService category) {
        this.categoryService = category;
    }
    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable("id") Integer id) {
        Category category = categoryService.findById(id);
        return category;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@RequestBody List<CategoryRequestDTO> categoryRequestDTO) {
       for(CategoryRequestDTO dto : categoryRequestDTO) {
           categoryService.createCategory(dto.getTitle());
       }
        return ResponseEntity.ok("Created SuccessFully");
    }
@PutMapping("/category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id")Integer id,
                                                             @RequestBody CategoryRequestDTO categoryRequestDTO) {
        ResponseEntity<Category> cat=categoryService.updateCategory(id,categoryRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(cat.getBody());
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Integer id) {
        if(categoryService.findById(id) != null) {
            categoryService.deleteCategory(id);
            return ResponseEntity.ok("Category Deleted Successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
