package com.ecomerce.product_repository.Service;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.CreateProductRequestDTO;
import com.ecomerce.product_repository.Modells.Category;
import com.ecomerce.product_repository.Modells.Products;
import com.ecomerce.product_repository.FakeStoreResponseDTO.FakeStoreResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
//service with bean name
@Service("fakeStoreService")
public class FakeStoreService implements ProductService {

    public RestTemplate restTemplate;
    public FakeStoreService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
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

    @Override
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

    @Override
    public Products createProducts(String title, String description, String imageUrl,String catTitle) {

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

    @Override
    public Products updateProducts(Integer id, CreateProductRequestDTO request) throws ProductNotFoundException {
        return null;
    }
//
//    @Override
//    public List<Products> updateProducts(Integer id) {
//        return null;
//    }

    @Override
    public ResponseEntity<String> deleteProducts(Integer id) {
        return null;
    }

    @Override
    public Page<Products> getProductByPage(int pageNo, int pageSize) {
        return null;
    }
}
