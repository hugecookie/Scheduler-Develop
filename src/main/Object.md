### 개발 전, 공통 조건 `필독`

- 모든 테이블은 고유 식별자(ID)를 가집니다.
- `3 Layer Architecture` 에 따라 각 Layer의 목적에 맞게 개발합니다.
- CRUD 필수 기능은 모두 데이터베이스 연결 및 `JPA`를 사용해서 개발합니다.
- 인증/인가 절차는 `Cookie/Session`을 활용하여 개발합니다.
- JPA 연관관계는 `단방향` 입니다. 정말 필요한 경우에만 `양방향` 적용!
- 키워드
    - CRUD 관련 어노테이션
        - `@Entity`: 데이터베이스 테이블과 매핑되는 클래스에 사용합니다.
        - `@Id`: 해당 필드를 기본 키로 지정합니다.
        - `@GeneratedValue`: 기본 키 생성 전략을 설정합니다.
        - `@Repository`: DAO(Data Access Object) 클래스에 사용하여 데이터 접근을 명시합니다.
        - `@ManyToOne`/`@OneToMany`: 테이블간 연간관계를 설정 합니다.
        - `@JoinColumn` : join할 컬럼을 설정 합니다.
    - Validation 어노테이션
        - `@Valid`

### Lv 0. API 명세 및 ERD 작성   `필수`

- [ ]  **API 명세서 작성하기**
    - [ ]  API명세서는 프로젝트 root(최상위) 경로의 `README.md` 에 작성
    - 참고) API 명세서 작성 가이드
        - API 명세서란 API명, 요청 값(파라미터), 반환 값, 인증/인가 방식, 데이터 및 전달 형식 등 API를 정확하게 호출하고 그 결과를 명확하게 해석하는데 필요한 정보들을 일관된 형식으로 기술한 것을 의미합니다.
        - request 및 response는 [json(링크)](https://namu.wiki/w/JSON) 형태로 작성합니다.

      [제목 없음](https://www.notion.so/1c32dc3ef51481d4bbb7c2f2890dea3f?pvs=21)

      > API 명세서 추천 무료 Tool
      >
      >
      > [Document your APIs in Postman | Postman Learning Center](https://learning.postman.com/docs/publishing-your-api/api-documentation-overview/)
>

- [ ]  **ERD 작성하기**
    - [ ]  ERD는 프로젝트 root(최상위) 경로의 `README.md` 에 첨부
    - 참고) ERD 작성 가이드

      출처: https://online.visual-paradigm.com/ko/community/share/er-diagram-for-online-book-store-1gnrscfbme

        - API 명세 작성을 통해 서비스의 큰 흐름과 기능을 파악 하셨다면 이제는 기능을 구현하기 위해 필요한 데이터가 무엇인지 생각해봐야합니다.
            - 이때, 구현해야 할 서비스의 영역별로 필요한 데이터를 설계하고 각 영역간의 관계를 표현하는 방법이 있는데 이를 ERD(Entity Relationship Diagram)라 부릅니다.
        - ERD 작성간에 다음과 같은 항목들을 학습합니다.
            - E(Entity. 개체)
                - 구현 할 서비스의 영역에서 필요로 하는 데이터를 담을 개체를 의미합니다.
                    - ex) `책`, `저자`, `독자`, `리뷰`
            - A(Attribute. 속성)
                - 각 개체가 가지는 속성을 의미합니다.
                    - ex) 책은 `제목`, `언어`, `출판일`, `저자`, `가격` 등의 속성을 가질 수 있습니다.
            - R(Relationship. 관계)
                - 개체들 사이의 관계를 정의합니다.
                    - ex) `저자`는 여러 권의 `책`을 집필할 수 있습니다. 이때, 저자와 책의 관계는 일대다(1:N) 관계입니다.
>

- [ ]  **SQL 작성하기**
    - [ ]  설치한 데이터베이스(Mysql)에 ERD를 따라 테이블을 생성
    - 참고) SQL 작성 가이드
        - 과제 프로그램의 root(최상위) 경로에`schedule.sql` 파일을 만들고, 테이블 생성에 필요한 query를 작성하세요.

### Lv 1. 일정 CRUD  `필수`

