
---

# 📅 **Spring 일정 관리 앱 Develop (CH3 프로젝트)**

---

## **1. 프로젝트 개요**
이 프로젝트는 **Spring Data JPA를 중심으로 설계된 일정 관리 웹 애플리케이션**입니다.  
관계형 데이터베이스에 맞춰 SQL을 직접 작성하는 대신, **객체 중심의 개발 패러다임**을 따르며,  
**JPA(Entity, Repository, Auditing 등)를 기반으로 모든 데이터 흐름을 관리**합니다.

유저는 회원가입 및 로그인 후, 본인의 일정 및 댓글을 CRUD 할 수 있으며,  
**Cookie/Session 기반 인증 시스템과 Bean Validation 기반의 예외처리 체계**도 함께 포함됩니다.

---

## **2. 주요 기능**
✔ **Spring Data JPA를 이용한 엔티티 기반 도메인 모델링**  
✔ **Entity 간 단방향 연관관계 매핑 및 JPA Auditing 적용**  
✔ **일정 및 댓글 등록, 조회, 수정, 삭제 (CRUD)**  
✔ **회원가입 및 로그인 (Session 기반 인증)**  
✔ **DTO-Entity 분리 및 유효성 검증 (Bean Validation)**  
✔ **BCrypt를 이용한 비밀번호 암호화**  
✔ **Exception 패키지를 통한 전역 에러 처리 설계**

---

## **3. 프로젝트 구조**
```plaintext
📦 schedule-app
 ┣ 📂 src
 ┃ ┣ 📂 main
 ┃ ┃ ┣ 📂 java
 ┃ ┃ ┃ ┗ 📂 org.example
 ┃ ┃ ┃   ┣ 📂 config             # 필터 및 JPA 설정
 ┃ ┃ ┃   ┣ 📂 controller         # REST API 엔드포인트 정의
 ┃ ┃ ┃   ┣ 📂 dto                # 요청/응답 객체
 ┃ ┃ ┃   ┣ 📂 entity             # JPA 엔티티 (Schedule, User, Comment 등)
 ┃ ┃ ┃   ┣ 📂 exception          # 커스텀 예외 및 GlobalExceptionHandler
 ┃ ┃ ┃   ┣ 📂 filter             # 인증 필터 (LoginCheckFilter)
 ┃ ┃ ┃   ┣ 📂 repository         # JPA Repository 인터페이스 계층
 ┃ ┃ ┃   ┣ 📂 service            # 비즈니스 로직 (트랜잭션 계층)
 ┃ ┃ ┃   ┗ 📜 ScheduleDevelopApplication.java  # 애플리케이션 진입점
 ┃ ┃ ┣ 📂 resources
 ┃ ┃ ┃ ┣ 📂 postman
 ┃ ┃ ┃ ┃ ┗ 📜 schedule-api.postman_collection.json  # API 테스트용 컬렉션
 ┃ ┃ ┃ ┣ 📜 application.yml      # DB 설정 및 JPA 설정
 ┃ ┃ ┃ ┗ 📜 Object.md            # 기능 요구사항 명세
 ┣ 📂 img                        # ERD 이미지 및 문서 자료
 ┗ 📜 README.md
```

---

## **4. 개발 환경**
| 항목 | 내용 |
|------|------|
| **Java** | 17 |
| **Spring Boot** | 3.x |
| **ORM** | Spring Data JPA (Hibernate) |
| **DB** | H2 / MySQL |
| **빌드 도구** | Gradle |
| **인증** | Cookie + Session |
| **IDE** | IntelliJ / VS Code 등 |

> 📌 `application.yml` 에 설정된 `ddl-auto=create` 로 DB 자동 생성

---

## **5. 실행 방법**
```bash
# 1. 저장소 클론
git clone https://github.com/your-repo/schedule-app.git
cd schedule-app

# 2. 실행
./gradlew bootRun
```

> 🔐 로그인하지 않은 사용자는 일정/댓글 API 접근 불가

---

## **6. 사용 예시**
schedule-api.postman_collection.json
파일을 import해서 실행하기!
```http
# 일정 등록
POST /api/schedules
{
  "title": "회의",
  "content": "기획 회의입니다.",
  "userId": 1
}

# 댓글 등록
POST /api/comments
{
  "scheduleId": 1,
  "userId": 1,
  "content": "확인했습니다."
}
```

---

## **7. JPA 설계 포인트**

| 항목 | 설명 |
|------|------|
| **Entity** | `@Entity`, `@Id`, `@GeneratedValue` 를 통해 테이블 매핑 |
| **연관관계** | `@ManyToOne`, `@JoinColumn` 을 활용한 단방향 연관관계 구성 |
| **Auditing** | `@CreatedDate`, `@LastModifiedDate` → 생성일/수정일 자동처리 |
| **DTO 분리** | Entity를 외부에 노출하지 않기 위해 Request/Response DTO 분리 |
| **트랜잭션 처리** | `@Transactional` 을 서비스 계층에 적용하여 일관된 데이터 처리 |

---

## **8. 트러블슈팅**
| 문제 | 해결 방법 |
|------|-----------|
| H2 콘솔 접속 불가 | `spring.h2.console.enabled=true` 설정 필요 |
| 로그인 후 요청이 401 에러 발생 | 쿠키 값이 누락됐거나 필터 등록이 누락됨 |
| JPA Auditing 작동 안 함 | `@EnableJpaAuditing`, `@EntityListeners` 누락 여부 확인 |
| 관계 매핑 오류 | `@JoinColumn`의 `nullable=false` 조건 또는 외래키 설정 확인 |

---

## **9. 라이선스**
본 프로젝트는 **MIT License** 하에 자유롭게 사용 및 수정 가능합니다.

---

## **10. 문의**
- **Email:** phrpp5770@gmail.com
- **GitHub Issue:** [링크](https://github.com/your-repo/schedule-app/issues)

---

## **11. 참고 자료**
- [Spring 일정 관리 Velog](https://velog.io/@hyang_do/series/Spring6%EA%B8%B0%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1Develop)
- [트러블슈팅 정리](https://velog.io/@hyang_do/series/Spring6%EA%B8%B0%EC%9D%BC%EC%A0%95%EA%B4%80%EB%A6%AC%EC%95%B1Develop%ED%8A%B8%EB%9F%AC%EB%B8%94%EC%8A%88%ED%8C%85)

---