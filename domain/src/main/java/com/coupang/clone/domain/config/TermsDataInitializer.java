package com.coupang.clone.domain.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coupang.clone.domain.terms.Terms;
import com.coupang.clone.domain.terms.TermsRepository;
import com.coupang.clone.domain.terms.TermsType;

@Configuration
public class TermsDataInitializer {

    @Bean
    CommandLineRunner initTermsData(TermsRepository termsRepository) {
        return args -> {
            if (termsRepository.count() > 0) {
                return;
            }

            termsRepository.save(Terms.builder()
                    .type(TermsType.AGE)
                    .title("[필수] 만 14세 이상입니다")
                    .content("""
                            쿠팡은 만 14세 미만 아동의 개인정보를 보호하기 위해 만 14세 미만인 경우 회원가입을 제한하고 있습니다.
                            만 14세 미만 아동이 회원가입을 시도하는 경우, 법정대리인의 동의 없이는 서비스 이용이 불가합니다.
                            본인은 만 14세 이상임을 확인하며, 이에 동의합니다.
                            """)
                    .version("1.0")
                    .isRequired(true)
                    .isActive(true)
                    .displayOrder(1)
                    .parent(null)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.SERVICE)
                    .title("[필수] 쿠팡 이용약관 동의")
                    .content("""
                            제1조 (목적)
                            이 약관은 쿠팡 주식회사(이하 "회사")가 운영하는 쿠팡 서비스(이하 "서비스")의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.

                            제2조 (정의)
                            1. "서비스"란 회사가 제공하는 전자상거래 관련 제반 서비스를 의미합니다.
                            2. "회원"이란 회사와 서비스 이용계약을 체결하고 회원 아이디(ID)를 부여받은 개인 또는 법인을 말합니다.
                            3. "아이디(ID)"란 회원의 식별과 서비스 이용을 위하여 회원이 설정하고 회사가 승인한 문자와 숫자의 조합을 의미합니다.

                            제3조 (약관의 게시와 개정)
                            1. 회사는 이 약관의 내용을 회원이 쉽게 알 수 있도록 서비스 초기 화면에 게시합니다.
                            2. 회사는 관련 법령을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
                            3. 회사가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행 약관과 함께 서비스 초기 화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다.

                            제4조 (서비스의 제공 및 변경)
                            1. 회사는 다음과 같은 서비스를 제공합니다.
                               - 재화 또는 용역에 대한 정보 제공 및 구매계약의 체결
                               - 구매계약이 체결된 재화 또는 용역의 배송
                               - 기타 회사가 정하는 업무
                            2. 회사는 서비스의 내용, 이용방법 등을 변경할 수 있으며, 이 경우 변경된 서비스의 내용 및 제공일자를 명시하여 현재의 서비스 내용을 게시한 곳에 즉시 공지합니다.

                            제5조 (서비스 이용 제한)
                            회사는 다음 각 호에 해당하는 경우 서비스 이용을 제한할 수 있습니다.
                            1. 가입 신청자가 이 약관에 의하여 이전에 회원자격을 상실한 경우
                            2. 실명이 아니거나 타인의 명의를 이용한 경우
                            3. 허위의 정보를 기재하거나, 회사가 제시하는 내용을 기재하지 않은 경우
                            """)
                    .version("1.0")
                    .isRequired(true)
                    .isActive(true)
                    .displayOrder(2)
                    .parent(null)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.FINANCIAL)
                    .title("[필수] 전자금융거래 이용약관 동의")
                    .content("""
                            제1조 (목적)
                            이 약관은 쿠팡 주식회사(이하 "회사")가 제공하는 전자금융거래 서비스를 회원(이하 "이용자")이 이용함에 있어 회사와 이용자 사이의 전자금융거래에 관한 기본적인 사항을 정함을 목적으로 합니다.

                            제2조 (정의)
                            1. "전자금융거래"라 함은 회사가 전자적 장치를 통하여 금융상품 및 서비스를 제공하고, 이용자가 회사의 종사자와 직접 대면하거나 의사소통을 하지 아니하고 자동화된 방식으로 이를 이용하는 거래를 말합니다.
                            2. "전자지급수단"이라 함은 전자자금이체, 직불전자지급수단, 선불전자지급수단, 전자화폐, 신용카드, 전자채권, 그 밖에 전자적 방법에 따른 지급수단을 말합니다.

                            제3조 (전자금융거래 서비스의 종류)
                            회사가 제공하는 전자금융거래 서비스의 종류는 다음과 같습니다.
                            1. 신용카드 결제 서비스
                            2. 계좌이체 서비스
                            3. 간편결제 서비스 (쿠페이 등)
                            4. 상품권·쿠폰 등 선불수단 결제 서비스

                            제4조 (거래내용의 확인)
                            1. 회사는 이용자가 전자금융거래의 내용을 확인할 수 있도록 거래내용을 서비스 화면을 통해 제공합니다.
                            2. 이용자는 거래내용에 오류가 있음을 안 때에는 즉시 회사에 정정을 요청할 수 있습니다.

                            제5조 (오류의 정정)
                            이용자는 전자금융거래 이용 시 오류가 발생하였음을 안 때에는 즉시 회사에 이를 알려야 하며, 회사는 이를 조사하여 처리결과를 이용자에게 알려드립니다.
                            """)
                    .version("1.0")
                    .isRequired(true)
                    .isActive(true)
                    .displayOrder(3)
                    .parent(null)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.PRIVACY)
                    .title("[필수] 개인정보 제3자 제공 동의")
                    .content("""
                            쿠팡 주식회사는 원활한 서비스 제공을 위해 아래와 같이 개인정보를 제3자에게 제공하고 있습니다.

                            ■ 개인정보를 제공받는 자
                            - 배송 파트너사 (CJ대한통운, 한진택배, 롯데택배 등)
                            - 결제 대행사 (KG이니시스, NHN한국사이버결제 등)
                            - 고객 상담 서비스 제공업체

                            ■ 제공받는 자의 개인정보 이용 목적
                            - 배송 파트너사: 상품 배송 및 배송 현황 안내
                            - 결제 대행사: 결제 처리 및 결제 내역 관리
                            - 고객 상담 업체: 고객 문의 접수 및 처리

                            ■ 제공하는 개인정보 항목
                            - 배송 파트너사: 수령인 성명, 배송지 주소, 휴대폰번호, 주문번호
                            - 결제 대행사: 성명, 이메일, 결제수단 정보
                            - 고객 상담 업체: 성명, 이메일, 휴대폰번호, 주문 관련 정보

                            ■ 제공받는 자의 개인정보 보유 및 이용 기간
                            - 배송 완료 후 6개월 또는 관계 법령에 따른 보존 기간
                            - 결제 완료 후 5년 (전자상거래법)
                            - 고객 문의 처리 완료 후 3년

                            ※ 위 동의를 거부할 권리가 있으나, 거부 시 배송·결제 등 필수 서비스 이용이 제한될 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(true)
                    .isActive(true)
                    .displayOrder(4)
                    .parent(null)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.MARKETING)
                    .title("[선택] 마케팅 목적의 개인정보 수집 및 이용 동의")
                    .content("""
                            쿠팡 주식회사는 더 나은 쇼핑 경험을 제공하기 위해 아래와 같이 마케팅 목적으로 개인정보를 수집·이용합니다.

                            ■ 수집·이용 목적
                            - 맞춤형 상품 추천 및 광고 제공
                            - 이벤트·프로모션 안내
                            - 신규 서비스 소개 및 이용 권유
                            - 쇼핑 통계·분석을 통한 개인화 서비스 제공

                            ■ 수집·이용 항목
                            - 성명, 이메일, 휴대폰번호
                            - 구매 이력, 검색 이력, 관심 상품 목록
                            - 서비스 이용 기록 및 접속 로그

                            ■ 보유 및 이용 기간
                            - 동의일로부터 회원 탈퇴 시 또는 동의 철회 시까지
                            - 단, 관계 법령에 따라 별도 보존이 필요한 경우 해당 기간까지

                            ※ 위 동의를 거부하더라도 쿠팡의 기본 서비스 이용에는 제한이 없습니다.
                            ※ 동의 후에도 마이페이지 > 알림 설정에서 언제든지 철회할 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(false)
                    .isActive(true)
                    .displayOrder(5)
                    .parent(null)
                    .build());

