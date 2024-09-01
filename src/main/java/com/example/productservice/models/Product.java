package com.example.productservice.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product  extends BaseModel{

    String title;
    Double price;
    String description;
    String imageUrl;

    int count;

    @ManyToOne
    Category category;
}
