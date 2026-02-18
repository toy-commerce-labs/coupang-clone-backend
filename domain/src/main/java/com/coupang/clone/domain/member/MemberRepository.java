package com.coupang.clone.domain.member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByUserId(String userId);

    // 기존 admin 계정 userId 업데이트용 (DB에 이미 데이터가 있는 경우)
    @Modifying
    @Query("UPDATE Member m SET m.userId = :userId WHERE m.email = :email AND m.userId IS NULL")
    void updateUserIdByEmail(@Param("email") String email, @Param("userId") String userId);
}
