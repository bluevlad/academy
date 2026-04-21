# 오프라인 강의 관리 패키지

오프라인 학원 강의(단과/종합반)를 관리하는 패키지입니다.

## 패키지 구조

```
lectureOff/
├── LectureOffApi.java              # REST Controller
├── README.md
└── service/
    ├── LectureOffService.java      # 서비스 클래스
    └── LectureOffVO.java           # VO
```

## 기능 설명

오프라인 학원에서 운영하는 단과 강의 및 종합반 강의의 등록, 수정, 삭제, 조회 기능을 제공합니다.

### 주요 기능

#### 단과 강의 관리
- 단과 강의 목록/상세 조회
- 단과 강의 등록/수정/삭제
- 강의 브릿지 관리 (연관 강의)
- 강의 교재 관리
- 강의 일정 관리

#### 종합반 강의 관리
- 종합반 강의 목록/상세 조회
- 종합반 강의 등록/수정/삭제
- 종합반 강의 구성 관리 (단과 강의 매핑)
- 선택형 종합반 관리

#### 공통 기능
- 카테고리/과목/강사 목록 조회
- 강의코드/종합반 SEQ 자동 생성

## API 엔드포인트

### 단과 강의 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getLectureList` | 단과 강의 목록 조회 |
| GET | `/api/lectureOff/getLectureDetail` | 단과 강의 상세 조회 |
| POST | `/api/lectureOff/insertLecture` | 단과 강의 등록 |
| POST | `/api/lectureOff/updateLecture` | 단과 강의 수정 |
| POST | `/api/lectureOff/deleteLecture` | 단과 강의 삭제 |

### 강의 브릿지 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getLectureBridgeList` | 강의 브릿지 목록 조회 |
| POST | `/api/lectureOff/insertLectureBridge` | 강의 브릿지 등록 |
| POST | `/api/lectureOff/deleteLectureBridge` | 강의 브릿지 삭제 |

### 강의 교재 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getLectureBookList` | 강의 교재 목록 조회 |
| GET | `/api/lectureOff/getBookSearchList` | 교재 검색 목록 조회 |
| POST | `/api/lectureOff/insertLectureBook` | 강의 교재 등록 |
| POST | `/api/lectureOff/deleteLectureBook` | 강의 교재 삭제 |

### 강의 일정 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getLectureDateList` | 강의 일정 목록 조회 |
| POST | `/api/lectureOff/insertLectureDate` | 강의 일정 등록 |
| POST | `/api/lectureOff/deleteLectureDate` | 강의 일정 삭제 |

### 종합반 강의 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getJongLectureList` | 종합반 강의 목록 조회 |
| GET | `/api/lectureOff/getJongLectureDetail` | 종합반 강의 상세 조회 |
| POST | `/api/lectureOff/insertJongLecture` | 종합반 강의 등록 |
| POST | `/api/lectureOff/updateJongLecture` | 종합반 강의 수정 |
| POST | `/api/lectureOff/deleteJongLecture` | 종합반 강의 삭제 |

### 종합반 강의 구성 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getJongLectureDetailList` | 종합반 강의 구성 목록 조회 |
| POST | `/api/lectureOff/insertJongLectureDetail` | 종합반 강의 구성 등록 |
| POST | `/api/lectureOff/deleteJongLectureDetail` | 종합반 강의 구성 삭제 |

### 선택형 종합반 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getChoiceJongList` | 선택형 종합반 목록 조회 |
| POST | `/api/lectureOff/insertChoiceJong` | 선택형 종합반 등록 |
| POST | `/api/lectureOff/deleteChoiceJong` | 선택형 종합반 삭제 |

