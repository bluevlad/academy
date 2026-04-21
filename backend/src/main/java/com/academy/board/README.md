# Board (게시판) 패키지

게시판 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
board/
├── BoardApi.java               # 게시판 REST API 컨트롤러
└── service/
    ├── BoardService.java       # 게시판 비즈니스 로직 서비스 (@Service)
    ├── BoardVO.java            # 게시판 Value Object
    ├── BoardCommentVO.java     # 게시판 댓글 Value Object
    ├── BoardFileVO.java        # 게시판 파일 Value Object
    └── BoardMngVO.java         # 게시판 관리 Value Object
```

## 주요 기능

### BoardApi
- **GET** `/api/board/list` - 게시판 목록 조회
- **GET** `/api/board/view` - 게시판 상세 조회
- **POST** `/api/board/save` - 게시판 등록
- **PUT** `/api/board/update` - 게시판 수정
- **DELETE** `/api/board/delete` - 게시판 삭제

### BoardService
- 게시판 CRUD 작업 처리
- 게시판 댓글 관리
- 게시판 파일 첨부 관리
- MyBatis BoardMapper를 통한 데이터베이스 연동

### Value Objects
- **BoardVO**: 게시판 기본 정보 (CommonVO 상속)
- **BoardCommentVO**: 게시판 댓글 정보
- **BoardFileVO**: 게시판 첨부파일 정보
- **BoardMngVO**: 게시판 관리 정보

## 아키텍처

```
[Client]
   ↓
[BoardApi] - REST API Layer
   ↓
[BoardService] - Business Logic Layer
   ↓
[BoardMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[boardSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 게시판 목록 조회
GET /api/board/list?currentPage=1&pageRow=10&SEARCHTYPE=제목&SEARCHTEXT=공지

// 게시판 등록
POST /api/board/save
{
  "title": "게시판 제목",
  "content": "게시판 내용",
  "category": "공지사항"
}
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
