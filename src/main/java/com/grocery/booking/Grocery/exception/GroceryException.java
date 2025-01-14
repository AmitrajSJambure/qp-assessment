package com.grocery.booking.Grocery.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public class GroceryException extends RuntimeException{
    private String errorMessage;
    public GroceryException(String message) {
        super(message);
        this.errorMessage = message;
    }
}