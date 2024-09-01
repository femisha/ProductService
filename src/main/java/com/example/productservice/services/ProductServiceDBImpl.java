package com.example.productservice.services;

import com.example.productservice.dtos.ProductRequestDto;
import com.example.productservice.exception.ProductNotFoundException;
import com.example.productservice.models.Category;
import com.example.productservice.models.Product;
import com.example.productservice.repositories.CategoryRepo;
import com.example.productservice.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("ProductServiceDB")
public class ProductServiceDBImpl implements ProductService{
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    public ProductServiceDBImpl(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProduct(Long productId) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepo.findById(productId);

        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList=productRepo.findAll();
        return productList;
    }

    @Override
    public Product createProduct(ProductRequestDto productRequestDto) throws ProductNotFoundException {
        Product product=new Product();
        product.setTitle(productRequestDto.getTitle());
        product.setImageUrl(productRequestDto.getImage());
        product.setPrice(productRequestDto.getPrice());
        product.setDescription(productRequestDto.getDescription());
        Category category=new Category();
        category.setName(productRequestDto.getCategory());
        product.setCategory(getCategoryFromDB(productRequestDto.getCategory()));
        return productRepo.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto productRequestDto) throws ProductNotFoundException {
        Optional<Product> productOptional=productRepo.findById(id);
        Product productToUpdate=productOptional.get();
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Update to update doesn't exists");

        }
        if(productRequestDto.getTitle()!=null){
            productToUpdate.setTitle(productRequestDto.getTitle());
        }
        if(productRequestDto.getDescription()!=null){
            productToUpdate.setDescription(productRequestDto.getDescription());
        }
        if(productRequestDto.getPrice()!=null){
            productToUpdate.setPrice(productRequestDto.getPrice());
        }
        if(productRequestDto.getCategory()!=null){
           productToUpdate.setCategory(getCategoryFromDB(productRequestDto.getCategory()));
        }

        return productRepo.save(productToUpdate);
    }

    public Category getCategoryFromDB(String categoryName) {

        Optional<Category> categoryOptional=categoryRepo.findByName(categoryName);
        if(categoryOptional.isEmpty()){
              Category category=new Category();
              category.setName(categoryName);
              return categoryRepo.save(category);
        }
        return categoryOptional.get();
    }
}
