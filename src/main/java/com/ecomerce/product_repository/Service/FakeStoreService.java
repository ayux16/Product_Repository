package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.FakeStoreResponseDTO.FakeStoreResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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

    public List<Products> getAllProducts() {
        List<Products> response=new ArrayList<>();
        ResponseEntity<FakeStoreResponseDTO[]> fakeStoreProducts
                =restTemplate.getForEntity("https://fakestoreapi.com/products/",FakeStoreResponseDTO[].class);

        System.out.println("Status Code "+fakeStoreProducts.getStatusCode());
        System.out.println(" Body length "+fakeStoreProducts.getBody().length);
        for(FakeStoreResponseDTO fakeStoreDTO:fakeStoreProducts.getBody()) {
            response.add(convertFakeStoreResponseDTOToProducts(fakeStoreDTO));
        }
        return response;
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

    public Products createProducts(String title, String description, String catTitle, String imageUrl) {

        FakeStoreResponseDTO requestBody = new FakeStoreResponseDTO();
        requestBody.setTitle(title);
        requestBody.setCategory(catTitle);
        requestBody.setDescription(description);
        requestBody.setImage(imageUrl);
        ResponseEntity<FakeStoreResponseDTO> responseEntity=
            restTemplate.postForEntity("https://fakestoreapi.com/products/", requestBody,
                    FakeStoreResponseDTO.class);
        // get the status code
        System.out.println("Status Code "+responseEntity.getStatusCode());
        //convert the response
        Products response = convertFakeStoreResponseDTOToProducts(responseEntity.getBody());
        return response;
    }
}
