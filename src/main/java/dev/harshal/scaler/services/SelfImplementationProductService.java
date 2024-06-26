package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("SelfImplementationProductService")
public class SelfImplementationProductService implements ProductService{
    @Override
    public List<GenricProductDTO> getAllProducts() {
        return new ArrayList<GenricProductDTO>();
    }

    @Override
    public GenricProductDTO getProductsById(Long id) {
        return null;
    }

    @Override
    public void deleteProductsById(Long id) {

    }

    @Override
    public void createProduct(GenricProductDTO genricProductDTO) {

    }

    @Override
    public void updateProductById(GenricProductDTO genricProductDTO) {

    }
}
