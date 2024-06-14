package com.chess4math.customer.controllers;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.entities.Address;
import com.chess4math.customer.entities.Customer;

import com.chess4math.customer.repositories.CustomerRepository;
import com.chess4math.customer.services.CustomerService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CustomerService customerService;

    @MockBean
    private CustomerRepository repository;


   private ObjectMapper objectMapper = new ObjectMapper();

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
                .id("1")
                .firstName("Ramon")
                .lastName("Lorente")
                .email("ramon@gmail.com")
                .address(address)
                .build();
    }

    @Test
    void createCustomerSuccessfully() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customerRequest);

        //given
        given(customerService.createCustomer(any(CustomerRequest.class))).willReturn(customer.getId());
        given(repository.save(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/api/v1/customers/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}