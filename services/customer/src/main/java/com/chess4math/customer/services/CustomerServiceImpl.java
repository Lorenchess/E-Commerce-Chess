package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;
import com.chess4math.customer.entities.Customer;
import com.chess4math.customer.exceptions.CustomerNotFoundException;
import com.chess4math.customer.mappers.CustomerMapper;
import com.chess4math.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        Customer persistedCustomer = customerRepository.save(mapper.dtoToDocument(request));
        return persistedCustomer.getId();
    }

    @Override
    public CustomerResponse getCustomer(String id) {
        Customer customer = getCustomerOrThrowException(id);

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();

    }

    @Override
    public CustomerResponse updateCustomer(String id, CustomerRequest customerRequest) {
        Customer customer = getCustomerOrThrowException(id);
        Customer updatedCustomer = updateCustomerAndPersist(customer, customerRequest);
        return CustomerResponse.builder()
                .id(updatedCustomer.getId())
                .firstName(updatedCustomer.getFirstName())
                .lastName(updatedCustomer.getLastName())
                .email(updatedCustomer.getEmail())
                .address(updatedCustomer.getAddress())
                .build();

    }

    private Customer getCustomerOrThrowException(String id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Customer with id: %s was not found!", id)));
    }

    private Customer updateCustomerAndPersist(Customer customer, CustomerRequest customerRequest) {
        customer.setFirstName(customerRequest.firstName());
        customer.setLastName(customerRequest.lastName());
        customer.setEmail(customerRequest.email());
        customer.setAddress(customerRequest.address());
        return customerRepository.save(customer);
    }
}
