package com.coupang.clone.domain.config;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.coupang.clone.domain.member.Member;
import com.coupang.clone.domain.member.MemberRepository;
import com.coupang.clone.domain.member.Role;

@Configuration
public class MemberDataInitializer {

    @Bean
    CommandLineRunner initMemberData(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (memberRepository.count() > 0) {
                return;
            }

            memberRepository.save(Member.builder()
                    .name("김쿠팡")
                    .password(passwordEncoder.encode("password123"))
                    .phone("010-1234-5678")
                    .email("kim@coupang.com")
                    .role(Role.CUSTOMER)
                    .address("서울특별시 강남구 테헤란로 131")
                    .birthday(LocalDate.of(1990, 5, 15))
                    .build());

            memberRepository.save(Member.builder()
                    .name("이로켓")
                    .password(passwordEncoder.encode("password123"))
                    .phone("010-2345-6789")
                    .email("lee@coupang.com")
                    .role(Role.CUSTOMER)
                    .address("서울특별시 송파구 올림픽로 300")
                    .birthday(LocalDate.of(1985, 11, 20))
                    .build());

            memberRepository.save(Member.builder()
                    .name("박배송")
                    .password(passwordEncoder.encode("password123"))
                    .phone("010-3456-7890")
                    .email("park@coupang.com")
                    .role(Role.CUSTOMER)
                    .address("경기도 성남시 분당구 판교역로 166")
                    .birthday(LocalDate.of(1995, 3, 8))
                    .build());

            memberRepository.save(Member.builder()
                    .name("최할인")
                    .password(passwordEncoder.encode("password123"))
                    .phone("010-4567-8901")
                    .email("choi@coupang.com")
                    .role(Role.CUSTOMER)
                    .address("부산광역시 해운대구 센텀중앙로 48")
                    .birthday(LocalDate.of(1992, 7, 25))
                    .build());

            memberRepository.save(Member.builder()
                    .name("관리자")
                    .password(passwordEncoder.encode("admin1234"))
                    .phone("010-0000-0000")
                    .email("admin@coupang.com")
                    .role(Role.ADMIN)
                    .address("서울특별시 송파구 송파대로 570")
                    .birthday(LocalDate.of(1988, 1, 1))
                    .build());
        };
    }
}
