package dev.harshal.scaler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.repository.CategoryRepository;
import dev.harshal.scaler.repository.PriceRepository;
import dev.harshal.scaler.repository.ProductRepository;
import dev.harshal.scaler.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    @Qualifier("FakeStoreProductService")
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    ProductControllerWebMvcTest(){

    }

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryRepository categoryRepository;

    @MockBean
    private PriceRepository priceRepository;

    @Test
    void getAllProductsReturnEmptyListWhenNoProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void returnListOfProductWhenProductExist() throws Exception {
        ArrayList<GenricProductDTO> products = new ArrayList<>();
        products.add(new GenricProductDTO());
        products.add(new GenricProductDTO());
        products.add(new GenricProductDTO());
        products.add(new GenricProductDTO());
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(products)));
     }

     @Test

    void createProductShouldCreateNewProduct() throws Exception {
        GenricProductDTO genricProductDTO =  new GenricProductDTO();
        genricProductDTO.setTitle("a");
        genricProductDTO.setPrice(1);
        genricProductDTO.setDescription("b");
        genricProductDTO.setCategory("phone");

         GenricProductDTO genricProductDTORes =  new GenricProductDTO();
         genricProductDTORes.setTitle("a");
         genricProductDTORes.setPrice(1);
         genricProductDTORes.setDescription("b");
         genricProductDTORes.setCategory("phone");
         genricProductDTORes.setId(1L);

//         when(productService.createProduct(genricProductDTO)).thenReturn(genricProductDTORes);

//            mockMvc.perform(post("/products")
//                    .content(objectMapper.writeValueAsString(genricProductDTO))
//            ).andExpect(content().string(objectMapper.writeValueAsString(genricProductDTORes))).andExpect(jsonPath("$.student.name",is("harshal")));
     }
}
