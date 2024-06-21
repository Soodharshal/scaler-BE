package dev.harshal.scaler.controllers;

import dev.harshal.scaler.dtos.GenricProductDTO;
import dev.harshal.scaler.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("FakeStoreProductService") ProductService productService){
        this.productService= productService;
    }
    @GetMapping
    public void getAllProducts(){

    }

    @GetMapping("{id}")
    public GenricProductDTO getProductsById(@PathVariable("id") Long id){
        return productService.getProductsById(id);
    }

    @DeleteMapping("{id}")
    public void deleteProductsById(){

    }

    @PostMapping
    public void createProduct(){

    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}

