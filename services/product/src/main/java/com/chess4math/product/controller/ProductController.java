package com.chess4math.product.controller;

import com.chess4math.product.dtos.ProductPurchaseRequest;
import com.chess4math.product.dtos.ProductPurchaseResponse;
import com.chess4math.product.dtos.ProductRequest;
import com.chess4math.product.dtos.ProductResponse;
import com.chess4math.product.services.ProductService;
import com.chess4math.product.utils.Utils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<Long> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Long productID = productService.createProduct(productRequest);

        return ResponseEntity.created(Utils.withLocation(productID)).body(productID);
    }

    @PostMapping("/purchase")
    ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts (@RequestBody @Valid List<ProductPurchaseRequest> request) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> findAllProduct(Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping("/all-by-category/{categoryId}")
    public ResponseEntity<List<ProductResponse>> findAllProductByCategory(Pageable pageable, @PathVariable Long categoryId) {
        return ResponseEntity.ok(productService.findAllByCategory(pageable, categoryId));
    }
}
