package dev.harshal.scaler.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Category category;
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private Price price;
}

