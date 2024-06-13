package com.chess4math.customer.entities;

import lombok.*;
import org.springframework.validation.annotation.Validated;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Validated
public class Address {

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
