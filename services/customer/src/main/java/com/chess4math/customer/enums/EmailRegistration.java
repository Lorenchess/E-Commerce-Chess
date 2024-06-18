package com.chess4math.customer.enums;

public enum EmailRegistration {

    SUBJECT("Customer Registration Successful!"),
    CONTENT("""
    WELCOME! You have successfully registered to our services.
    
    Greetings!
    E-commerce-chess team.
    """);

    private final String value;

    EmailRegistration(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EmailRegistration fromString(String value) throws IllegalAccessException {
        if (value != null) {
            return switch (value.toUpperCase()) {
                case "SUBJECT" -> SUBJECT;
                case "CONTENT" -> CONTENT;
                default -> throw new IllegalArgumentException(String.format("The value: %s was not found", value));
            };
        }
        throw new IllegalArgumentException("value cannot be null");
    }


}
