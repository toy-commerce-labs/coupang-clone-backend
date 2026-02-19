package com.coupang.clone.domain.terms;

public enum TermsType {
    AGE,        // [필수] 만 14세 이상
    SERVICE,    // [필수] 쿠팡 이용약관
    FINANCIAL,  // [필수] 전자금융거래 이용약관
    PRIVACY,    // [필수] 개인정보 제3자 제공
    MARKETING,  // [선택] 마케팅 목적의 개인정보 수집 및 이용
    AD_INFO,    // [선택] 광고성 정보 수신 (부모)
    AD_EMAIL,   // [선택] 이메일 수신
    AD_SMS,     // [선택] SMS, SNS 수신
    AD_PUSH     // [선택] 앱 푸시 수신
}
