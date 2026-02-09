package com.coupang.clone.customer.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupResponse {

    private Long id;
    private String email;
    private String name;
}
