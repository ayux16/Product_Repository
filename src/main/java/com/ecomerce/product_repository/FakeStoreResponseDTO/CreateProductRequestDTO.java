package com.ecomerce.product_repository.FakeStoreResponseDTO;

import com.ecomerce.product_repository.Modells.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private String description;
    private String imageUrl;
    private CategoryRequestDTO category;
}
