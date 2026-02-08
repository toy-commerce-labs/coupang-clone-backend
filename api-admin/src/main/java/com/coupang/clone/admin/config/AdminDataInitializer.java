package com.coupang.clone.admin.config;

import com.coupang.clone.domain.member.Member;
import com.coupang.clone.domain.member.MemberRepository;
import com.coupang.clone.domain.member.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AdminDataInitializer implements ApplicationRunner {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        String adminEmail = "admin@coupang.com";

        if (memberRepository.findByEmail(adminEmail).isEmpty()) {
            Member admin = Member.builder()
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin1234"))
                    .name("관리자")
                    .role(Role.ADMIN)
                    .build();

            memberRepository.save(admin);
            log.info("기본 관리자 계정이 생성되었습니다: {}", adminEmail);
        }
    }
}
