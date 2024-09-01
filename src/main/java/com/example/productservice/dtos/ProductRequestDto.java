package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {

    Long id;
    String title;
    Double price;
    String description;
    String image;
    String category;
}
