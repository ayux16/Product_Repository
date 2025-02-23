package com.ecomerce.product_repository.Modells;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel {

    private String title;

    @OneToMany(mappedBy = "category",cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Products> products;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
