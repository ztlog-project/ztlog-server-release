
# 🚀 ztlog-server-release

개발 블로그의 백엔드 시스템입니다. Spring Boot 기반의 **멀티 모듈 아키텍처**로 설계되었으며, 관리자 설정 및 블로그 콘텐츠 제공을 위한 REST API를 지원합니다.

---

### 🛠 기술 스택 (Tech Stack)

| 분류 | 기술 |
| --- | --- |
| Framework | Spring Boot 3.2.2 |
| Database | MySQL 8.0 |
| ORM | Spring Data JPA, MyBatis (Hybrid) |
| Security | Spring Security, JWT |
| Documentation | Swagger (OpenAPI 3.0) |

<br>

### 🏗 프로젝트 구조 (Project Structure)

```text
ztlog-server-release/
├── ztlog-core/           # 공통 모듈
│   ├── entity/           # JPA 엔티티 (@MapsId, @EmbeddedId 구조)
│   ├── repository/       # QueryDSL 기반 데이터 접근 계층
│   └── common/           # 공통 유틸리티 및 예외 처리
├── ztlog-admin/          # 관리자 서비스 (Port: 8080)
│   ├── controller/       # 게시글 등록/수정/삭제 및 파일 관리
│   ├── mapper/           # MyBatis 기반 통계/대량 조회 쿼리
│   └── config/security/  # JWT 인증 및 권한 제어
└── ztlog-api/            # 사용자 서비스 (Port: 8086)
    ├── controller/       # 게시글 조회 및 태그 검색
    └── config/swagger/   # API 명세 자동화 설정

```

<br>

### 🗄 데이터베이스 구조 (Database Design)

물리적 외래 키(FK) 제약을 제거하여 유연성을 확보하고, 정합성은 애플리케이션 계층(JPA)에서 관리합니다.

* **`user_mst`** — 사용자 계정 및 권한 관리
* **`contents_mst`** — 게시글 메타데이터 (제목, 부제목 등)
* **`contents_dtl`** — 게시글 상세 본문 (`@MapsId`를 통한 마스터 PK 공유)
* **`tags_mst`** — 태그 마스터 정보
* **`contents_tags`** — 게시글-태그 다대다(N:M) 매핑 테이블
* **`file_mst`** — 첨부 파일 이력 관리

<br>

---

### 📖 API Documentation

* **Admin API Docs** — `:8080/admin/swagger-ui/index.html`
* **Front API Docs** — `:8086/front/swagger-ui/index.html`

![js](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
