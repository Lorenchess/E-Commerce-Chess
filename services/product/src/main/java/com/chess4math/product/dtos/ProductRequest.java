package com.chess4math.product.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(
        Long id,

        @NotBlank(message = "Product Name is required")
        String name,

        @NotBlank(message = "Product description is required")
        String description,

        @Positive(message = "available quantity should be positive")
        Long availableQuantity,

        @Positive(message = "Price should be positive")
        BigDecimal price,

        @Positive(message = "Price should be positive")
        Long categoryId
) {
}
