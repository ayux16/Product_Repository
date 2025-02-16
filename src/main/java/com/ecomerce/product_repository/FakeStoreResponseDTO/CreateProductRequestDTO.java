package com.ecomerce.product_repository.FakeStoreResponseDTO;

import com.ecomerce.product_repository.Modells.Category;

public class CreateProductRequestDTO {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private CategoryRequestDTO category;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public CategoryRequestDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryRequestDTO category) {
        this.category = category;
    }
}
