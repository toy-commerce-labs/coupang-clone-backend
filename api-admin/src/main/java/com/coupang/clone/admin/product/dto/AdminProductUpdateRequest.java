package com.coupang.clone.admin.product.dto;

import com.coupang.clone.domain.product.ProductStatus;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminProductUpdateRequest {

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "price is required")
    @Min(value = 0, message = "price must be >= 0")
    private Long price;

    @NotNull(message = "stock is required")
    @Min(value = 0, message = "stock must be >= 0")
    private Integer stock;

    private String category;
    private String description;
    private String imageUrl;

    @NotNull(message = "status is required")
    private ProductStatus status;
}

