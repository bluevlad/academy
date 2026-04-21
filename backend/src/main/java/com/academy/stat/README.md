# Stat (통계) 패키지

매출 통계 및 검색어 통계를 관리하는 RESTful API를 제공합니다.

## 패키지 구조

```
stat/
├── SalesStatApi.java           # 매출 통계 REST API 컨트롤러
└── service/
    ├── SalesStatService.java   # 매출 통계 비즈니스 로직 서비스 (@Service)
    └── SalesStatVO.java        # 매출 통계 Value Object
```

## 주요 기능

### SalesStatApi (강사 관련)
- **GET** `/api/stat/teacher/getTeacherList` - 강사 목록 조회
- **GET** `/api/stat/teacher/getTeacherSubjectList` - 강사별 과목 목록 조회
- **GET** `/api/stat/teacher/getTeacherDetail` - 강사 상세 조회
- **GET** `/api/stat/teacher/getTeacherSalesStat` - 강사 매출 통계 조회

### SalesStatApi (매출 관련)
- **GET** `/api/stat/sales/getUserBuyStat` - 회원 구매 통계 조회

### SalesStatApi (검색어 관련)
- **GET** `/api/stat/search/getSearchKeywordList` - 검색어 통계 목록 조회

## 아키텍처

```
[Client]
   ↓
[SalesStatApi] - REST API Layer
   ↓
[SalesStatService] - Business Logic Layer
   ↓
[SalesStatMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[salesStatSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 강사 매출 통계 조회
GET /api/stat/teacher/getTeacherSalesStat?teacherId=T001&startDate=2024-01-01&endDate=2024-12-31

// 검색어 통계 조회
GET /api/stat/search/getSearchKeywordList?pageIndex=1&pageUnit=20&startDate=2024-01-01&endDate=2024-01-31

// 회원 구매 통계 조회
GET /api/stat/sales/getUserBuyStat?userId=user001
```

## 참고사항

- 통계 데이터는 기간별 조회가 가능합니다
- 강사별, 과목별, 회원별 다양한 통계를 제공합니다
- 검색어 통계는 사용자 검색 패턴 분석에 활용됩니다
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
