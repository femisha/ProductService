package com.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    String name;
    String description;

   // @OneToMany
  //  List<Product> featuredProducts;

    @OneToMany(mappedBy="category")
    List<Product> products;
}
