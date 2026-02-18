package com.coupang.clone.customer.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coupang.clone.common.response.ApiResponse;
import com.coupang.clone.customer.member.dto.LoginRequest;
import com.coupang.clone.customer.member.dto.LoginResponse;
import com.coupang.clone.customer.member.service.CustomerMemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class CustomerMemberController {

    private final CustomerMemberService customerMemberService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = customerMemberService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
