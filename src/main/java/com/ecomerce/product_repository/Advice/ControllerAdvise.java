package com.ecomerce.product_repository.Advice;

import com.ecomerce.product_repository.Exceptions.ProductNotFoundException;
import com.ecomerce.product_repository.FakeStoreResponseDTO.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvise {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorDTO> handleillegalArgument(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(400);
        errorDTO.setMessage("Bad Request");

        return ResponseEntity.badRequest().body(errorDTO);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleproductNotFound(){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setCode(404);
        errorDTO.setMessage("Product Not Found");

        return ResponseEntity.badRequest().body(errorDTO);
    }
}
