# Exam (시험) 패키지

시험 및 문제은행 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
exam/
├── ExamApi.java              # 시험 REST API 컨트롤러
├── ExamBankApi.java          # 문제은행 REST API 컨트롤러
└── service/
    ├── ExamService.java      # 시험 비즈니스 로직 서비스 (@Service)
    ├── ExamVO.java           # 시험 Value Object
    ├── ExamBankService.java  # 문제은행 비즈니스 로직 서비스 (@Service)
    ├── ExamBankVO.java       # 문제은행 Value Object
    └── ExamReqVO.java        # 시험 요청 Value Object
```

## 주요 기능

### ExamApi
- **GET** `/api/exam/list` - 시험 목록 조회
- **GET** `/api/exam/view` - 시험 상세 조회
- **POST** `/api/exam/save` - 시험 등록
- **PUT** `/api/exam/update` - 시험 수정
- **DELETE** `/api/exam/delete` - 시험 삭제

### ExamBankApi
- **GET** `/api/examBank/list` - 문제은행 목록 조회
- **GET** `/api/examBank/view` - 문제은행 상세 조회
- **POST** `/api/examBank/save` - 문제 등록
- **PUT** `/api/examBank/update` - 문제 수정
- **DELETE** `/api/examBank/delete` - 문제 삭제

### ExamService & ExamBankService
- 시험 및 문제은행 CRUD 작업 처리
- MyBatis Mapper를 통한 데이터베이스 연동

## 아키텍처

```
[Client]
   ↓
[ExamApi / ExamBankApi] - REST API Layer
   ↓
[ExamService / ExamBankService] - Business Logic Layer
   ↓
[ExamMapper / ExamBankMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[examSQL.xml / examBankSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 시험 목록 조회
GET /api/exam/list?currentPage=1&pageRow=10&SEARCHTYPE=EXAM_NM&SEARCHTEXT=중간고사

// 문제은행 목록 조회
GET /api/examBank/list?currentPage=1&pageRow=10&SUBJECT_CD=MATH
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- 시험과 문제은행은 별도로 관리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
