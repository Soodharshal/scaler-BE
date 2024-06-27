package dev.harshal.scaler.repository;

import dev.harshal.scaler.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PriceRepository extends JpaRepository<Price, UUID> {
}
