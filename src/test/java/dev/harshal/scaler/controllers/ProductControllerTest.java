package dev.harshal.scaler.controllers;


import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.exceptions.NotFoundException;
import dev.harshal.scaler.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    ProductService productService;
    @Test
    void returnNullWhenProductDonotExist() throws NotFoundException {
        when(productService.getProductsById(124L)).thenReturn(null);
        GenricProductDTO productDTO =  productController.getProductsById(123L);
      assertNull(productDTO);
    }
    @Test
    void shouldReturnTitleWithProductId1() throws NotFoundException {
        GenricProductDTO genricProductDTO = new GenricProductDTO();
        genricProductDTO.setTitle("Harshal");
        when(productService.getProductsById(1L)).thenReturn(genricProductDTO);
        GenricProductDTO productDTO =  productController.getProductsById(1L);
        assertEquals(productDTO.getTitle(),"Harshal");
    }

    @Test
    void throwNotFoundExceptionIfProductDoNotExist() throws NotFoundException{
        when(productService.getProductsById(123L)).thenThrow(new NotFoundException("Product not found with id 123"));
        assertThrows(NotFoundException.class,()->productService.getProductsById(123L));
    }
}
