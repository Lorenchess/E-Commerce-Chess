package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.entities.Customer;
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
}
