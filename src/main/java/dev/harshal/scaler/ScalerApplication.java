package dev.harshal.scaler;

import dev.harshal.scaler.models.Category;
import dev.harshal.scaler.models.Price;
import dev.harshal.scaler.models.Product;
import dev.harshal.scaler.repository.CategoryRepository;
import dev.harshal.scaler.repository.PriceRepository;
import dev.harshal.scaler.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ScalerApplication implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PriceRepository priceRepository;

    public ScalerApplication(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             PriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.priceRepository = priceRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ScalerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            // Initialize and save categories
            Category category = new Category();
            category.setName("Phones/Electronics");
            Category savedCategory = categoryRepository.save(category);

            // Initialize and save prices
            Price priceInr = new Price("INR",1.1);
            Price priceUsd = new Price("USD",2.1);
            Price savedPriceInr = priceRepository.save(priceInr);
            Price savedPriceUsd = priceRepository.save(priceUsd);
            Product product1 = new Product();
            product1.setTitle("xiaomi 12 pro");
            product1.setDescription("known for the best camera");
            product1.setPrice(savedPriceInr);
            product1.setCategory(savedCategory);
            Product product2 = new Product();
            productRepository.save(product1);
            product2.setTitle("xiaomi 12 pro 1");
            product2.setDescription("known for the best camera 1");
            product2.setPrice(savedPriceUsd);
            product2.setCategory(savedCategory);

            productRepository.save(product2);
            productRepository.deleteById(UUID.fromString("edd687a1-a643-44bd-80f2-5704e3683449"));

            List<Product> productRepositoryList =  productRepository.findAllByPrice_Currency("INR");


            long count =  productRepository.countAllByPrice_Currency("INR");
            System.out.println(productRepositoryList+"989789798798");

            System.out.println(count+"--989789798798count");
            System.out.println("fetching category by id 00ae1490-18f6-45bd-ba5f-32501c94da7c");
            Thread.sleep(2000);
            Optional<Category> categoryOptional =categoryRepository.findById(UUID.fromString("00ae1490-18f6-45bd-ba5f-32501c94da7c"));
            Category category1 = categoryOptional.get();
            System.out.println();
            // Initialize and save products

            // Retrieve and display category
//            Category retrievedCategory = categoryRepository.findById(UUID.fromString("d3892b29-a78e-4086-8d7c-35b0492f0972"))
//                    .orElseThrow(() -> new RuntimeException("Category not found"));
//            System.out.println("{}-------------------"+ retrievedCategory.getName());
//
//            // Display products in category
//            retrievedCategory.getProductList().forEach(
//                    product ->  System.out.println("{} 111111111"+ product.getTitle())
//            );
        } catch (Exception e) {
            System.out.println("Error initializing database"+ e);
            throw e;
        }
    }



}
