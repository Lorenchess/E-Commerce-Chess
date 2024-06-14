package com.chess4math.customer.advice;

import com.chess4math.customer.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String KEY = "Error: ";

    private Map<String, String> errorMap = new HashMap<>();

    private Map<String,String> errorMapHandler(Exception exception) {
        errorMap.put(KEY, exception.getMessage());
        return errorMap;
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return errorMapHandler(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return errorMapHandler(exception);
    }
}
