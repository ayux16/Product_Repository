package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Products;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public Products getProductsById(Integer id);

    public List<Products> getAllProducts();

    public Products createProducts(String title, String description, String imageUrl,String catTitle) throws ProductNotFoundException;

    Products updateProducts(Integer id, CreateProductRequestDTO request) throws ProductNotFoundException;


    public ResponseEntity<String> deleteProducts(Integer id);
    public Page<Products> getProductByPage(int pageNo, int pageSize);

}
