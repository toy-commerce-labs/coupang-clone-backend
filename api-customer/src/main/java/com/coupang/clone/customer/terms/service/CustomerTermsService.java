package com.coupang.clone.customer.terms.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coupang.clone.customer.terms.dto.TermsResponse;
import com.coupang.clone.domain.terms.Terms;
import com.coupang.clone.domain.terms.TermsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerTermsService {

    private final TermsRepository termsRepository;

    // 회원가입 화면에 표시할 약관 목록 조회 (계층 구조로 반환)
    @Transactional(readOnly = true)
    public List<TermsResponse> getActiveTerms() {
        List<Terms> all = termsRepository.findByIsActiveTrueOrderByDisplayOrderAsc();

        // 최상위 항목(parent 없는 것)만 추려서, 각각의 자식 항목을 붙여 반환
        return all.stream()
                .filter(terms -> terms.getParent() == null)
                .map(parent -> new TermsResponse(parent, findChildren(parent, all)))
                .collect(Collectors.toList());
    }

    private List<TermsResponse> findChildren(Terms parent, List<Terms> all) {
        return all.stream()
                .filter(terms -> terms.getParent() != null
                        && terms.getParent().getId().equals(parent.getId()))
                .map(child -> new TermsResponse(child, findChildren(child, all)))
                .collect(Collectors.toList());
    }
}
