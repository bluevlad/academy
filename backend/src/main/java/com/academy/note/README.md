# Note (쪽지) 패키지

사용자 간 쪽지 기능을 관리하는 RESTful API를 제공합니다.

## 패키지 구조

```
note/
├── NoteApi.java                # 쪽지 관리 REST API 컨트롤러
└── service/
    ├── NoteService.java        # 쪽지 비즈니스 로직 서비스 (@Service)
    └── NoteVO.java             # 쪽지 Value Object
```

## 주요 기능

### NoteApi
- **GET** `/api/note/getNoteList` - 쪽지 목록 조회
- **GET** `/api/note/getNoteDetail` - 쪽지 상세 조회
- **POST** `/api/note/insertNote` - 쪽지 등록
- **POST** `/api/note/updateNote` - 쪽지 수정
- **POST** `/api/note/deleteNote` - 쪽지 삭제
- **POST** `/api/note/updateNoteReadYn` - 쪽지 읽음 처리

## 아키텍처

```
[Client]
   ↓
[NoteApi] - REST API Layer
   ↓
[NoteService] - Business Logic Layer
   ↓
[NoteMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[noteSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 쪽지 목록 조회
GET /api/note/getNoteList?pageIndex=1&pageUnit=10

// 쪽지 등록
POST /api/note/insertNote
{
  "senderId": "user001",
  "receiverId": "user002",
  "title": "안녕하세요",
  "content": "쪽지 내용입니다."
}

// 쪽지 읽음 처리
POST /api/note/updateNoteReadYn
{
  "noteId": "NOTE001"
}
```

## 참고사항

- 쪽지는 발신자와 수신자 간 1:1 메시지입니다
- 읽음 처리는 수신자가 쪽지를 확인할 때 자동으로 처리됩니다
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
