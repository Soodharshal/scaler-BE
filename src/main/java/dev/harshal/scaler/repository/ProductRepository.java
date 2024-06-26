package dev.harshal.scaler.repository;

import dev.harshal.scaler.models.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findByTitleEquals(String title);

    Product findByTitleEqualsAndPrice_Price(String title,double price);

    List<Product> findAllByPrice_Currency(String currency);

    long countAllByPrice_Currency(String currency);
}
