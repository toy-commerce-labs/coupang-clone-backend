package com.coupang.clone.customer.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class TermsAgreementRequest {

    @NotNull
    private Long termsId;

    private boolean agreed;
}
