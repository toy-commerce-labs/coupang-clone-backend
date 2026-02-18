package com.coupang.clone.admin.member.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.coupang.clone.admin.member.dto.AdminLoginRequest;
import com.coupang.clone.admin.member.dto.AdminLoginResponse;
import com.coupang.clone.common.exception.CustomException;
import com.coupang.clone.common.exception.ErrorCode;
import com.coupang.clone.domain.member.Role;
import com.coupang.clone.infra.jwt.JwtTokenProvider;
import com.coupang.clone.infra.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AdminLoginResponse login(AdminLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (userDetails.getMember().getRole() != Role.ADMIN) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        String token = jwtTokenProvider.createToken(
                userDetails.getUsername(),
                userDetails.getMember().getRole().name()
        );
        return new AdminLoginResponse(token);
    }
}
