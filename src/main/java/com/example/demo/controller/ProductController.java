package com.example.demo.controller;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {
    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("")
    List<Product> getAll(){
        return productService.getAllProduct();
    }
    @PreAuthorize("hasAuthority('user:read')")
    @GetMapping("{id}")
    Product getProduct(@PathVariable int id){
        Product product = productService.getProduct(id);
        return product;
    }
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @DeleteMapping("{id}")
    Boolean delete(@PathVariable int id){
        return productService.deleteProduct(id);
    }
    @PreAuthorize("hasAuthority('user:create')")
    @PostMapping("")
    Product savProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }


}
