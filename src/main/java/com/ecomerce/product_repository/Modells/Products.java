package com.ecomerce.product_repository.Modells;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Products {
    private Integer id;
    private String title;
    private String description;
    private String imageUrl;
    private Category category;
}
