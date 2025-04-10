package com.ecomerce.product_repository.Repository;

import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.Repository.Projections.ProductsProjections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Products,Integer> {

    @Query("select p.title, p.imageUrl,p.description from Products p where p.title =:title")
    ProductsProjections findByTitle(@Param("title") String title);
}

