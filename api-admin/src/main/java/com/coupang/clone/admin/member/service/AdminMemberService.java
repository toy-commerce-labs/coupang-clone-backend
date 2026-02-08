package com.coupang.clone.admin.member.service;

import com.coupang.clone.admin.member.dto.AdminLoginRequest;
import com.coupang.clone.admin.member.dto.AdminLoginResponse;
import com.coupang.clone.common.exception.CustomException;
import com.coupang.clone.common.exception.ErrorCode;
import com.coupang.clone.domain.member.Member;
import com.coupang.clone.domain.member.MemberRepository;
import com.coupang.clone.domain.member.Role;
import com.coupang.clone.infra.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminMemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AdminLoginResponse login(AdminLoginRequest request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        if (member.getRole() != Role.ADMIN) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        String token = jwtTokenProvider.createToken(member.getEmail(), member.getRole().name());
        return new AdminLoginResponse(token);
    }
}
