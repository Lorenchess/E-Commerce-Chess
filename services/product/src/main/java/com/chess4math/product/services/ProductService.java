package com.chess4math.product.services;

import com.chess4math.product.dtos.ProductPurchaseRequest;
import com.chess4math.product.dtos.ProductRequest;
import com.chess4math.product.dtos.ProductPurchaseResponse;
import com.chess4math.product.dtos.ProductResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    Long createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse getProduct(Long productId);

    List<ProductResponse> findAll(Pageable pageable);

    List<ProductResponse> findAllByCategory(Pageable pageable, Long categoryId);
}
