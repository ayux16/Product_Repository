package com.ecomerce.product_repository.Repository;

import com.ecomerce.product_repository.Modells.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Integer> {

    Optional<Category> findByTitle(String title);

    Optional<Category> findById(int id);
}
