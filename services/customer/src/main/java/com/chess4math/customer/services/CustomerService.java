package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;

public interface CustomerService {
    String createCustomer(CustomerRequest request);
}
