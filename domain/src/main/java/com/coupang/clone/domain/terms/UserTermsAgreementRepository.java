package com.coupang.clone.domain.terms;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTermsAgreementRepository extends JpaRepository<UserTermsAgreement, Long> {

    // 특정 회원의 동의 기록 전체 조회
    List<UserTermsAgreement> findByMemberId(Long memberId);

    // 특정 회원 + 특정 약관의 동의 기록 조회
    Optional<UserTermsAgreement> findByMemberIdAndTermsId(Long memberId, Long termsId);

    // 특정 회원의 현재 동의 중인 기록만 조회
    List<UserTermsAgreement> findByMemberIdAndIsAgreedTrue(Long memberId);
}
