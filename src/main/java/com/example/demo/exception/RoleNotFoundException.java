package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RoleNotFoundException extends ResponseStatusException {
    public RoleNotFoundException(String name) {
        super(HttpStatus.NOT_FOUND,String.format("product with id : %d not found",name));
    }
}
