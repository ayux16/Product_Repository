package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.FakeStoreResponseDTO.CategoryRequestDTO;
import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Repository.CategoryRepos;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfcategoryservice")
public class SelfCategoryService implements CategoryService{


    private CategoryRepos categoryRepos;
    SelfCategoryService(CategoryRepos categoryRepos) {
        this.categoryRepos = categoryRepos;
    }

    @Override
    public Category findById(Integer id){
        Category category=categoryRepos.findById(id).get();
        return category;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList=categoryRepos.findAll();
        return categoryList;
    }

    @Override
    public Category createCategory(String categoryTitle) {
        Category category=new Category();
        Optional<Category> optionalCategory=categoryRepos.findByTitle(categoryTitle);
        if(optionalCategory.isPresent()){
            category=optionalCategory.get();
        }
        else{
            category.setTitle(categoryTitle);
            categoryRepos.save(category);
        }
        return category;
    }

    @Override
    public ResponseEntity<String> deleteCategory(Integer id) {
        if(categoryRepos.findById(id).isPresent()){
            categoryRepos.deleteById(id);
            return ResponseEntity.ok("Category Deleted Successfully");
        }
        return ResponseEntity.status(404).body("Category Not Found");
    }

    @Override
    public ResponseEntity<Category> updateCategory(Integer id, CategoryRequestDTO categoryTitle) {

        Category category=new Category();
        Optional<Category> optionalCategory=categoryRepos.findById(id);
        if(optionalCategory.isPresent()){
            category=optionalCategory.get();
            category.setTitle(categoryTitle.getTitle());
            categoryRepos.save(category);
            return ResponseEntity.ok(category);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