            Terms adInfo = termsRepository.save(Terms.builder()
                    .type(TermsType.AD_INFO)
                    .title("[선택] 광고성 정보 수신 동의")
                    .content("""
                            쿠팡 주식회사로부터 할인 쿠폰, 이벤트, 신상품, 혜택 등 다양한 광고성 정보를 수신하는 것에 동의합니다.

                            ■ 발송 내용
                            - 특가·할인 정보, 쿠폰 및 포인트 혜택
                            - 신상품 및 추천 상품 안내
                            - 쿠팡 이벤트 및 프로모션 정보
                            - 로켓와우 회원 전용 혜택 안내

                            ■ 발송 채널
                            이메일, SMS/MMS, 앱 푸시 알림 (개별 채널별 동의 가능)

                            ※ 본 동의는 선택사항으로, 미동의 시에도 기본 서비스 이용에는 제한이 없습니다.
                            ※ 동의 후 마이페이지 > 알림 설정에서 수신 채널별로 언제든지 변경·철회할 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(false)
                    .isActive(true)
                    .displayOrder(6)
                    .parent(null)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.AD_EMAIL)
                    .title("[선택] 이메일 수신 동의")
                    .content("""
                            쿠팡으로부터 이메일을 통해 아래와 같은 광고성 정보를 수신하는 것에 동의합니다.

                            ■ 발송 내용
                            - 주간 특가 및 타임딜 상품 안내
                            - 개인화 상품 추천 뉴스레터
                            - 멤버십 혜택 및 쿠폰 안내
                            - 계절별·이슈별 기획전 안내

                            ■ 발송 주기
                            - 주 1~3회 (프로모션 기간 중 변동 가능)

                            ※ 동의 후 이메일 하단의 수신거부 링크 또는 마이페이지 > 알림 설정에서 언제든지 철회할 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(false)
                    .isActive(true)
                    .displayOrder(7)
                    .parent(adInfo)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.AD_SMS)
                    .title("[선택] SMS, SNS 수신 동의")
                    .content("""
                            쿠팡으로부터 SMS/MMS 및 카카오톡 등 SNS 채널을 통해 아래와 같은 광고성 정보를 수신하는 것에 동의합니다.

                            ■ 발송 내용
                            - 실시간 특가·타임딜 긴급 안내
                            - 장바구니 상품 가격 변동 알림
                            - 한정 수량 상품 입고 안내
                            - 쿠폰 만료 임박 알림

                            ■ 발송 채널
                            - SMS/MMS (문자메시지)
                            - 카카오 알림톡/친구톡

                            ■ 발송 주기
                            - 주 1~5회 (이벤트 기간 중 변동 가능)

                            ※ 동의 후 마이페이지 > 알림 설정에서 언제든지 철회할 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(false)
                    .isActive(true)
                    .displayOrder(8)
                    .parent(adInfo)
                    .build());

            termsRepository.save(Terms.builder()
                    .type(TermsType.AD_PUSH)
                    .title("[선택] 앱 푸시 수신 동의")
                    .content("""
                            쿠팡 앱의 푸시 알림을 통해 아래와 같은 광고성 정보를 수신하는 것에 동의합니다.

                            ■ 발송 내용
                            - 실시간 특가 및 타임딜 알림
                            - 관심 상품 가격 인하 알림
                            - 배송 현황 알림 (필수 알림과 별개)
                            - 쿠팡 이벤트 및 혜택 알림
                            - 로켓와우 회원 전용 혜택 알림

                            ■ 알림 유형
                            - 마케팅 푸시: 광고·프로모션 안내
                            - 개인화 푸시: 관심 상품·카테고리 기반 맞춤 알림

                            ※ 기기의 알림 설정과 앱 내 알림 설정 모두 허용되어 있어야 수신됩니다.
                            ※ 동의 후 마이페이지 > 알림 설정에서 언제든지 철회할 수 있습니다.
                            """)
                    .version("1.0")
                    .isRequired(false)
                    .isActive(true)
                    .displayOrder(9)
                    .parent(adInfo)
                    .build());
        };
    }
}
