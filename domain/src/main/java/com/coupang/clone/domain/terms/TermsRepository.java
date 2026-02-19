package com.coupang.clone.domain.terms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    // 현재 유효한 약관 전체 조회 (회원가입 화면에 표시할 목록)
    List<Terms> findByIsActiveTrueOrderByDisplayOrderAsc();

    // 특정 타입의 현재 유효한 약관 조회
    java.util.Optional<Terms> findByTypeAndIsActiveTrue(TermsType type);
}
