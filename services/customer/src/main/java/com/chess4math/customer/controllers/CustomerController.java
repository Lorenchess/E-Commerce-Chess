package com.chess4math.customer.controllers;

import com.chess4math.customer.dtos.CustomerRequest;
import com.chess4math.customer.services.CustomerService;
import com.chess4math.customer.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/new")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        String resourceId = customerService.createCustomer(request);
        return ResponseEntity.created(Utils.withLocation(resourceId)).build();
    }
}
