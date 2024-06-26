package com.chess4math.customer.globalExceptionHandler;

import com.chess4math.customer.exceptions.CustomerNotFoundException;
import com.chess4math.customer.exceptions.DuplicatedEmailException;
import com.chess4math.customer.exceptions.InvalidEmailAddressException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private String KEY = "Error message";



    private <T extends Exception>Map<String,String> errorMapHandler(T exception) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(KEY, exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CustomerNotFoundException.class)
    public Map<String,String> handleCustomerNotFoundException(CustomerNotFoundException exception) {
        return errorMapHandler(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(err -> errorMap.put(err.getField(), err.getDefaultMessage()));

        return errorMap;

    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DuplicatedEmailException.class)
    public Map<String,String> handleDuplicatedEmailException(DuplicatedEmailException exception) {
        return errorMapHandler(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEmailAddressException.class)
    public Map<String,String> handleInvalidEmailAddress(InvalidEmailAddressException exception) {
        return errorMapHandler(exception);
    }
}
