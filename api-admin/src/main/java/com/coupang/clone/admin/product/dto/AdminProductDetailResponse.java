package com.coupang.clone.admin.product.dto;

import com.coupang.clone.domain.product.Product;
import com.coupang.clone.domain.product.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminProductDetailResponse {
    private Long id;
    private String name;
    private Long price;
    private Integer stock;
    private String category;
    private String description;
    private String imageUrl;
    private ProductStatus status;

    public static AdminProductDetailResponse from(Product product) {
        return new AdminProductDetailResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getDescription(),
                product.getImageUrl(),
                product.getStatus()
        );
    }
}

