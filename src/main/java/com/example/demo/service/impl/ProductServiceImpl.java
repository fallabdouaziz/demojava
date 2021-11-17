package com.example.demo.service.impl;

import com.example.demo.dao.ProductRepository;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        log.info("Save product {} to database",product);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(int id) {
        log.info("fetching product {}",id);
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getAllProduct() {
        log.info("fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(Product product) {
        log.info("Update product {} to database",product);
        return productRepository.save(product);
    }

    @Override
    public Boolean deleteProduct(int id) {
        productRepository.delete(getProduct(id));
        return true;
    }

}
