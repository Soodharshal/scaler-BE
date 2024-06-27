package dev.harshal.scaler.repository;

import dev.harshal.scaler.models.Category;
import dev.harshal.scaler.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
