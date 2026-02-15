
# ztlog-server-release

개발 블로그의 백엔드 시스템입니다. Spring Boot 기반의 **멀티 모듈 아키텍처**로 설계되었으며, 관리자 설정 및 블로그 콘텐츠 제공을 위한 REST API를 지원합니다.

![Spring](https://img.shields.io/badge/Spring_Boot-3.2.2-6DB33F?style=flat-square&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-007396?style=flat-square&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?style=flat-square&logo=mysql&logoColor=white)

---

## 기술 스택 (Tech Stack)

| 분류 | 기술 |
| --- | --- |
| Language | Java 17 |
| Framework | Spring Boot 3.2.2 |
| Database | MySQL 8.0 |
| ORM | Spring Data JPA + QueryDSL, MyBatis (Hybrid) |
| Security | Spring Security, JWT (JJWT 0.11.5) |
| Build | Gradle (Multi-module) |
| Documentation | Swagger (OpenAPI 3.0) |

---

## 프로젝트 구조 (Project Structure)

```text
ztlog-server-release/
├── ztlog-core/           # 공통 모듈 (no boot JAR)
│   ├── entity/           # JPA 엔티티 (@MapsId, @EmbeddedId 구조)
│   ├── repository/       # QueryDSL 기반 데이터 접근 계층
│   └── common/           # 공통 유틸리티, 예외 계층, 응답 코드
├── ztlog-admin/          # 관리자 서비스 (Port: 8080, context: /admin)
│   ├── controller/       # 게시글 등록/수정/삭제 및 파일 관리
│   ├── mapper/           # MyBatis 기반 통계/대시보드 쿼리
│   └── config/security/  # JWT 인증 및 권한 제어
└── ztlog-api/            # 사용자 서비스 (Port: 8086, context: /front)
    ├── controller/       # 게시글 조회 및 태그 검색
    └── config/swagger/   # API 명세 자동화 설정
```

### 계층 구조

```
controller → service → repository (core) → entity (core)
```

---

## 데이터베이스 구조 (Database Design)

물리적 외래 키(FK) 제약을 제거하여 유연성을 확보하고, 정합성은 애플리케이션 계층(JPA)에서 관리합니다.

| 테이블 | 설명 |
| --- | --- |
| `user_mst` | 사용자 계정 및 권한 관리 |
| `contents_mst` | 게시글 메타데이터 (제목, 부제목 등) |
| `contents_dtl` | 게시글 상세 본문 (`@MapsId`를 통한 마스터 PK 공유) |
| `tags_mst` | 태그 마스터 정보 |
| `contents_tags` | 게시글-태그 다대다(N:M) 매핑 테이블 |
| `file_mst` | 첨부 파일 이력 관리 |

---

## 빌드 및 실행 (Build & Run)

```bash
# 전체 빌드
./gradlew build

# 모듈별 빌드
./gradlew :ztlog-api:build
./gradlew :ztlog-admin:build
./gradlew :ztlog-core:build

# 서버 실행
./gradlew :ztlog-admin:bootRun   # 관리자 서비스 (8080)
./gradlew :ztlog-api:bootRun     # 사용자 서비스 (8086)

# 테스트
./gradlew test
./gradlew :ztlog-admin:test
./gradlew :ztlog-api:test

# 클린 빌드
./gradlew clean build
```

> **프로파일**: `local`, `dev`, `prd` (공통 설정은 `common` 프로파일로 관리)

---

## API 문서 (API Documentation)

| 서비스 | URL |
| --- | --- |
| Admin Swagger UI | `http://localhost:8080/admin/swagger-ui/index.html` |
| Front Swagger UI | `http://localhost:8086/front/swagger-ui/index.html` |

---

## 보안 (Security)

- JWT 기반 인증 (JJWT 0.11.5), 커스텀 `TokenUtils` 및 `JwtAuthenticationEntryPoint`
- 비밀번호 암호화: `Pbkdf2PasswordEncoder` (HMAC-SHA512, 16-byte salt, 310,000 iterations)
- 공개 엔드포인트: `/api/v1/user/login`, `/api/v1/user/signup`, `/main/**`, Swagger 경로
