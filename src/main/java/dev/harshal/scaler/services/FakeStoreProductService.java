package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;

import dev.harshal.scaler.exceptions.NotFoundException;
import dev.harshal.scaler.thirdpatyclients.productservice.fakestore.FakeStoreProductServiceClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("FakeStoreProductService")
@Primary
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductServiceClient fakeStoreProductServiceClient;

    FakeStoreProductService(FakeStoreProductServiceClient fakeStoreProductServiceClient){
        this.fakeStoreProductServiceClient =  fakeStoreProductServiceClient;
    }

    @Override
    public List<GenricProductDTO> getAllProducts() {
     return convertListToGenricProductDTO(fakeStoreProductServiceClient.getAllProducts());
    }

    @Override
    public GenricProductDTO getProductsById(Long id) throws NotFoundException {
        return convertToGenricProductDTO(fakeStoreProductServiceClient.getProductsById(id));
    }

    @Override
    public void deleteProductsById(Long id) {
       fakeStoreProductServiceClient.deleteProductsById(id);
    }

    @Override
    public void createProduct(GenricProductDTO product) {
       fakeStoreProductServiceClient.createProduct(product);
    }

    @Override
    public void updateProductById(GenricProductDTO product) {
       fakeStoreProductServiceClient.updateProductById(product);
    }

    private GenricProductDTO convertToGenricProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
        GenricProductDTO product = new GenricProductDTO();
        // Assuming both classes have similar fields. Set fields from fakeProduct to genricProduct
        product.setCategory(fakeStoreProductDTO.getCategory());
        product.setDescription(fakeStoreProductDTO.getDescription());
        product.setPrice(fakeStoreProductDTO.getPrice());
        product.setTitle(fakeStoreProductDTO.getTitle());
        product.setPrice(fakeStoreProductDTO.getPrice());
        // Repeat for all fields

        return product;
    }

    private List<GenricProductDTO> convertListToGenricProductDTO(List<FakeStoreProductDTO> fakeStoreProductDTOList) {
        return fakeStoreProductDTOList.stream()
                .map(this::convertToGenricProductDTO)
                .collect(Collectors.toList());
    }

}
