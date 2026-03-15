package com.coupang.clone.admin.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.clone.admin.product.dto.AdminProductCreateRequest;
import com.coupang.clone.admin.product.dto.AdminProductDetailResponse;
import com.coupang.clone.admin.product.dto.AdminProductListItemResponse;
import com.coupang.clone.admin.product.dto.AdminProductUpdateRequest;
import com.coupang.clone.admin.product.service.AdminProductService;
import com.coupang.clone.common.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin/products")
@RequiredArgsConstructor
public class AdminProductController {

    private final AdminProductService adminProductService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AdminProductListItemResponse>>> list() {
        return ResponseEntity.ok(ApiResponse.success(adminProductService.list()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminProductDetailResponse>> get(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success(adminProductService.get(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AdminProductDetailResponse>> create(
            @Valid @RequestBody AdminProductCreateRequest request
    ) {
        return ResponseEntity.status(201).body(ApiResponse.created(adminProductService.create(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AdminProductDetailResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody AdminProductUpdateRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(adminProductService.update(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        adminProductService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}

