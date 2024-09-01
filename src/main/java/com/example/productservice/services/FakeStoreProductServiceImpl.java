package com.example.productservice.services;

import com.example.productservice.dtos.FakeStoreResponseDto;
import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    @Autowired
    RestTemplate restTemplate;

    public Product getProduct(Long productId) throws ProductNotFoundException{
        FakeStoreResponseDto responseDto=restTemplate.getForObject("http://fakestoreapi.com/products/"+productId,
                                                                        FakeStoreResponseDto.class);
        if(responseDto==null){
            throw new ProductNotFoundException("Product not found");
        }
        return responseDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreResponseDto[] responseDtos=restTemplate.getForObject("http://fakestoreapi.com/products",
                                                                           FakeStoreResponseDto[].class);
        List<Product> products=new ArrayList<>();
        for(FakeStoreResponseDto responseDto: responseDtos){
            products.add(responseDto.toProduct());
        }
        return  products;
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) throws ProductNotFoundException {

        FakeStoreResponseDto responseDto=restTemplate.postForObject("http://fakestoreapi.com/products", productRequestDto, FakeStoreResponseDto.class);
        if(responseDto==null){
            throw new ProductNotFoundException("Error while adding product");
        }
        return responseDto.toProduct();
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) {
        HttpEntity<ProductRequestDto> entity=new HttpEntity<>(productRequestDto);
        ResponseEntity<FakeStoreResponseDto> resDto=restTemplate.exchange("http://fakestoreapi.com/products/"+id,
                                                                               HttpMethod.PUT, entity, FakeStoreResponseDto.class);
        FakeStoreResponseDto fakeStoreResponseDto=resDto.getBody();
        return fakeStoreResponseDto != null ? fakeStoreResponseDto.toProduct() : null;
    }
}
