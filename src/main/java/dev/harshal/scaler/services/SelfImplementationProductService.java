package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.GenricProductDTO;
import org.springframework.stereotype.Service;

@Service("SelfImplementationProductService")
public class SelfImplementationProductService implements ProductService{
    @Override
    public void getAllProducts() {

    }

    @Override
    public GenricProductDTO getProductsById(Long id) {
        return null;
    }

    @Override
    public void deleteProductsById() {

    }

    @Override
    public void createProduct() {

    }

    @Override
    public void updateProductById() {

    }
}
