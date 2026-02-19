package com.coupang.clone.customer.member.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.clone.common.exception.CustomException;
import com.coupang.clone.common.exception.ErrorCode;
import com.coupang.clone.customer.member.dto.LoginRequest;
import com.coupang.clone.customer.member.dto.LoginResponse;
import com.coupang.clone.customer.member.dto.SignupRequest;
import com.coupang.clone.customer.member.dto.TermsAgreementRequest;
import com.coupang.clone.domain.member.Member;
import com.coupang.clone.domain.member.MemberRepository;
import com.coupang.clone.domain.member.Role;
import com.coupang.clone.domain.terms.Terms;
import com.coupang.clone.domain.terms.TermsRepository;
import com.coupang.clone.domain.terms.UserTermsAgreement;
import com.coupang.clone.domain.terms.UserTermsAgreementRepository;
import com.coupang.clone.infra.jwt.JwtTokenProvider;
import com.coupang.clone.infra.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerMemberService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final TermsRepository termsRepository;
    private final UserTermsAgreementRepository userTermsAgreementRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        if (userDetails.getMember().getRole() != Role.CUSTOMER) {
            throw new CustomException(ErrorCode.ACCESS_DENIED);
        }

        String token = jwtTokenProvider.createToken(
                userDetails.getUsername(),
                userDetails.getMember().getRole().name()
        );
        return new LoginResponse(token);
    }

    @Transactional
    public void signup(SignupRequest request) {
        // 1. 이메일 중복 체크
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        // 2. 필수 약관 동의 여부 검증
        List<Terms> activeTerms = termsRepository.findByIsActiveTrueOrderByDisplayOrderAsc();
        Map<Long, Boolean> agreementMap = request.getAgreements().stream()
                .collect(Collectors.toMap(TermsAgreementRequest::getTermsId, TermsAgreementRequest::isAgreed));

        activeTerms.stream()
                .filter(Terms::isRequired)
                .forEach(required -> {
                    if (!Boolean.TRUE.equals(agreementMap.get(required.getId()))) {
                        throw new CustomException(ErrorCode.REQUIRED_TERMS_NOT_AGREED);
                    }
                });

        // 3. 회원 저장
        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phone(request.getPhone())
                .role(Role.CUSTOMER)
                .build();
        memberRepository.save(member);

        // 4. 약관 동의 기록 저장
        for (TermsAgreementRequest agreementRequest : request.getAgreements()) {
            Terms terms = termsRepository.findById(agreementRequest.getTermsId())
                    .orElseThrow(() -> new CustomException(ErrorCode.TERMS_NOT_FOUND));

            UserTermsAgreement agreement = UserTermsAgreement.builder()
                    .member(member)
                    .terms(terms)
                    .isAgreed(agreementRequest.isAgreed())
                    .build();
            userTermsAgreementRepository.save(agreement);
        }
    }
}
