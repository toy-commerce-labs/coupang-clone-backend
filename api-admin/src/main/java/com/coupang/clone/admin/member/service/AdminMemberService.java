package com.coupang.clone.admin.member.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.coupang.clone.admin.member.dto.AdminLoginRequest;
import com.coupang.clone.admin.member.dto.AdminLoginResponse;
import com.coupang.clone.common.exception.CustomException;
import com.coupang.clone.common.exception.ErrorCode;
import com.coupang.clone.domain.member.Member;
import com.coupang.clone.domain.member.MemberRepository;
import com.coupang.clone.domain.member.Role;
import com.coupang.clone.infra.jwt.JwtTokenProvider;
import com.coupang.clone.infra.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMemberService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;

    public AdminLoginResponse login(AdminLoginRequest request) {
        // userId로 회원 조회 (없으면 인증 실패)
        Member member = memberRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD));

        // 조회된 회원의 email + 입력 비밀번호로 Spring Security 인증
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        // ADMIN 권한 체크
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
