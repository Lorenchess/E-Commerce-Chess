package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    CustomerResponse getCustomer(String id);

    CustomerResponse updateCustomer(String id, CustomerRequest customerRequest);
}
