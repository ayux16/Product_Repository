package com.ecomerce.product_repository.Modells;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Products extends BaseModel{
    @Column(name = "title", nullable = false) // Ensure this is mapped correctly
    private String title;
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
