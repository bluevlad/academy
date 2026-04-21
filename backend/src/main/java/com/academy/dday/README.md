# D-Day 관리 패키지

D-Day 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
dday/
├── DdayApi.java                    # REST Controller
├── README.md
└── service/
    ├── DdayService.java            # 서비스 클래스
    └── DdayVO.java                 # Value Object
```

## 기능 설명

D-Day(시험일정, 중요 일정 등)를 관리하는 기능을 제공합니다.

### 주요 기능
- D-Day 목록 조회 (카테고리별)
- D-Day 상세 조회
- D-Day 등록/수정/삭제
- 카테고리 목록 조회

## API 엔드포인트

| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/dday/getCategoryList` | 카테고리 목록 조회 |
| GET | `/api/dday/getDdayList` | D-Day 목록 조회 (페이징) |
| GET | `/api/dday/getDdayDetail` | D-Day 상세 조회 |
| POST | `/api/dday/insertDday` | D-Day 등록 |
| POST | `/api/dday/updateDday` | D-Day 수정 |
| POST | `/api/dday/deleteDday` | D-Day 삭제 |

## 관련 테이블

| 테이블명 | 설명 |
|---------|------|
| TB_DDAY | D-Day 마스터 테이블 |
| TB_CATEGORY_INFO | 카테고리 정보 테이블 |

### TB_DDAY 테이블 구조

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| DDAY_IDX | INT | D-Day 인덱스 (PK) |
| USER_ID | VARCHAR | 등록 사용자 ID |
| DDAY_TYPE | VARCHAR | D-Day 타입 (P: 개인, G: 전체) |
| DDAY_CATEGORY | VARCHAR | D-Day 카테고리 코드 |
| DDAY_NAME | VARCHAR | D-Day 이름 |
| DDAY_DATE | VARCHAR | D-Day 날짜 (YYYYMMDD) |
| REGDATE | DATETIME | 등록일시 |
| DDAY_LINK | VARCHAR | D-Day 링크 URL |
| DDAY_ACTIVE | VARCHAR | 활성화 여부 (Y/N) |

## 검색 조건

| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchDdayName | String | D-Day 이름 검색 |
| searchCategory | String | 카테고리 코드 검색 |
| menuType | String | 메뉴 타입 (O: 온라인, F: 오프라인) |

## 사용 예시

### D-Day 목록 조회
```
GET /api/dday/getDdayList?pageIndex=1&searchCategory=GOSI&searchDdayName=시험
```

### D-Day 등록
```
POST /api/dday/insertDday
Content-Type: application/x-www-form-urlencoded

userId=admin&ddayType=G&ddayCategory=GOSI&ddayName=2024년 국가직 공무원 시험&ddayDate=20240323&ddayActive=Y
```

## 관련 Mapper

- **DdayMapper.java**: `com.academy.mapper.DdayMapper`
- **dday.xml**: `src/main/resources/mapper/dday.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
