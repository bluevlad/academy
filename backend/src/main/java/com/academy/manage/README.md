# 관리 패키지 (manage)

학원 운영 관련 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
manage/
├── README.md
├── categorySale/               # 카테고리별 매출 관리
│   ├── CategorySaleApi.java
│   └── service/
│       ├── CategorySaleService.java
│       └── CategorySaleVO.java
├── lectureYear/                # 강의 연도별 관리
│   ├── LectureYearApi.java
│   └── service/
│       ├── LectureYearService.java
│       └── LectureYearVO.java
└── teacherCalculate/           # 강사 정산 관리
    ├── TeacherCalculateApi.java
    └── service/
        ├── TeacherCalculateService.java
        └── TeacherCalculateVO.java
```

## 하위 패키지 설명

### 1. CategorySale (카테고리별 매출 관리)
카테고리별 매출 통계 및 분석 기능을 제공합니다.
- 카테고리별 매출 목록/상세 조회
- 일별/월별/년별 매출 통계
- 카테고리별 매출 비율 분석

### 2. LectureYear (강의 연도별 관리)
연도별 강의 운영 관리 기능을 제공합니다.
- 연도별 강의 목록/상세 조회
- 연도별 카테고리/강사 통계
- 연도별 매출 통계

### 3. TeacherCalculate (강사 정산 관리)
강사 정산 및 지급 관리 기능을 제공합니다.
- 강사별 정산 목록/상세 조회
- 월별 정산 관리
- 지급 처리 및 상태 관리

## API 엔드포인트 기본 경로

| 패키지 | 기본 경로 |
|--------|----------|
| CategorySale | `/api/manage/categorySale` |
| LectureYear | `/api/manage/lectureYear` |
| TeacherCalculate | `/api/manage/teacherCalculate` |

## 관련 Mapper

- **CategorySaleMapper.java**: `com.academy.mapper.CategorySaleMapper`
- **LectureYearMapper.java**: `com.academy.mapper.LectureYearMapper`
- **TeacherCalculateMapper.java**: `com.academy.mapper.TeacherCalculateMapper`

## 관련 XML Mapper

- `src/main/resources/mapper/categorySale.xml`
- `src/main/resources/mapper/lectureYear.xml`
- `src/main/resources/mapper/teacherCalculate.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
