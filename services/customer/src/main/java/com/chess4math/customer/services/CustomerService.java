package com.chess4math.customer.services;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.dtos.CustomerResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    String createCustomer(CustomerRequest request);

    CustomerResponse getCustomer(String id);

    CustomerResponse updateCustomer(String id, CustomerRequest customerRequest);

    List<CustomerResponse> getAllCustomers(Pageable pageable);

    void deleteCustomer(String customerId);
}
