package com.chess4math.customer.services;

public interface EmailService {
    void sendRegisterConfirmation(String recipient, String subject, String content);
}
