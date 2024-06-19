package com.chess4math.product.dtos;

import lombok.Builder;

@Builder
public record CategoryDTO(
        Long id,
        String name,
        String description
) {
}
