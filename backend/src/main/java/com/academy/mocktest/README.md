# MockTest (모의고사) 패키지

모의고사 관련 기능을 관리하는 RESTful API를 제공합니다.

## 패키지 구조

```
mocktest/
├── board/                          # 모의고사 게시판
│   ├── MockBoardApi.java           # 게시판 REST API 컨트롤러
│   └── service/
│       ├── MockBoardService.java   # 게시판 비즈니스 로직 서비스
│       └── MockBoardVO.java        # 게시판 Value Object
├── lectureFees/                    # 수강료 관리
│   ├── LectureFeesApi.java         # 수강료 REST API 컨트롤러
│   └── service/
│       ├── LectureFeesService.java # 수강료 비즈니스 로직 서비스
│       └── LectureFeesVO.java      # 수강료 Value Object
├── mouigosa/                       # 모의고사 관리
│   ├── apply/                      # 모의고사 신청
│   │   ├── MouiApplyApi.java       # 신청 REST API 컨트롤러
│   │   └── service/
│   │       ├── MouiApplyService.java
│   │       └── MouiApplyVO.java
│   └── exam/                       # 모의고사 시험
│       ├── MouiExamApi.java        # 시험 REST API 컨트롤러
│       └── service/
│           ├── MouiExamService.java
│           └── MouiExamVO.java
├── offExamReg/                     # 오프라인 시험 등록
│   ├── exam/                       # 오프라인 시험 관리
│   │   ├── OffExamApi.java         # 시험 REST API 컨트롤러
│   │   └── service/
│   │       ├── OffExamService.java
│   │       └── OffExamVO.java
│   └── registration/               # 오프라인 시험 등록
│       ├── OffExamRegApi.java      # 등록 REST API 컨트롤러
│       └── service/
│           ├── OffExamRegService.java
│           └── OffExamRegVO.java
└── stats/                          # 성적 통계
    ├── person/                     # 개인별 통계
    │   ├── PersonStatsApi.java     # 개인 통계 REST API 컨트롤러
    │   └── service/
    │       ├── PersonStatsService.java
    │       └── PersonStatsVO.java
    └── total/                      # 전체 통계
        ├── TotalStatsApi.java      # 전체 통계 REST API 컨트롤러
        └── service/
            ├── TotalStatsService.java
            └── TotalStatsVO.java
```

## 주요 기능

### MockBoardApi (모의고사 게시판)
- **GET** `/api/mocktest/board/getMockBoardList` - 게시글 목록 조회
- **GET** `/api/mocktest/board/getMockBoardDetail` - 게시글 상세 조회
- **POST** `/api/mocktest/board/insertMockBoard` - 게시글 등록
- **POST** `/api/mocktest/board/updateMockBoard` - 게시글 수정
- **POST** `/api/mocktest/board/deleteMockBoard` - 게시글 삭제
- **POST** `/api/mocktest/board/updateAnswer` - Q&A 답변 등록/수정
- **GET** `/api/mocktest/board/getNoticeTopList` - 상단 공지사항 목록

### LectureFeesApi (수강료 관리)
- **GET** `/api/mocktest/lectureFees/getLectureFeesList` - 수강료 목록 조회
- **GET** `/api/mocktest/lectureFees/getLectureFeesDetail` - 수강료 상세 조회
- **POST** `/api/mocktest/lectureFees/insertLectureFees` - 수강료 등록
- **POST** `/api/mocktest/lectureFees/updateLectureFees` - 수강료 수정
- **POST** `/api/mocktest/lectureFees/deleteLectureFees` - 수강료 삭제
- **GET** `/api/mocktest/lectureFees/getPackageFeesList` - 패키지 수강료 목록
- **GET** `/api/mocktest/lectureFees/getExamYearList` - 시험년도 목록 조회

### MouiApplyApi (모의고사 신청)
- **GET** `/api/mocktest/mouigosa/apply/getMouiApplyList` - 신청 목록 조회
- **GET** `/api/mocktest/mouigosa/apply/getMouiApplyDetail` - 신청 상세 조회
- **POST** `/api/mocktest/mouigosa/apply/insertMouiApply` - 신청 등록
- **POST** `/api/mocktest/mouigosa/apply/updateMouiApply` - 신청 수정
- **POST** `/api/mocktest/mouigosa/apply/deleteMouiApply` - 신청 삭제
- **POST** `/api/mocktest/mouigosa/apply/updateApplyStatus` - 신청 상태 변경
- **POST** `/api/mocktest/mouigosa/apply/updateExamResult` - 시험 결과 등록

### OffExamApi (오프라인 시험 관리)
- **GET** `/api/mocktest/offExamReg/exam/getOffExamList` - 시험 목록 조회
- **GET** `/api/mocktest/offExamReg/exam/getOffExamDetail` - 시험 상세 조회
- **POST** `/api/mocktest/offExamReg/exam/insertOffExam` - 시험 등록
- **POST** `/api/mocktest/offExamReg/exam/updateOffExam` - 시험 수정
- **POST** `/api/mocktest/offExamReg/exam/deleteOffExam` - 시험 삭제
- **POST** `/api/mocktest/offExamReg/exam/updateOffExamStatus` - 시험 상태 변경

### PersonStatsApi (개인별 성적 통계)
- **GET** `/api/mocktest/stats/person/getPersonStatsList` - 개인별 성적 목록 조회
- **GET** `/api/mocktest/stats/person/getPersonStatsDetail` - 개인 성적 상세 조회
- **GET** `/api/mocktest/stats/person/getPersonSubjectStats` - 개인 과목별 성적 조회
- **GET** `/api/mocktest/stats/person/getPersonTotalStats` - 개인 전체 성적 통계 조회

### TotalStatsApi (전체 성적 통계)
- **GET** `/api/mocktest/stats/total/getTotalStatsList` - 전체 성적 통계 목록 조회
- **GET** `/api/mocktest/stats/total/getTotalStatsDetail` - 전체 성적 통계 상세 조회
- **GET** `/api/mocktest/stats/total/getTotalSubjectStats` - 과목별 전체 통계 조회
- **GET** `/api/mocktest/stats/total/getOffererCnt` - 응시자 현황 조회

## 아키텍처

```
[Client]
   ↓
[*Api] - REST API Layer
   ↓
[*Service] - Business Logic Layer
   ↓
[*Mapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[*.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 참고사항

- 모의고사 관련 기능은 계층적 패키지 구조로 구성되어 있습니다
- 각 하위 패키지는 독립적인 도메인 모듈로 동작합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 모든 API는 인증이 필요합니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
