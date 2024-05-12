package com.dmajd.nutritionapi.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductRestExceptionHandler
{
    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(Exception e)
    {
        ProductErrorResponse error = new ProductErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage("Invalid URL");
        error.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
