# 상담 관리 패키지

상담 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
counsel/
├── CounselApi.java                 # REST Controller
├── README.md
└── service/
    ├── CounselService.java         # 서비스 클래스
    ├── CounselScheduleVO.java      # 상담 일정 VO (COUNSEL_SCH)
    ├── CounselRequestVO.java       # 상담 신청 VO (COUNSEL_RST)
    └── PresentRequestVO.java       # 설명회 신청 VO (TB_PRESENT)
```

## 기능 설명

상담 일정 관리, 상담 신청 관리, 설명회 신청 관리 기능을 제공합니다.

### 주요 기능

#### 상담 일정 관리
- 상담 일정 일자/상세 목록 조회
- 상담 일정 테이블 조회
- 상담 시간 테이블 조회
- 상담 일정 등록/수정/삭제
- 일괄 등록/삭제 지원

#### 상담 신청 관리
- 상담 신청 목록 조회
- 상담 예약 현황 조회
- 개인 상담 신청 내역 조회

#### 설명회 신청 관리
- 설명회 신청 목록 조회
- 설명회 코드 목록 조회

## API 엔드포인트

### 상담 일정
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/counsel/getScheduleDayList` | 상담 일정 일자 목록 조회 |
| GET | `/api/counsel/getScheduleList` | 상담 일정 상세 목록 조회 |
| GET | `/api/counsel/getScheduleTable` | 상담 일정 테이블 조회 |
| GET | `/api/counsel/getTimeTable` | 상담 시간 테이블 조회 |
| POST | `/api/counsel/insertSchedule` | 상담 일정 등록 |
| POST | `/api/counsel/updateSchedule` | 상담 일정 수정 |
| POST | `/api/counsel/deleteSchedule` | 상담 일정 삭제 |

### 상담 신청
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/counsel/getCounselRequestList` | 상담 신청 목록 조회 |
| GET | `/api/counsel/getCounselReqList` | 상담 예약 현황 조회 |
| GET | `/api/counsel/getCounselUserReq` | 개인 상담 신청 내역 조회 |

### 설명회 신청
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/counsel/getPresentReqList` | 설명회 신청 목록 조회 |

## 관련 테이블

### 상담 관련
| 테이블명 | 설명 |
|---------|------|
| COUNSEL_SCH | 상담 일정 테이블 |
| COUNSEL_RST | 상담 신청 테이블 |
| COUNSEL_TS | 상담 시간 설정 테이블 |
| TB_PRESENT | 설명회 신청 테이블 |

### COUNSEL_SCH 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| SCH_DAY | VARCHAR | 상담일 (YYYY-MM-DD) |
| TS_IDX | INT | 시간 인덱스 |
| MAX_USR | INT | 최대 인원 |
| REQ_CNT | INT | 예약 인원 |
| IS_USE | VARCHAR | 사용 여부 (Y/N) |
| REQ_TYPE | VARCHAR | 예약 타입 |
| CAT_CD | VARCHAR | 카테고리 코드 |

### COUNSEL_RST 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| SCH_DAY | VARCHAR | 상담일 |
| TS_IDX | INT | 시간 인덱스 |
| USER_ID | VARCHAR | 사용자 ID |
| USER_NM | VARCHAR | 사용자명 |
| USER_CATEGORY | VARCHAR | 사용자 카테고리 |
| USER_PERIOD | VARCHAR | 사용자 기간 (A/B/C) |
| USER_LEC | VARCHAR | 사용자 강의 |
| USER_BIRTHDAY | VARCHAR | 생년월일 |
| USER_PHONE | VARCHAR | 전화번호 |
| USER_EMAIL | VARCHAR | 이메일 |
| USER_COMMENTS | TEXT | 코멘트 |
| CANCEL_DATE | VARCHAR | 취소일 |
| RESERVE | VARCHAR | 예약 여부 |
| CAT_CD | VARCHAR | 카테고리 코드 |

### TB_PRESENT 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| USER_ID | VARCHAR | 사용자 ID |
| UNIV_CD | VARCHAR | 대학 코드 |
| USER_NM | VARCHAR | 사용자명 |
| PHONE_NO | VARCHAR | 전화번호 |
| EMAIL | VARCHAR | 이메일 |
| COMENTS | TEXT | 코멘트 |

## 사용자 기간 구분

| 코드 | 설명 |
|------|------|
| A | 6개월 미만 |
| B | 1년 미만 |
| C | 1년 이상 |

## 검색 조건

### 상담 일정
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchCategory | String | 카테고리 코드 (콤마 구분) |
| sDate | String | 검색 시작일 (YYYYMMDD) |
| eDate | String | 검색 종료일 (YYYYMMDD) |

### 상담 신청
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchCategoryS | String | 카테고리 코드 (콤마 구분) |
| schDay | String | 상담일 |
| userId | String | 사용자 ID |

### 설명회 신청
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchCode | String | 대학 코드 |

## 사용 예시

### 상담 일정 일자 목록 조회
```
GET /api/counsel/getScheduleDayList?searchCategory=GOSI,POLICE&sDate=20240101&eDate=20241231
```

### 상담 일정 등록
```
POST /api/counsel/insertSchedule
Content-Type: application/x-www-form-urlencoded

catCds=GOSI,POLICE&sDate=20240115&dateCount=7&timeCount=3&maxUsr=10&reqCnt=5
```

### 상담 일정 삭제 (일괄)
```
POST /api/counsel/deleteSchedule
Content-Type: application/x-www-form-urlencoded

delArr=2024-01-15#GOSI&delArr=2024-01-16#GOSI
```

### 상담 신청 목록 조회
```
GET /api/counsel/getCounselRequestList?pageIndex=1&searchCategoryS=GOSI
```

### 상담 예약 현황 조회
```
GET /api/counsel/getCounselReqList?schDay=2024-01-15&catCd=GOSI
```

### 설명회 신청 목록 조회
```
GET /api/counsel/getPresentReqList?pageIndex=1&searchCode=UNIV001
```

## 관련 Mapper

- **CounselMapper.java**: `com.academy.mapper.CounselMapper`
- **counsel.xml**: `src/main/resources/mapper/counsel.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
