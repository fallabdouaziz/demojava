package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailExistException extends ResponseStatusException {
    public EmailExistException(String username) {
        super(HttpStatus.CONFLICT,
                String.format("User with username : %s already exist !", username));
    }
}
