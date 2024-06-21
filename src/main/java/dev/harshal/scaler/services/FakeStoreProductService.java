package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    RestTemplateBuilder restTemplateBuilder;
    private String getProductRequestURL = "https://fakestoreapi.com/products/{id}";
    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public void getAllProducts() {

    }

    @Override
    public GenricProductDTO getProductsById(Long id) {
         RestTemplate restTemplate = restTemplateBuilder.build();
         ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(getProductRequestURL, FakeStoreProductDTO.class,id);
         FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        GenricProductDTO product = new GenricProductDTO();
         product.setCategory(fakeStoreProductDTO.getCategory());
         product.setDescription(fakeStoreProductDTO.getDescription());
         product.setPrice(fakeStoreProductDTO.getPrice());
         product.setTitle(fakeStoreProductDTO.getTitle());
         product.setPrice(fakeStoreProductDTO.getPrice());
         return product;

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
