package com.example.productservice.dtos;

import com.example.productservice.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDto {

    Long id;
    String title;
    Double price;
    String description;
    String image;
    String category;

    public static ProductResponseDto from(Product product){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId()!=null ? product.getId() : Long.valueOf(""));
        dto.setTitle(product.getTitle()!=null? product.getTitle() : "");
        dto.setPrice(product.getPrice()!=null? product.getPrice() : 0.0);
        dto.setDescription(product.getDescription()!=null? product.getDescription() : "");
        dto.setImage(product.getImageUrl()!=null? product.getImageUrl() : "");
        dto.setCategory(product.getCategory().getName()!=null? product.getCategory().getName() : "");

        return dto;
    }
}
