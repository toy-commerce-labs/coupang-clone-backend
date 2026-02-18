# Coupang Clone

Spring Boot 기반 멀티 모듈 프로젝트

## 기술 스택

- Java 17
- Spring Boot 3.5.10
- Spring Security + JWT (UserDetailsService 방식)
- Spring Data JPA
- MySQL
- Gradle (멀티 모듈)
- Swagger (springdoc-openapi)

## 모듈 구조

```
coupang-clone/
├── api-customer    - 고객용 API 서버 (port: 8080)
├── api-admin       - 관리자용 API 서버 (port: 8081)
├── domain          - 엔티티, 리포지토리
├── infra           - JWT, 인증 필터, UserDetailsService
└── common          - 공통 응답, 예외 처리
```

## API 엔드포인트

### api-customer (port: 8080)

| Method | URL | 설명 | 인증 |
|--------|-----|------|------|
| POST | `/api/v1/members/login` | 고객 로그인 | 불필요 |

### api-admin (port: 8081)

| Method | URL | 설명 | 인증 |
|--------|-----|------|------|
| POST | `/api/v1/admin/members/login` | 관리자 로그인 | 불필요 |

## 인증 흐름

```
1. 로그인 요청 (userId + password)
2. AuthenticationManager → CustomUserDetailsService → DB 조회
3. Spring Security가 BCrypt 비밀번호 비교
4. 인증 성공 시 JWT 토큰 발급
5. 이후 요청: Authorization: Bearer {token} 헤더로 인증
```

## Swagger UI

- customer: http://localhost:8080/swagger-ui/index.html
- admin: http://localhost:8081/swagger-ui/index.html

## 모듈 상세

### api-customer

고객 대상 API 서버. 로그인 기능을 제공한다.

- 의존: `common`, `domain`, `infra`
- 주요 라이브러리: Spring Web, Spring Security, Spring Data JPA, Validation, MySQL, Swagger

### api-admin

관리자 대상 API 서버. 관리자 로그인 및 관리 기능을 제공한다. ADMIN 권한이 필요하다.

- 의존: `common`, `domain`, `infra`
- 주요 라이브러리: Spring Web, Spring Security, Spring Data JPA, Validation, MySQL, Swagger

### domain

JPA 엔티티와 리포지토리를 관리하는 모듈. 비즈니스 도메인 모델을 정의한다.

- 의존: `common`
- 주요 라이브러리: Spring Data JPA, Spring Security

### infra

JWT 토큰 발급/검증, 인증 필터, UserDetailsService 등 보안 관련 구현을 담당하는 모듈.

- 의존: `common`, `domain`
- 주요 라이브러리: Spring Web, Spring Security, Spring Data JPA, JJWT

### common

전역 예외 처리, 공통 API 응답 포맷 등 모든 모듈에서 공유하는 코드를 관리하는 모듈.

- 의존: 없음
- 주요 라이브러리: Spring Web

## 테스트 계정

| userId | password | role |
|--------|----------|------|
| kim123 | password123 | CUSTOMER |
| lee123 | password123 | CUSTOMER |
| park123 | password123 | CUSTOMER |
| choi123 | password123 | CUSTOMER |
| admin | admin1234 | ADMIN |
