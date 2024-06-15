package com.chess4math.customer.mappers;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;
import com.chess4math.customer.entities.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public Customer dtoToDocument(CustomerRequest customerRequest) {
        if (customerRequest == null)
            return null;
        return Customer
                .builder()
                .id(customerRequest.id())
                .firstName(customerRequest.firstName())
                .lastName(customerRequest.lastName())
                .email(customerRequest.email())
                .address(customerRequest.address())
                .build();
    }

    @Override
    public CustomerResponse documentToDTO(Customer customer) {
        if (customer == null)
            return null;
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }
}
