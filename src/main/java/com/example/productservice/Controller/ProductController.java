package com.example.productservice.Controller;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.dtos.ProductResponseDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Product;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Qualifier("ProductServiceDB")
    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier("ProductServiceDB") ProductService productService) {
        this.productService=productService;
    }


    @GetMapping("/product/{productId}")
    public ProductResponseDto getProductById(@PathVariable("productId") Long id) throws ProductNotFoundException {

        Product product = productService.getProduct(id);
        return ProductResponseDto.from(product);

    }

    @GetMapping("/product")
    public List<ProductResponseDto> getAllProducts() {

        List<Product> products=productService.getAllProducts();
        List<ProductResponseDto> resDtos=new ArrayList<>();
        for(Product product:products){
            resDtos.add(ProductResponseDto.from(product));
        }
        return resDtos;
    }

    @PostMapping("/product")
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto ) throws ProductNotFoundException {
        Product product=productService.createProduct(productRequestDto);

        return ProductResponseDto.from(product);
    }

    @PatchMapping("product/{id}")
    public ProductResponseDto partialUpdate(@PathVariable("id") Long id,@RequestBody  ProductRequestDto productRequestDto) throws ProductNotFoundException {

        Product product=productService.updateProduct(id,productRequestDto);
        return ProductResponseDto.from(product);
    }

}
