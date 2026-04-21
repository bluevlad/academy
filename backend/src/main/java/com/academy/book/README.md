# Book (교재) 패키지

교재 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
book/
├── BookApi.java            # 교재 REST API 컨트롤러
└── service/
    ├── BookService.java    # 교재 비즈니스 로직 서비스 (@Service)
    └── BookVO.java         # 교재 Value Object
```

## 주요 기능

### BookApi
- **GET** `/api/book/list` - 교재 목록 조회
- **GET** `/api/book/view` - 교재 상세 조회
- **POST** `/api/book/save` - 교재 등록
- **PUT** `/api/book/update` - 교재 수정
- **DELETE** `/api/book/delete` - 교재 삭제
- **GET** `/api/book/caSubjectTeacherList` - 과목별 강사 목록 조회
- **GET** `/api/book/learningFormList` - 학습형태 목록 조회

### BookService
- 교재 CRUD 작업 처리
- 강의와 연계된 교재 정보 관리
- MyBatis BookMapper를 통한 데이터베이스 연동

### BookVO
- CommonVO를 상속하여 공통 필드 사용
- 교재 관련 데이터 전달 객체
- 파일 업로드 지원 (MultipartFile)

## 아키텍처

```
[Client]
   ↓
[BookApi] - REST API Layer
   ↓
[BookService] - Business Logic Layer
   ↓
[BookMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[bookSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 교재 목록 조회
GET /api/book/list?currentPage=1&pageRow=10&SEARCHKIND=all&SEARCHTEXT=수학

// 교재 등록
POST /api/book/save
{
  "rscId": "BOOK001",
  "rscNm": "수학의 정석",
  "rscImg": "math_book.jpg",
  "rscCost": "25000",
  "rscSpec": "고등 수학 교재"
}

// 과목별 강사 목록
GET /api/book/caSubjectTeacherList?SUBJECT_CD=MATH
```

## 참고사항

- 파일 업로드는 FileUtil을 통해 처리됩니다
- 모든 API는 세션 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- 교재와 강의는 연관 관계를 가집니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
