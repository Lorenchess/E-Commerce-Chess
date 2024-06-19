package com.chess4math.customer.exceptions;

public class InvalidEmailAddressException extends RuntimeException {

    public InvalidEmailAddressException(String message) {
        super(message);
    }
}
