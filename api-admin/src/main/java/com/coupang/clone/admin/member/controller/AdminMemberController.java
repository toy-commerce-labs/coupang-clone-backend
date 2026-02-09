package com.coupang.clone.admin.member.controller;

import com.coupang.clone.admin.member.dto.AdminLoginRequest;
import com.coupang.clone.admin.member.dto.AdminLoginResponse;
import com.coupang.clone.admin.member.service.AdminMemberService;
import com.coupang.clone.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/members")
@RequiredArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AdminLoginResponse>> login(
            @Valid @RequestBody AdminLoginRequest request) {
        AdminLoginResponse response = adminMemberService.login(request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
