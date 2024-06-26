package dev.harshal.scaler.thirdpatyclients.productservice;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.exceptions.NotFoundException;

import java.util.List;

public interface ThirdPartyProductServiceClient {
    List<FakeStoreProductDTO> getAllProducts();
    FakeStoreProductDTO getProductsById(Long id) throws NotFoundException;
    void deleteProductsById(Long id);

    void createProduct(FakeStoreProductDTO genricProductDTO);

    void updateProductById(FakeStoreProductDTO genricProductDTO);
}
