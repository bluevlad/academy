# BookCmmt (교재 구매 후기) 패키지

교재 구매 후기 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
bookCmmt/
├── BookCmmtApi.java           # 교재 구매 후기 REST API 컨트롤러
└── service/
    ├── BookCmmtService.java   # 교재 구매 후기 비즈니스 로직 서비스 (@Service)
    └── BookCmmtVO.java        # 교재 구매 후기 Value Object
```

## 주요 기능

### BookCmmtApi
- **GET** `/api/bookCmmt/list` - 교재 구매 후기 목록 조회
- **GET** `/api/bookCmmt/view` - 교재 구매 후기 상세 조회 (댓글 포함)
- **DELETE** `/api/bookCmmt/delete` - 교재 구매 후기 삭제

### BookCmmtService
- 교재 구매 후기 조회 및 관리
- 후기 댓글 목록 조회
- MyBatis BookCmmtMapper를 통한 데이터베이스 연동

### BookCmmtVO
- CommonVO를 상속하여 공통 필드 사용
- 교재 구매 후기 관련 데이터 전달 객체
- 댓글 페이징 지원

## 아키텍처

```
[Client]
   ↓
[BookCmmtApi] - REST API Layer
   ↓
[BookCmmtService] - Business Logic Layer
   ↓
[BookCmmtMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[bookCmmtSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 교재 구매 후기 목록 조회
GET /api/bookCmmt/list?currentPage=1&pageRow=10&SEARCHKIND=RSC_NM&SEARCHTEXT=수학

// 교재 구매 후기 상세 조회 (댓글 포함)
GET /api/bookCmmt/view?SEQ=1&RSC_ID=BOOK001&currentPageReply=1&pageRowReply=10

// 교재 구매 후기 삭제
DELETE /api/bookCmmt/delete?DELETE_SEQ=1&DELETE_RSC_ID=BOOK001
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 댓글 페이징은 별도로 관리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- Book 패키지와 연관 관계를 가집니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
