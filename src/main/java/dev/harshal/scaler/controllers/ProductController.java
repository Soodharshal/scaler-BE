package dev.harshal.scaler.controllers;

import dev.harshal.scaler.dtos.FakeStoreProductDTO;
import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.exceptions.NotFoundException;
import dev.harshal.scaler.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService= productService;
    }
    @GetMapping
    public  ResponseEntity<List<GenricProductDTO>> getAllProducts(){
        ResponseEntity<List<GenricProductDTO>> responseEntity = new ResponseEntity<>(
                productService.getAllProducts(),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("{id}")
    public GenricProductDTO getProductsById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.getProductsById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductsById(@PathVariable("id") Long id){
         productService.deleteProductsById(id);
    }

    @PostMapping
    public void createProduct(@RequestBody GenricProductDTO genricProductDTO){
        productService.createProduct(genricProductDTO);
    }

    @PutMapping("{id}")
    public void updateProductById(@RequestBody GenricProductDTO genricProductDTO){
        productService.updateProductById(genricProductDTO);
    }
}