### 공통 조회
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/lectureOff/getCategoryList` | 카테고리 목록 조회 |
| GET | `/api/lectureOff/getSubjectList` | 과목 목록 조회 |
| GET | `/api/lectureOff/getTeacherList` | 강사 목록 조회 |
| GET | `/api/lectureOff/getNextLeccode` | 다음 강의코드 조회 |
| GET | `/api/lectureOff/getNextJongseq` | 다음 종합반 SEQ 조회 |

## 관련 테이블

| 테이블명 | 설명 |
|---------|------|
| TB_OFF_LEC_MST | 단과 강의 마스터 테이블 |
| TB_OFF_LEC_BRIDGE | 강의 브릿지 테이블 (연관 강의) |
| TB_OFF_PLUS_CA_BOOK | 강의 교재 테이블 |
| TB_OFF_LECTURE_DATE | 강의 일정 테이블 |
| TB_OFF_LEC_JONG_MST | 종합반 마스터 테이블 |
| TB_OFF_LEC_JONG | 종합반 강의 구성 테이블 |
| TB_OFF_CHOICE_JONG_NO | 선택형 종합반 테이블 |

### TB_OFF_LEC_MST 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| SEQ | INT | 시퀀스 (PK, AUTO_INCREMENT) |
| LECCODE | VARCHAR | 강의 코드 (UK) |
| CATEGORY_CD | VARCHAR | 카테고리 코드 |
| LEARNING_CD | VARCHAR | 과목 코드 |
| SUBJECT_TITLE | VARCHAR | 강의 제목 |
| TEACHER_KEY | VARCHAR | 강사 키 |
| LEL_DATE | VARCHAR | 개강일 |
| LEC_GUBUN | VARCHAR | 강의 구분 |
| KIND | VARCHAR | 종류 |
| FORM | VARCHAR | 형태 |
| LEC_NUM | INT | 강의 횟수 |
| PRICE | INT | 정가 |
| SALE_PRICE | INT | 판매가 |
| POINT_USER | INT | 적립 포인트 |
| LIMIT_DAY | INT | 수강 기간 |
| MOBILE | VARCHAR | 모바일 여부 |
| ORG_PRICE | INT | 원가 |
| ISUSE | CHAR(1) | 사용여부 (Y/N) |
| INDATE | DATETIME | 등록일 |
| LEC_INFO | TEXT | 강의 정보 |
| LEC_CONTENT | TEXT | 강의 내용 |
| LEC_TIME | VARCHAR | 강의 시간 |
| PLACE | VARCHAR | 강의 장소 |

### TB_OFF_LEC_JONG_MST 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| JONGSEQ | INT | 종합반 SEQ (PK, AUTO_INCREMENT) |
| MST_LECCODE | VARCHAR | 마스터 강의코드 |
| GUBUN | VARCHAR | 구분 |
| JONG_NM | VARCHAR | 종합반 명 |
| JONG_PRICE | INT | 종합반 정가 |
| JONG_SALE_PRICE | INT | 종합반 판매가 |
| ISUSE | CHAR(1) | 사용여부 (Y/N) |
| INDATE | DATETIME | 등록일 |
| JONG_INFO | TEXT | 종합반 정보 |
| JONG_CONTENT | TEXT | 종합반 내용 |

## 검색 조건

### 단과 강의 목록
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| categoryCd | String | 카테고리 코드 |
| learningCd | String | 과목 코드 |
| teacherKey | String | 강사 키 |
| isuse | String | 사용여부 (Y/N) |
| searchKeyword | String | 강의 제목 검색 |

### 종합반 강의 목록
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| gubun | String | 구분 |
| isuse | String | 사용여부 (Y/N) |
| searchKeyword | String | 종합반 명 검색 |

## 강의코드 형식

강의코드는 자동 생성되며 다음과 같은 형식입니다:
- 형식: `OFF` + `YYYYMMDD` + `0001`
- 예시: `OFF202312120001`

## 사용 예시

### 단과 강의 목록 조회
```
GET /api/lectureOff/getLectureList?categoryCd=CAT001&isuse=Y&pageIndex=1&pageUnit=10
```

### 단과 강의 등록
```
POST /api/lectureOff/insertLecture
Content-Type: application/x-www-form-urlencoded

leccode=OFF202312120001&categoryCd=CAT001&learningCd=LRN001&subjectTitle=테스트 강의&teacherKey=TCH001&price=100000&salePrice=80000&isuse=Y
```

### 종합반 강의 등록
```
POST /api/lectureOff/insertJongLecture
Content-Type: application/x-www-form-urlencoded

gubun=A&jongNm=종합반 테스트&jongPrice=500000&jongSalePrice=400000&isuse=Y
```

### 종합반 강의 구성 등록
```
POST /api/lectureOff/insertJongLectureDetail
Content-Type: application/x-www-form-urlencoded

jongseq=1&mstLeccode=OFF202312120001&sort=1
```

## 관련 Mapper

- **LectureOffMapper.java**: `com.academy.mapper.LectureOffMapper`
- **lectureOff.xml**: `src/main/resources/mapper/lectureOff.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
