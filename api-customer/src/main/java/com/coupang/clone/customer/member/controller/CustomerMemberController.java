package com.coupang.clone.customer.member.controller;

import com.coupang.clone.common.response.ApiResponse;
import com.coupang.clone.customer.member.dto.LoginRequest;
import com.coupang.clone.customer.member.dto.LoginResponse;
import com.coupang.clone.customer.member.dto.SignupRequest;
import com.coupang.clone.customer.member.dto.SignupResponse;
import com.coupang.clone.customer.member.service.CustomerMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class CustomerMemberController {

    private final CustomerMemberService customerMemberService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(
            @Valid @RequestBody SignupRequest request) {
        SignupResponse response = customerMemberService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(
            @Valid @RequestBody LoginRequest request) {
        LoginResponse response = customerMemberService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
