package com.coupang.clone.customer.terms.dto;

import java.util.List;

import com.coupang.clone.domain.terms.Terms;
import com.coupang.clone.domain.terms.TermsType;

import lombok.Getter;

@Getter
public class TermsResponse {

    private final Long id;
    private final TermsType type;
    private final String title;
    private final String content;
    private final String version;
    private final boolean required;
    private final int displayOrder;
    private final Long parentId;  // null이면 최상위, 값 있으면 자식 항목
    private final List<TermsResponse> children;

    public TermsResponse(Terms terms, List<TermsResponse> children) {
        this.id = terms.getId();
        this.type = terms.getType();
        this.title = terms.getTitle();
        this.content = terms.getContent();
        this.version = terms.getVersion();
        this.required = terms.isRequired();
        this.displayOrder = terms.getDisplayOrder();
        this.parentId = terms.getParent() != null ? terms.getParent().getId() : null;
        this.children = children;
    }
}
