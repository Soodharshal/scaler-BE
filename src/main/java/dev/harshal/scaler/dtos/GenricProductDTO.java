package dev.harshal.scaler.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GenricProductDTO extends BaseDTO{
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

}
