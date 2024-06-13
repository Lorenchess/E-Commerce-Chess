package com.chess4math.customer.dtos;

import com.chess4math.customer.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CustomerRequest(
        String id,

        @NotBlank(message = "Customer firstName is required!")
        String firstName,

        @NotBlank(message = "Customer lastName is required!")
        String lastName,

        @NotBlank(message = "Customer email is required!")
        @Email(message = "Customer email is not a valid email address")
        String email,

        Address address
) {
}
