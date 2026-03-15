package com.coupang.clone.admin.product.dto;

import com.coupang.clone.domain.product.Product;
import com.coupang.clone.domain.product.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdminProductListItemResponse {
    private Long id;
    private String name;
    private Long price;
    private Integer stock;
    private String category;
    private ProductStatus status;

    public static AdminProductListItemResponse from(Product product) {
        return new AdminProductListItemResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                product.getCategory(),
                product.getStatus()
        );
    }
}

