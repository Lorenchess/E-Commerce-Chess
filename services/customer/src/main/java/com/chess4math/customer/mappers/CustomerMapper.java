package com.chess4math.customer.mappers;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;
import com.chess4math.customer.entities.Customer;

public interface CustomerMapper {

    Customer dtoToDocument(CustomerRequest customerRequest);

    CustomerResponse documentToDTO(Customer customer);
}
