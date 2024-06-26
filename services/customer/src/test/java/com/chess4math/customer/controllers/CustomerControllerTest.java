package com.chess4math.customer.controllers;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;
import com.chess4math.customer.entities.Address;
import com.chess4math.customer.entities.Customer;

import com.chess4math.customer.exceptions.CustomerNotFoundException;
import com.chess4math.customer.exceptions.DuplicatedEmailException;
import com.chess4math.customer.repositories.CustomerRepository;
import com.chess4math.customer.services.CustomerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

   private CustomerRequest invalidCustomerRequest;

   private CustomerRequest invalidCustomerEmailRequest;

   private CustomerResponse customerResponse;

    private CustomerResponse customerResponse1;

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
        customerResponse = CustomerResponse.builder()
                .id("12345")
                .firstName("Ramon")
                .lastName("Lorente")
                .email("ramon@gmail.com")
                .address(address)
                .build();
        customerResponse1 = CustomerResponse.builder()
                .id("67891")
                .firstName("Joseph")
                .lastName("Perez")
                .email("joseph@gmail.com")
                .address(address)
                .build();
        invalidCustomerRequest = CustomerRequest.builder()
                .id(null)
                .firstName("")
                .lastName("")
                .email("")
                .address(address)
                .build();
        invalidCustomerEmailRequest = CustomerRequest.builder()
                .id(null)
                .firstName("Ramon")
                .lastName("Lorente")
                .email("ramon.gmail.com")
                .address(address)
                .build();
    }

    @Test
    void createCustomerSuccessfully() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/v1/customers/new")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerJson))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void shouldThrowAndExceptionWhenTryingToCreateCustomerWithPersistedEmailAddress() throws Exception {
        String customerJson = objectMapper.writeValueAsString(customerRequest);

        mockMvc.perform(post("/api/v1/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andDo(print())
                .andExpect(status().isCreated());

        doThrow(new DuplicatedEmailException(String.format("email: %s already exists. Please register with another email.", customerRequest.email())))
                .when(customerService).createCustomer(any(CustomerRequest.class));

        mockMvc.perform(post("/api/v1/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andDo(print())
                .andExpect(status().isConflict())
                .andExpect(content().string("{\"Error message\":\"email: ramon@gmail.com already exists. Please register with another email.\"}"));
    }

    @Test
    void shouldThrowAnExceptionWhenPassingInvalidParametersInValidatedPayload() throws Exception {
        String customerJson = objectMapper.writeValueAsString(invalidCustomerRequest);

        mockMvc.perform(post("/api/v1/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.firstName").value("Customer firstName is required!"))
                .andExpect(jsonPath("$.lastName").value("Customer lastName is required!"))
                .andExpect(jsonPath("$.email").value("Customer email is required!"));
    }

    @Test
    void shouldThrowAnExceptionWhenPassingInvalidEmailAddress() throws Exception {
        String customerJson = objectMapper.writeValueAsString(invalidCustomerEmailRequest);

        mockMvc.perform(post("/api/v1/customers/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(customerJson))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.email").value("Customer email is not a valid email address"));

    }

    @Test
    void shouldGetCustomerById() throws Exception {

        given(customerService.getCustomer(any(String.class))).willReturn(customerResponse);

        mockMvc.perform(get("/api/v1/customers/{id}", "12345").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerResponse.firstName()))
                .andExpect(jsonPath("$.lastName").value(customerResponse.lastName()))
                .andExpect(jsonPath("$.email").value(customerResponse.email()))
                .andExpect(jsonPath("$.address").value(customerResponse.address()))
        ;
    }

    @Test
    void shouldThrowCustomerNotFoundException() throws Exception {
        given(customerService.getCustomer(any(String.class))).willThrow(new CustomerNotFoundException(String.format("Customer with id: %s was not found!", "12345")));

        mockMvc.perform(get("/api/v1/customers/{id}", "12345").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"Error message\":\"Customer with id: 12345 was not found!\"}"));
    }

    @Test
    void shouldGetListOfCustomers() throws Exception {
        List<CustomerResponse> customers = List.of(customerResponse, customerResponse1);
        given(customerService.getAllCustomers(any(Pageable.class))).willReturn(customers);
        String customersJson = objectMapper.writeValueAsString(customers);

        mockMvc.perform(get("/api/v1/customers/all").contentType(MediaType.APPLICATION_JSON).content(customersJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].id").value(customerResponse.id()))
                .andExpect(jsonPath("$.[1].id").value(customerResponse1.id()));

    }
}