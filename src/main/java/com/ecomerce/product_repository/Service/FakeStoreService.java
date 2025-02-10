package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.FakeStoreResponseDTO.FakeStoreResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreService {

    public RestTemplate restTemplate;

    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public Products getProductsById(Integer id) {
        Products product = new Products();

        ResponseEntity<FakeStoreResponseDTO> fakeStoreResponse
                = restTemplate.getForEntity("https://fakestoreapi.com/products/" + id, FakeStoreResponseDTO.class);


        FakeStoreResponseDTO response=fakeStoreResponse.getBody();

        if(response==null) {
            throw new IllegalArgumentException("Can't find product with id "+id);
        }

        product=convertFakeStoreResponseDTOToProducts(response);
        return product;
    }


    private Products convertFakeStoreResponseDTOToProducts(FakeStoreResponseDTO response) {
        Products products = new Products();
        Category category = new Category();
        category.setTitle(response.getCategory());

        products.setId(response.getId());
        products.setTitle(response.getTitle());
        products.setDescription(response.getDescription());
        products.setCategory(category);
        products.setImageUrl(response.getImage());
        return products;
    }
}