- [ ]  일정을 생성, 조회, 수정, 삭제할 수 있습니다.
- [ ]  일정은 아래 필드를 가집니다.
    - [ ]  `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다. → `3주차 JPA Auditing 참고!`

### Lv 2. 유저 CRUD  `필수`

- [ ]  유저를 생성, 조회, 수정, 삭제할 수 있습니다.
- [ ]  유저는 아래와 같은 필드를 가집니다.
    - [ ]  `유저명`, `이메일`, `작성일` , `수정일` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용합니다.
- [ ]  연관관계 구현
    - [ ]  일정은 이제 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가집니다.
### Lv 3. 회원가입  `필수`

- [ ]  유저에 `비밀번호` 필드를 추가합니다.
    - 비밀번호 암호화는 도전 기능에서 수행합니다.

### Lv 4. 로그인(인증)  `필수`

- 키워드

  **인터페이스**

    - HttpServletRequest / HttpServletResponse : 각 HTTP 요청에서 주고받는 값들을 담고 있습니다.
- [ ]  **설명**
    - [ ]  **Cookie/Session**을 활용해 로그인 기능을 구현합니다. → `2주차 Servlet Filter 실습 참고!`
    - [ ]  필터를 활용해 인증 처리를 할 수 있습니다.
    - [ ]  `@Configuration` 을 활용해 필터를 등록할 수 있습니다.
- [ ]  **조건**
    - [ ]  `이메일`과 `비밀번호`를 활용해 로그인 기능을 구현합니다.
    - [ ]  회원가입, 로그인 요청은 인증 처리에서 제외합니다.
- [ ]  **예외처리**
    - [ ]  로그인 시 이메일과 비밀번호가 일치하지 않을 경우 HTTP Status code 401을 반환합니다.
    - [ ]  응답 예시
```json
{
	"timestamp": "2025-03-26T14:26:45",
	"status": 400,
	"error": "BAD_REQUEST",
	"code": "C001",
	"message": "잘못된 입력값입니다",
	"path": "/api/login",
	"fieldErrors": [
		{
			"field": "username",
			"message": "아이디 입력은 필수입니다"
		}
	]
}
```

```json
{
    "code": "C001",
    "message": "아이디 입력은 필수입니다",
    "status": "BAD_REQUEST"
}
```

3️⃣ 도전 기능 가이드

### Lv 5. 다양한 예외처리 적용하기  `도전`

- [ ]  Validation을 활용해 다양한 예외처리를 적용해 봅니다. → `1주차 Bean Validation 참고!`
- [ ]  정해진 예외처리 항목이 있는것이 아닌 프로젝트를 분석하고 예외사항을 지정해 봅니다.
    - [ ]  Ex) 할일 제목은 10글자 이내, 유저명은 4글자 이내
    - [ ]  `@Pattern`을 사용해서 회원 가입 Email 데이터 검증 등
        - [ ]  정규표현식을 적용하되, 정규표현식을 어떻게 쓰는지 몰두하지 말 것!
        - [ ]  검색해서 나오는 것을 적용하는 것으로 충분!

### Lv 6. 비밀번호 암호화  `도전`

- [ ]  Lv.3에서 추가한 `비밀번호` 필드에 들어가는 비밀번호를 암호화합니다.
    - [ ]  암호화를 위한 `PasswordEncoder`를 직접 만들어 사용합니다.
        - PasswordEncoder 참고 코드
            1. `build.gradle` 에 아래의 의존성을 추가해주세요.

                ```java
                implementation 'at.favre.lib:bcrypt:0.10.2'
                ```

            2. `config` 패키지가 없다면 추가하고, 아래의 클래스를 추가해주세요.

                ```java
                import at.favre.lib.crypto.bcrypt.BCrypt;
                import org.springframework.stereotype.Component;
                
                @Component
                public class PasswordEncoder {
                
                    public String encode(String rawPassword) {
                        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
                    }
                
                    public boolean matches(String rawPassword, String encodedPassword) {
                        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
                        return result.verified;
                    }
                }
                ```


### Lv 7. 댓글 CRUD  `도전`

- [ ]  생성한 일정에 댓글을 남길 수 있습니다.
    - [ ]  댓글과 일정은 연관관계를 가집니다. →  `3주차 연관관계 매핑 참고!`
- [ ]  댓글을 저장, 조회, 수정, 삭제할 수 있습니다.
- [ ]  댓글은 아래와 같은 필드를 가집니다.
    - [ ]  `댓글 내용`, `작성일`, `수정일`, `유저 고유 식별자`, `일정 고유 식별자` 필드
    - [ ]  `작성일`, `수정일` 필드는 `JPA Auditing`을 활용하여 적용합니다.

### Lv 8. 일정 페이징 조회  `도전`

- 키워드

  **데이터베이스**

    - offset / limit : SELECT 쿼리에 적용해서 데이터를 제한 범위에 맞게 조회할 수 있습니다.

  **페이징**

    - Pageable : Spring Data JPA에서 제공되는 페이징 관련 인터페이스 입니다.
    - PageRequest : Spring Data JPA에서 제공되는 페이지 요청 관련 클래스입니다.
- [ ]  일정을 Spring Data JPA의 `Pageable`과 `Page` 인터페이스를 활용하여 페이지네이션을 구현
    - [ ]  `페이지 번호`와 `페이지 크기`를 쿼리 파라미터로 전달하여 요청하는 항목을 나타냅니다.
    - [ ]  `할일 제목`, `할일 내용`, `댓글 개수`, `일정 작성일`, `일정 수정일`, `일정 작성 유저명` 필드를 조회합니다.
    - [ ]  디폴트 `페이지 크기`는 10으로 적용합니다.
- [ ]  일정의 `수정일`을 기준으로 내림차순 정렬합니다.