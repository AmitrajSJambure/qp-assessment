package com.grocery.booking.Grocery.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GroceryException.class)
    public ResponseEntity<ErrorDetail> handleGroceryExceptions( GroceryException ex) {
        ErrorDetail errorDetail = new ErrorDetail(ex.getErrorMessage(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
    }
}
