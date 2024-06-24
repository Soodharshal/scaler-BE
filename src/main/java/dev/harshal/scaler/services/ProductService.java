package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    List<GenricProductDTO> getAllProducts();
    GenricProductDTO getProductsById(Long id) throws NotFoundException;
    void deleteProductsById(Long id);

    void createProduct(GenricProductDTO genricProductDTO);

    void updateProductById(GenricProductDTO genricProductDTO);
}
