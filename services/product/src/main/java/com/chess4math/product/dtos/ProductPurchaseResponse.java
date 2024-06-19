package com.chess4math.product.dtos;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(
    Long productId,
    String name,
    String description,
    BigDecimal price,
    Long quantity
) {
}
