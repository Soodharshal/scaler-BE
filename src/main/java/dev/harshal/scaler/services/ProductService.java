package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.GenricProductDTO;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {
    List<GenricProductDTO> getAllProducts();
    GenricProductDTO getProductsById(Long id);
    void deleteProductsById(Long id);

    void createProduct(GenricProductDTO genricProductDTO);

    void updateProductById(GenricProductDTO genricProductDTO);
}
