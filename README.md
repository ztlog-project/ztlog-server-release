

#  ztlog-server-release

---
Devlog Project for BackEnd


![js](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)


## 📂 프로젝트 구조 및 상세 설명

```text
ztlog-server-release/
├── ztlog-core/           # 시스템 공통 및 핵심 도메인 모듈
│   └── src/main/java/com/devlog/core/
│       ├── common/       # 공통 상수, Enum, 응답 DTO, 유틸리티
│       ├── config/       # JPA, QueryDSL, 보안 관련 공통 설정
│       ├── entity/       # DB 테이블과 매핑되는 JPA 엔티티 클래스
│       └── repository/   # 데이터 액세스를 위한 Spring Data JPA 및 QueryDSL 레포지토리
│
├── ztlog-api/            # 일반 사용자용 서비스 제공 모듈
│   └── src/main/java/com/devlog/api/
│       ├── controller/   # 게시글 조회, 검색 등 읽기 전용 API 엔드포인트
│       ├── service/      # 비즈니스 로직 및 조회용 데이터 가공 서비스
│       └── config/       # API 전용 Swagger 명세 설정
│
└── ztlog-admin/          # 블로그 운영 및 관리자 전용 모듈
    └── src/main/java/com/devlog/admin/
        ├── config/       # 관리자 권한 제어를 위한 Spring Security 설정
        ├── controller/   # 콘텐츠 CRUD 및 파일 업로드 관리 API
        ├── service/      # 관리자 전용 비즈니스 로직 (콘텐츠 생성/수정/삭제)
        └── resources/    # 관리자 모듈 환경 설정 및 MyBatis 매퍼 파일

```

## 🛠 모듈별 핵심 역할

###  ztlog-core (Core Module)

* **엔티티 설계**  <br>
콘텐츠 마스터(`Content`)와 상세 본문(`ContentDetail`)을 1:1 구조로 설계

###  ztlog-api (Service Module)

* **조회 최적화**  <br> 방문자가 블로그 게시글을 빠르게 검색하고 태그별로 필터링할 수 있는 기능을 제공합니다.

###  ztlog-admin (Management Module)

* **콘텐츠 생명주기 관리**  <br> 게시글의 등록, 수정, 삭제 시 관련 태그와 첨부파일 정보가 트랜잭션 단위로 정합성 있게 처리되도록 보장합니다.
 
