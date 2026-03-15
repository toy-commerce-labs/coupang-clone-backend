package com.coupang.clone.admin.product.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.clone.admin.product.dto.AdminProductCreateRequest;
import com.coupang.clone.admin.product.dto.AdminProductDetailResponse;
import com.coupang.clone.admin.product.dto.AdminProductListItemResponse;
import com.coupang.clone.admin.product.dto.AdminProductUpdateRequest;
import com.coupang.clone.common.exception.CustomException;
import com.coupang.clone.common.exception.ErrorCode;
import com.coupang.clone.domain.product.Product;
import com.coupang.clone.domain.product.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminProductService {

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<AdminProductListItemResponse> list() {
        return productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(AdminProductListItemResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public AdminProductDetailResponse get(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        return AdminProductDetailResponse.from(product);
    }

    @Transactional
    public AdminProductDetailResponse create(AdminProductCreateRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .description(request.getDescription())
                .imageUrl(request.getImageUrl())
                .status(request.getStatus())
                .build();

        Product saved = productRepository.save(product);
        return AdminProductDetailResponse.from(saved);
    }

    @Transactional
    public AdminProductDetailResponse update(Long id, AdminProductUpdateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        product.update(
                request.getName(),
                request.getPrice(),
                request.getStock(),
                request.getCategory(),
                request.getDescription(),
                request.getImageUrl(),
                request.getStatus()
        );

        return AdminProductDetailResponse.from(product);
    }

    @Transactional
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }
}

