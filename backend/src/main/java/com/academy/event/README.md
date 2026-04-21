# 이벤트 관리 패키지

이벤트 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
event/
├── EventApi.java                   # REST Controller
├── README.md
└── service/
    ├── EventService.java           # 서비스 클래스
    ├── EventVO.java                # 이벤트 정보 VO (TB_EVENT_INFO)
    ├── EventOptionVO.java          # 이벤트 옵션 VO (TB_EVENT_OPTION1~4)
    ├── EventFileVO.java            # 이벤트 파일 VO (TB_EVENT_FILE)
    ├── EventResultVO.java          # 이벤트 참여자 VO (TB_EVENT_RESULT)
    └── LecEventVO.java             # 강의 이벤트 VO (TB_LECTURE_EVENT_INFO)
```

## 기능 설명

온라인/오프라인 이벤트 및 강의 이벤트(코너)를 관리하는 기능을 제공합니다.

### 주요 기능

#### 이벤트 관리
- 이벤트 목록/상세 조회
- 이벤트 등록/수정/삭제
- 이벤트 댓글(옵션2) 관리
- 이벤트 참여자 조회
- 이벤트 파일 첨부

#### 강의 이벤트 관리
- 강의 이벤트 목록/상세 조회
- 강의 이벤트 등록/수정/삭제
- 강의 이벤트 강좌 연결

## API 엔드포인트

### 이벤트 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/event/getEventList` | 이벤트 목록 조회 |
| GET | `/api/event/getEventDetail` | 이벤트 상세 조회 |
| POST | `/api/event/insertEvent` | 이벤트 등록 |
| POST | `/api/event/updateEvent` | 이벤트 수정 |
| POST | `/api/event/deleteEvent` | 이벤트 삭제 |

### 이벤트 댓글
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/event/getEventCommentList` | 이벤트 댓글 목록 조회 |
| POST | `/api/event/insertEventComment` | 이벤트 댓글 등록 |
| POST | `/api/event/deleteEventComment` | 이벤트 댓글 삭제 |

### 이벤트 참여자
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/event/getEventResultList` | 이벤트 참여자 목록 조회 |

### 강의 이벤트
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/event/getLecEventList` | 강의 이벤트 목록 조회 |
| GET | `/api/event/getLecEventDetail` | 강의 이벤트 상세 조회 |
| POST | `/api/event/insertLecEvent` | 강의 이벤트 등록 |
| POST | `/api/event/updateLecEvent` | 강의 이벤트 수정 |
| POST | `/api/event/deleteLecEvent` | 강의 이벤트 삭제 |
| POST | `/api/event/insertLecEventLecture` | 강의 이벤트 강좌 등록 |
| POST | `/api/event/deleteLecEventLecture` | 강의 이벤트 강좌 삭제 |
| GET | `/api/event/getLectureListForEvent` | 이벤트용 강좌 목록 조회 |

## 관련 테이블

### 이벤트 관련
| 테이블명 | 설명 |
|---------|------|
| TB_EVENT_INFO | 이벤트 마스터 테이블 |
| TB_EVENT_FILE | 이벤트 첨부파일 테이블 |
| TB_EVENT_OPTION1 | 이벤트 옵션1 (신청구분) 테이블 |
| TB_EVENT_OPTION2 | 이벤트 옵션2 (댓글) 테이블 |
| TB_EVENT_OPTION3 | 이벤트 옵션3 (SMS) 테이블 |
| TB_EVENT_OPTION4 | 이벤트 옵션4 (팝업) 테이블 |
| TB_EVENT_RESULT | 이벤트 참여자 테이블 |

### 강의 이벤트 관련
| 테이블명 | 설명 |
|---------|------|
| TB_LECTURE_EVENT_INFO | 강의 이벤트 마스터 테이블 |
| TB_LECTURE_EVENT_LIST | 강의 이벤트 강좌 목록 테이블 |

### TB_EVENT_INFO 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| EVENT_NO | VARCHAR | 이벤트 번호 (PK) |
| ONOFF_DIV | VARCHAR | 온/오프라인 구분 (O/F/L) |
| CATEGORY_CODE | VARCHAR | 카테고리 코드 |
| NOTICE_GUBUN | VARCHAR | 공지 구분 |
| OPEN_YN | VARCHAR | 공개 여부 |
| MEMBER_GUBUN | VARCHAR | 회원 구분 |
| START_DATE | VARCHAR | 시작일 |
| END_DATE | VARCHAR | 종료일 |
| TITLE | VARCHAR | 제목 |
| CONTENTS_TEXT | TEXT | 내용 텍스트 |
| OPTION1_YN | VARCHAR | 옵션1 사용여부 |
| OPTION2_YN | VARCHAR | 옵션2 사용여부 |
| OPTION3_YN | VARCHAR | 옵션3 사용여부 |
| OPTION4_YN | VARCHAR | 옵션4 사용여부 |

## 이벤트 상태 분류

| 상태 | 조건 |
|------|------|
| 대기중 | START_DATE > 현재일자 |
| 진행중 | START_DATE <= 현재일자 AND END_DATE >= 현재일자 |
| 완료 | END_DATE < 현재일자 |

## 검색 조건

| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchCategory | String | 카테고리 코드 검색 |
| searchType | String | 검색 유형 (TITLE, CONTENTS_TEXT) |
| searchKeyword | String | 검색어 |
| menuType | String | 메뉴 타입 (OM_ROOT, FM_ROOT) |
| onoffDiv | String | 온/오프라인 구분 |

## 사용 예시

### 이벤트 목록 조회
```
GET /api/event/getEventList?pageIndex=1&menuType=OM_ROOT&searchCategory=GOSI
```

### 이벤트 등록
```
POST /api/event/insertEvent
Content-Type: application/x-www-form-urlencoded

onoffDiv=O&categoryCode=GOSI&title=2024년 합격자 이벤트&startDate=20240101&endDate=20240131&openYn=Y
```

### 강의 이벤트 강좌 등록
```
POST /api/event/insertLecEventLecture
Content-Type: application/x-www-form-urlencoded

eventNo=1&leccode=D202400001
```

## 관련 Mapper

- **EventMapper.java**: `com.academy.mapper.EventMapper`
- **event.xml**: `src/main/resources/mapper/event.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
