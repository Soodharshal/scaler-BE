package dev.harshal.scaler.dtos;

import dev.harshal.scaler.models.Category;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDTO extends BaseDTO{
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;
}
