//package com.ecomerce.product_repository.Service;
//
//import com.ecomerce.product_repository.FakeStoreResponseDTO.FakeStoreResponseDTO;
//import com.ecomerce.product_repository.Modells.Category;
//import com.ecomerce.product_repository.Modells.Products;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//    import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class FakeProductServices {
//
//    public RestTemplate restTemplate;
//
//    public FakeProductServices(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public List<Products> getAllProducts(){
//        List<FakeStoreResponseDTO> response = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreResponseDTO.class);
//
//        // Step 2: Convert the response to a list of Products
//        List<Products> productsList = convertFakeStoreResponseDTOToProducts(response);
//
//        // Step 3: Return the list of Products
//        return productsList;
//    }
//    private List<Products> convertFakeStoreResponseDTOToProducts(List<Products> response) {
//        List<Products> productList = new ArrayList<>();
//
//        for (FakeStoreResponseDTO dto : response) {
//            Products product = new Products();
//            Category category = new Category();
//            category.setTitle(dto.getCategory());
//
//            product.setId(dto.getId());
//            product.setTitle(dto.getTitle());
//            product.setDescription(dto.getDescription());
//            product.setCategory(category);
//            product.setImageUrl(dto.getImage());
//
//            productList.add(product);
//        }
//
//        return productList;
//    }
//}
