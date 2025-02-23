package com.ecomerce.product_repository.Modells;

import jakarta.persistence.*;

@Entity
public class Products extends BaseModel{
    @Column(name = "title", nullable = false) // Ensure this is mapped correctly
    private String title;
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;

    public String getTitle() {
        return this.title;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
