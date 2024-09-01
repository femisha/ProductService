package com.example.productservice.services;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Product;

import java.util.List;

public interface ProductService {

    public Product getProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product createProduct(ProductRequestDto productRequestDto) throws ProductNotFoundException;

    Product updateProduct(Long  id, ProductRequestDto productRequestDto) throws ProductNotFoundException;
}
