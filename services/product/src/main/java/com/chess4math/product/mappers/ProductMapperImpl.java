package com.chess4math.product.mappers;

import com.chess4math.product.dtos.ProductPurchaseResponse;
import com.chess4math.product.dtos.ProductRequest;

import com.chess4math.product.dtos.ProductResponse;
import com.chess4math.product.entities.Product;

import com.chess4math.product.exceptions.CategoryNotFoundException;
import com.chess4math.product.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;


@Service
@RequiredArgsConstructor
public class ProductMapperImpl implements ProductMapper {

    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse entityToDTO(Product product) {
        if (product == null)
            return null;
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .availableQuantity(product.getAvailableQuantity())
                .categoryId(product.getCategory().getId())
                .categoryName(product.getName())
                .categoryDescription(product.getCategory().getDescription())
                .build();
    }

    @Override
    public Product dtoToEntity(ProductRequest productRequest) {
        if (productRequest == null)
            return null;
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(categoryRepository.
                        findById(productRequest.categoryId())
                        .orElseThrow(() -> new CategoryNotFoundException(format("Category with id: %s was not found", productRequest.id()))))
                .build();
    }

    @Override
    public ProductPurchaseResponse toProductPurchaseResponse(Product product, Long quantity) {
        return ProductPurchaseResponse.builder()
                .productId(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(quantity)
                .build();
    }

}
