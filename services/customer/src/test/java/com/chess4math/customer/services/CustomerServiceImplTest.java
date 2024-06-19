package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.entities.Address;
import com.chess4math.customer.entities.Customer;
import com.chess4math.customer.mappers.CustomerMapper;
import com.chess4math.customer.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl service;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper mapper;

    @Mock
    private EmailService emailService;

    private CustomerRequest customerRequest;

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
        customerRequest = CustomerRequest.builder()
                .id(null)
                .firstName("Ramon")
                .lastName("Lorente")
                .email("ramon@gmail.com")
                .address(address)
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
    void createCustomerSuccessfully() {
        //given
        when(mapper.dtoToDocument(any(CustomerRequest.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        //when
        String response = service.createCustomer(customerRequest);
        assertEquals(response, customer.getId());
        verify(emailService, times(1)).sendRegisterConfirmation(anyString(), anyString(), anyString());
    }

//    @Test
//    void failedToCreateCustomerDueInvalidEmailAddress() {
//      customer.setEmail("invalid-email");
//      doThrow(service.createCustomer(customerRequest))
//    }

    @Test
    void getCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void getAllCustomers() {
    }

    @Test
    void deleteCustomer() {
    }
}