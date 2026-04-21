# Member (회원) 패키지

회원 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
member/
├── MemberApi.java            # 회원 REST API 컨트롤러
└── service/
    ├── MemberService.java    # 회원 비즈니스 로직 서비스 (@Service)
    └── MemberVO.java         # 회원 Value Object
```

## 주요 기능

### MemberApi
- **GET** `/api/member/list` - 회원 목록 조회
- **GET** `/api/member/view` - 회원 상세 조회
- **POST** `/api/member/save` - 회원 등록
- **PUT** `/api/member/update` - 회원 정보 수정
- **DELETE** `/api/member/delete` - 회원 삭제
- **GET** `/api/member/checkDuplicate` - 중복 확인 (ID, 이메일 등)

### MemberService
- 회원 CRUD 작업 처리
- 회원 인증 및 권한 관리
- MyBatis MemberMapper를 통한 데이터베이스 연동

### MemberVO
- CommonVO를 상속하여 공통 필드 사용
- 회원 관련 데이터 전달 객체
- 개인정보 보호 처리

## 아키텍처

```
[Client]
   ↓
[MemberApi] - REST API Layer
   ↓
[MemberService] - Business Logic Layer
   ↓
[MemberMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[memberSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 회원 목록 조회
GET /api/member/list?currentPage=1&pageRow=10&SEARCHTYPE=USER_NM&SEARCHTEXT=홍길동

// 회원 등록
POST /api/member/save
{
  "userId": "hong123",
  "userNm": "홍길동",
  "userEmail": "hong@example.com",
  "userPhone": "010-1234-5678"
}

// 중복 확인
GET /api/member/checkDuplicate?type=userId&value=hong123
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 비밀번호는 암호화되어 저장됩니다
- 페이징은 CommonVO를 통해 처리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- 개인정보 처리 시 보안에 주의가 필요합니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
