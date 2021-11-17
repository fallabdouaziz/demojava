package com.example.demo.service;

import com.example.demo.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product saveProduct(Product product);
    Product getProduct(int id);
    List<Product> getAllProduct();
    Product updateProduct(Product product);
    Boolean deleteProduct(int id );
}
