package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.FakeStoreResponseDTO.CategoryRequestDTO;
import com.ecomerce.product_repository.Modells.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService{
    public Category findById(Integer id);
    public List<Category> getAllCategory();
    public Category createCategory(String categoryTitle);
    public ResponseEntity<String> deleteCategory(Integer id);
    public ResponseEntity<Category> updateCategory(Integer id, CategoryRequestDTO categoryTitle);
}