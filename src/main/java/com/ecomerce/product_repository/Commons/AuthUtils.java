package com.ecomerce.product_repository.Commons;

import com.ecomerce.product_repository.FakeStoreResponseDTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthUtils {
    private RestTemplate restTemplate;
    AuthUtils(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public UserDTO ValidateToken(String token){
        return restTemplate.getForObject(
                "http://localhost:8081/users/validate/"+token,
                UserDTO.class);
    }

}
