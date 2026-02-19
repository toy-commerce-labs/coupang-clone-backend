package com.coupang.clone.customer.terms.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.clone.common.response.ApiResponse;
import com.coupang.clone.customer.terms.dto.TermsResponse;
import com.coupang.clone.customer.terms.service.CustomerTermsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/terms")
@RequiredArgsConstructor
public class CustomerTermsController {

    private final CustomerTermsService customerTermsService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<TermsResponse>>> getActiveTerms() {
        List<TermsResponse> terms = customerTermsService.getActiveTerms();
        return ResponseEntity.ok(ApiResponse.success(terms));
    }
}
