package com.chess4math.customer.dtos;

import com.chess4math.customer.entities.Address;
import lombok.Builder;


@Builder
public record CustomerResponse (
        String id,

        String firstName,

        String lastName,

        String email,

        Address address
){
}
