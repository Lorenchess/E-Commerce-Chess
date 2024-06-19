package com.chess4math.product.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

@Builder
public record ProductPurchaseRequest(
     @NotNull(message = "Product is mandatory")
     Long productId,

     @Positive(message = "quantity must be positive")
     @NotNull(message = "quantity is mandatory")
     Long quantity
) {
}
