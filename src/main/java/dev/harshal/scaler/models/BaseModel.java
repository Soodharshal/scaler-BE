package dev.harshal.scaler.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@MappedSuperclass
public class BaseModel {
   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(
           name = "UUID",
           strategy = "org.hibernate.id.UUIDGenerator"
   )
   @Column(name = "id", updatable = false, nullable = false, columnDefinition = "BINARY(16)")
   private UUID uuid;
}
