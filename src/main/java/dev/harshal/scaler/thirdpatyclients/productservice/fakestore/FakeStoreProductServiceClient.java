package dev.harshal.scaler.thirdpatyclients.productservice.fakestore;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FakeStoreProductServiceClient {
    RestTemplateBuilder restTemplateBuilder;

    @Value("${fakestore.api.url}")
    private String fakeStoreApiUrl;

    @Value("${fakestore.api.path.product}")
    private String fakeStoreApiPath;
    private String productRequestByIDURL ;
    private String productRequestURL;


    FakeStoreProductServiceClient(RestTemplateBuilder restTemplateBuilder,  @Value("${fakestore.api.url}") String fakeStoreApiUrl,
                                  @Value("${fakestore.api.path.product}") String fakeStoreApiPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productRequestByIDURL = fakeStoreApiUrl+ fakeStoreApiPath +"/{id}";
        productRequestURL = fakeStoreApiUrl + fakeStoreApiPath;

    }


    public List<FakeStoreProductDTO> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        String url = productRequestURL; // Ensure this URL is correct

        ResponseEntity<FakeStoreProductDTO[]> responseEntity = restTemplate.getForEntity(url, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOArray = responseEntity.getBody();

        if (fakeStoreProductDTOArray != null) {
            // Convert FakeStoreProductDTO[] to List<GenricProductDTO>
            List<FakeStoreProductDTO> productsList = Arrays.stream(fakeStoreProductDTOArray)
                    .collect(Collectors.toList());
            return productsList;
        } else {
            // Handle the case when the response body is null
            System.err.println("No products found at the provided URL.");
            return new ArrayList<>();
        }
    }


    public FakeStoreProductDTO getProductsById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.getForEntity(productRequestByIDURL, FakeStoreProductDTO.class,id);
        FakeStoreProductDTO fakeStoreProductDTO = responseEntity.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("Product not found with id "+id+" doesn't exist");
        }
//        GenricProductDTO product = new GenricProductDTO();
//        product.setCategory(fakeStoreProductDTO.getCategory());
//        product.setDescription(fakeStoreProductDTO.getDescription());
//        product.setPrice(fakeStoreProductDTO.getPrice());
//        product.setTitle(fakeStoreProductDTO.getTitle());
//        product.setPrice(fakeStoreProductDTO.getPrice());
        return fakeStoreProductDTO;
    }


    public void deleteProductsById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.delete(productRequestByIDURL);
    }


    public void createProduct(GenricProductDTO product) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> responseEntity = restTemplate.postForEntity(productRequestURL, product, FakeStoreProductDTO.class);
        return ;
    }


    public void updateProductById(GenricProductDTO product) {
        String url = productRequestURL + "/" + product.getId();
        RestTemplate restTemplate = restTemplateBuilder.build();
        restTemplate.put(url, product);
    }

//    private GenricProductDTO convertToGenricProductDTO(FakeStoreProductDTO fakeStoreProductDTO) {
//        GenricProductDTO product = new GenricProductDTO();
//        // Assuming both classes have similar fields. Set fields from fakeProduct to genricProduct
//        product.setCategory(fakeStoreProductDTO.getCategory());
//        product.setDescription(fakeStoreProductDTO.getDescription());
//        product.setPrice(fakeStoreProductDTO.getPrice());
//        product.setTitle(fakeStoreProductDTO.getTitle());
//        product.setPrice(fakeStoreProductDTO.getPrice());
//        // Repeat for all fields
//
//        return product;
//    }

}
