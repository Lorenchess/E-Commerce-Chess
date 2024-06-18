package com.chess4math.customer.services;

import com.chess4math.customer.config.EmailConfigProperties;
import com.chess4math.customer.entities.Address;
import com.chess4math.customer.entities.Customer;
import com.chess4math.customer.enums.EmailRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {

    @InjectMocks
    private EmailServiceImpl emailService;

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailConfigProperties emailConfigProperties;

    @Mock
    private SimpleMailMessage templateMessage;

    private Customer customer;

    private Address address;

    @BeforeEach
    void setUp() {
        address = Address.builder()
                .street("Success St")
                .city("Progress City")
                .country("Heaven")
                .zipCode("777")
                .build();
        customer = Customer.builder()
                .id("12345")
                .firstName("Ramon")
                .lastName("Lorente")
                .email("ramon@gmail.com")
                .address(address)
                .build();
    }

    @Test
    void sendRegisterConfirmation() {

        //when
        emailService.sendRegisterConfirmation(customer.getEmail(), EmailRegistration.SUBJECT.getValue(), EmailRegistration.CONTENT.getValue());

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}