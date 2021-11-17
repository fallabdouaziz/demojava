package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(int id) {
        super(HttpStatus.NOT_FOUND,String.format("User with id : %d not found",id));
    }
}
