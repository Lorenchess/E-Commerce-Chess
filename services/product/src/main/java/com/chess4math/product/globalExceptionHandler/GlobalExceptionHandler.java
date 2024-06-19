package com.chess4math.product.globalExceptionHandler;

import com.chess4math.product.exceptions.CategoryNotFoundException;
import com.chess4math.product.exceptions.ProductNotFoundException;
import com.chess4math.product.exceptions.ProductPurchaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final String KEY = "Error: ";

    private <T extends Exception>Map<String, String> handleErrorMap(T exception) {
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put(KEY, exception.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();

        exception.getBindingResult().getFieldErrors().forEach(err -> errorMap.put(err.getField(), err.getDefaultMessage()));

        return errorMap;

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public Map<String,String> handleProductNotFoundException(ProductNotFoundException exception) {
        return handleErrorMap(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(CategoryNotFoundException.class)
    public Map<String,String> handleCategoryNotFoundException(CategoryNotFoundException exception) {
        return handleErrorMap(exception);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductPurchaseException.class)
    public Map<String,String> handleProductPurchaseException(ProductPurchaseException exception) {
        return handleErrorMap(exception);
    }

}
