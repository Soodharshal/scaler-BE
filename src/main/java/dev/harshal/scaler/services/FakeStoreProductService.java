package dev.harshal.scaler.services;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;

import dev.harshal.scaler.exceptions.NotFoundException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    RestTemplateBuilder restTemplateBuilder;
    private String productRequestByIDURL = "https://fakestoreapi.com/products/{id}";
    private String productRequestURL = "https://fakestoreapi.com/products";

    FakeStoreProductService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public List<GenricProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = productRequestURL; // Ensure this URL is correct

           ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(url, FakeStoreProductDTO[].class);
            FakeStoreProductDTO[] fakeStoreProductDTOArray = responseEntity.getBody();

            if (fakeStoreProductDTOArray != null) {
                // Convert FakeStoreProductDTO[] to List<GenricProductDTO>
                List<GenricProductDTO> productsList = Arrays.stream(fakeStoreProductDTOArray)
                        .map(this::convertToGenricProductDTO) // Use method reference
                        .collect(Collectors.toList());
                return productsList;
            } else {
                // Handle the case when the response body is null
                System.err.println("No products found at the provided URL.");
                return new ArrayList<>();
            }
    }

    @Override
    public GenricProductDTO getProductsById(Long id) throws NotFoundException {
         RestTemplate restTemplate = restTemplateBuilder.build();
         ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(productRequestByIDURL, FakeStoreProductDTO.class,id);
         FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
         if(fakeStoreProductDTO == null){
             throw new NotFoundException("Product not found with id "+id+" doesn't exist");
         }
         GenricProductDTO product = new GenricProductDTO();
         product.setCategory(fakeStoreProductDTO.getCategory());
         product.setDescription(fakeStoreProductDTO.getDescription());
         product.setPrice(fakeStoreProductDTO.getPrice());
         product.setTitle(fakeStoreProductDTO.getTitle());
         product.setPrice(fakeStoreProductDTO.getPrice());
         return product;
    }

    @Override
    public void deleteProductsById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productRequestByIDURL);
    }

    @Override
    public void createProduct(GenricProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<GenricProductDTO> responseEntity = restTemplate.postForEntity(productRequestURL, product, GenricProductDTO.class);
        return ;
    }

    @Override
    public void updateProductById(GenricProductDTO product) {
        String url = productRequestURL + "/" + product.getId();
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(url, product);
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

}
