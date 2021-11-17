package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class ProductNotFoundException extends ResponseStatusException {
    public ProductNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND,String.format("product with id : %d not found",id));
    }
}
