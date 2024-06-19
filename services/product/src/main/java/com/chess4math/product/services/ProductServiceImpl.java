package com.chess4math.product.services;

import com.chess4math.product.dtos.ProductPurchaseRequest;
import com.chess4math.product.dtos.ProductPurchaseResponse;
import com.chess4math.product.dtos.ProductRequest;
import com.chess4math.product.dtos.ProductResponse;
import com.chess4math.product.entities.Category;
import com.chess4math.product.entities.Product;
import com.chess4math.product.exceptions.CategoryNotFoundException;
import com.chess4math.product.exceptions.ProductNotFoundException;
import com.chess4math.product.exceptions.ProductPurchaseException;
import com.chess4math.product.mappers.ProductMapper;
import com.chess4math.product.repositories.CategoryRepository;
import com.chess4math.product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final ProductMapper mapper;

    @Override
    public Long createProduct(ProductRequest productRequest) {
        Product product  = productRepository.save(mapper.dtoToEntity(productRequest));
        return product.getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> requests) {
        List<Long> idList = requests.stream().map(ProductPurchaseRequest::productId).toList();

        List<Product> productListFound = productRepository.findAllById(idList);

        if (productListFound.size() != idList.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }

        List<Long> quantityList = requests.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).map(ProductPurchaseRequest::quantity).toList();

        List<ProductPurchaseResponse> purchasedProductsList = new ArrayList<>();

        for (int i = 0; i < productListFound.size(); i++) {
            Product currentProduct = productListFound.get(i);
            if (currentProduct.getAvailableQuantity() < quantityList.get(i)){
                throw new ProductPurchaseException(format("Insufficient stock quantity for product with id %s", productListFound.get(i)));
            }
            Long updatedAvailabilityQuantity = currentProduct.getAvailableQuantity() - quantityList.get(i);
            currentProduct.setAvailableQuantity(updatedAvailabilityQuantity);
            productRepository.save(currentProduct);
            purchasedProductsList.add(mapper.toProductPurchaseResponse(currentProduct, quantityList.get(i)));
        }
        return purchasedProductsList;

    }

    @Override
    public ProductResponse getProduct(Long productId) {
       return productRepository
               .findById(productId)
               .map(mapper::entityToDTO)
               .orElseThrow(() -> new ProductNotFoundException(format("Product with id: %s was not found", productId)));
    }

    @Override
    public List<ProductResponse> findAll(Pageable pageable) {
        List<Product> productList = productRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "price"))
        )).getContent();

        return productList.stream().map(mapper::entityToDTO).toList();
    }

    @Override
    public List<ProductResponse> findAllByCategory(Pageable pageable, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(format("Category with id %s was not found", categoryId)));
        List<Product> productsByCategory = productRepository.findAllByCategory(category);
        return productsByCategory.stream().map(mapper::entityToDTO).toList();
    }

}
