package com.chess4math.product.mappers;

import com.chess4math.product.dtos.ProductPurchaseResponse;
import com.chess4math.product.dtos.ProductRequest;
import com.chess4math.product.dtos.ProductResponse;
import com.chess4math.product.entities.Product;

public interface ProductMapper {

    ProductResponse entityToDTO(Product product);

    Product dtoToEntity(ProductRequest productRequest);

    ProductPurchaseResponse toProductPurchaseResponse(Product product, Long quantity);
}
