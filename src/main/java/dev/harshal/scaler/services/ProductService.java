package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.GenricProductDTO;

public interface ProductService {
    void getAllProducts();
    GenricProductDTO getProductsById(Long id);
    void deleteProductsById();

    void createProduct();

    void updateProductById();
}
