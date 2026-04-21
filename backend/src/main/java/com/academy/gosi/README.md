# 고시 관리 패키지

고시 관리 및 공지/게시판 기능을 제공하는 패키지입니다.

## 패키지 구조

```
gosi/
├── GosiApi.java                    # 고시 관리 REST Controller
├── PubApi.java                     # 공지/게시판 REST Controller
├── README.md
└── service/
    ├── GosiService.java            # 고시 관리 서비스 클래스
    ├── GosiVO.java                 # 고시 관리 VO
    ├── PubService.java             # 공지/게시판 서비스 클래스
    └── PubVO.java                  # 공지/게시판 VO
```

## 기능 설명

### Gosi (고시 관리)

국가고시 관련 정보를 관리하는 기능을 제공합니다.

#### 주요 기능

##### 고시 마스터 관리
- 고시 목록 조회

##### 샘플 사용자 관리
- 샘플 사용자 목록/상세 조회
- 샘플 사용자 등록/수정/삭제

##### 고시 지역 마스터 관리
- 고시 지역 목록/상세 조회
- 고시 지역 정보 수정

##### VOD 관리
- VOD 과목 목록 조회
- VOD 상세 조회/수정

##### 고시 통계 관리
- 고시 통계 목록 조회
- 고시 결과/표준/통계/조정 마스터 생성 (저장 프로시저 호출)

##### 이벤트 결과 관리
- 이벤트 결과 목록 조회

### Pub (공지/게시판 관리)

공지사항 및 게시판 관련 기능을 제공합니다.

#### 주요 기능

##### 공지사항 관리
- 공지사항 목록/상세 조회
- 공지사항 등록/수정/삭제
- 조회수 증가

##### 첨부파일 관리
- 첨부파일 목록 조회
- 첨부파일 등록/삭제

## API 엔드포인트

### 고시 마스터
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getGosiList` | 고시 목록 조회 |

### 샘플 사용자
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getSampleUserList` | 샘플 사용자 목록 조회 |
| GET | `/api/gosi/getSampleUserDetail` | 샘플 사용자 상세 조회 |
| POST | `/api/gosi/insertSampleUser` | 샘플 사용자 등록 |
| POST | `/api/gosi/updateSampleUser` | 샘플 사용자 수정 |
| POST | `/api/gosi/deleteSampleUser` | 샘플 사용자 삭제 |

### 고시 지역 마스터
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getGosiAreaMstList` | 고시 지역 목록 조회 |
| GET | `/api/gosi/getGosiAreaMstDetail` | 고시 지역 상세 조회 |
| POST | `/api/gosi/updateGosiAreaMst` | 고시 지역 수정 |

### VOD
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getVodSubjectList` | VOD 과목 목록 조회 |
| GET | `/api/gosi/getVodDetail` | VOD 상세 조회 |
| POST | `/api/gosi/updateGosiVod` | VOD 수정 |

### 고시 통계
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getGosiStatMstList` | 고시 통계 목록 조회 |
| POST | `/api/gosi/makeGosiResult` | 고시 결과 생성 |
| POST | `/api/gosi/makeGosiStandard` | 고시 표준 생성 |
| POST | `/api/gosi/makeGosiStatMst` | 고시 통계 마스터 생성 |
| POST | `/api/gosi/makeGosiAdjustMst` | 고시 조정 마스터 생성 |

### 고시 과목
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getGosiSubjectList` | 고시 과목 목록 조회 |

### 이벤트 결과
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/gosi/getEventResultList` | 이벤트 결과 목록 조회 |

### 공지사항
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/pub/getPubList` | 공지사항 목록 조회 |
| GET | `/api/pub/getPubDetail` | 공지사항 상세 조회 |
| POST | `/api/pub/insertPub` | 공지사항 등록 |
| POST | `/api/pub/updatePub` | 공지사항 수정 |
| POST | `/api/pub/deletePub` | 공지사항 삭제 |

### 첨부파일
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/pub/getPubFileList` | 첨부파일 목록 조회 |
| POST | `/api/pub/insertPubFile` | 첨부파일 등록 |
| POST | `/api/pub/deletePubFile` | 첨부파일 삭제 |

## 관련 테이블

### 고시 관련
| 테이블명 | 설명 |
|---------|------|
| GOSI_MST | 고시 마스터 테이블 |
| GOSI_RST_SMP | 샘플 사용자 테이블 |
| GOSI_AREA_MST | 고시 지역 마스터 테이블 |
| GOSI_VOD | VOD 테이블 |
| GOSI_STAT_MST | 고시 통계 마스터 테이블 |
| GOSI_SBJ_MST | 고시 과목 마스터 테이블 |
| TB_EVENT_RESULT | 이벤트 결과 테이블 |

### 공지/게시판 관련
| 테이블명 | 설명 |
|---------|------|
| TB_PUB | 공지사항 테이블 |
| TB_PUB_FILE | 공지사항 첨부파일 테이블 |

### GOSI_RST_SMP 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| RST_NO | INT | 결과 번호 (PK, AUTO_INCREMENT) |
| GOSI_CD | VARCHAR | 고시 코드 |
| USER_NM | VARCHAR | 사용자명 |
| USER_AGE | VARCHAR | 나이 |
| STUDY_WAIT | VARCHAR | 학습 대기기간 |
| STUDY_TYPE | VARCHAR | 학습 유형 |
| ADD_POINT | VARCHAR | 추가 점수 |
| EXAM_STAT | VARCHAR | 시험 상태 |
| AREA_01 | VARCHAR | 지역1 |
| AREA_02 | VARCHAR | 지역2 |
| SEL_SBJ_01 | VARCHAR | 선택과목1 |
| SEL_SBJ_02 | VARCHAR | 선택과목2 |
| SBJ_01~05 | VARCHAR | 과목1~5 점수 |
| SBJ_MO_01~05 | VARCHAR | 과목1~5 모의점수 |
| USER_SEX | VARCHAR | 성별 |
| ISUSE | VARCHAR | 사용여부 (Y/N) |
| INPUT_DATE | DATETIME | 입력일 |
| UPDATE_DATE | DATETIME | 수정일 |

### TB_PUB 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| PUB_NO | INT | 게시글 번호 (PK, AUTO_INCREMENT) |
| PUB_TYPE | VARCHAR | 게시판 타입 |
| CAT_CD | VARCHAR | 카테고리 코드 |
| TITLE | VARCHAR | 제목 |
| CONTENT | TEXT | 내용 |
| WRITE_ID | VARCHAR | 작성자 ID |
| WRITE_NM | VARCHAR | 작성자명 |
| HIT_CNT | INT | 조회수 |
| NOTICE_YN | VARCHAR | 공지여부 (Y/N) |
| USE_YN | VARCHAR | 사용여부 (Y/N) |
| REG_DATE | DATETIME | 등록일 |
| MOD_DATE | DATETIME | 수정일 |

## 검색 조건

### 샘플 사용자
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| gosiCd | String | 고시 코드 |
| searchUserNm | String | 사용자명 검색 |
| isuse | String | 사용여부 (Y/N) |

### 이벤트 결과
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchEventCd | String | 이벤트 코드 |
| searchStartDate | String | 검색 시작일 (YYYYMMDD) |
| searchEndDate | String | 검색 종료일 (YYYYMMDD) |
| searchKeyword | String | 키워드 검색 (사용자명/ID) |

### 공지사항
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchPubType | String | 게시판 타입 |
| searchCatCd | String | 카테고리 코드 |
| searchKeyword | String | 키워드 검색 (제목/내용) |
| searchTitle | String | 제목 검색 |
| searchWriteNm | String | 작성자명 검색 |
| searchStartDate | String | 검색 시작일 (YYYYMMDD) |
| searchEndDate | String | 검색 종료일 (YYYYMMDD) |
| searchNoticeYn | String | 공지여부 (Y/N) |
| searchUseYn | String | 사용여부 (Y/N) |

## 사용 예시

### 샘플 사용자 목록 조회
```
GET /api/gosi/getSampleUserList?pageIndex=1&gosiCd=GOSI001&searchUserNm=홍길동
```

### 샘플 사용자 등록
```
POST /api/gosi/insertSampleUser
Content-Type: application/x-www-form-urlencoded

gosiCd=GOSI001&userNm=홍길동&userAge=25&studyWait=6개월&studyType=독학&area01=서울&area02=경기
```

### 고시 결과 생성
```
POST /api/gosi/makeGosiResult
Content-Type: application/x-www-form-urlencoded

gosiCd=GOSI001
```

### 공지사항 목록 조회
```
GET /api/pub/getPubList?pageIndex=1&searchPubType=NOTICE&searchKeyword=공지
```

### 공지사항 등록
```
POST /api/pub/insertPub
Content-Type: application/x-www-form-urlencoded

pubType=NOTICE&catCd=GENERAL&title=공지사항 제목&content=공지사항 내용&writeId=admin&writeNm=관리자&noticeYn=Y
```

## 저장 프로시저

고시 통계 생성 시 아래 저장 프로시저를 호출합니다:

| 프로시저명 | 설명 |
|-----------|------|
| SP_MAKE_GOSI_RESULT | 고시 결과 생성 |
| SP_MAKE_GOSI_STANDARD | 고시 표준 생성 |
| SP_MAKE_GOSI_STAT_MST | 고시 통계 마스터 생성 |
| SP_MAKE_GOSI_ADJUST_MST | 고시 조정 마스터 생성 |

## 관련 Mapper

- **GosiMapper.java**: `com.academy.mapper.GosiMapper`
- **gosi.xml**: `src/main/resources/mapper/gosi.xml`
- **PubMapper.java**: `com.academy.mapper.PubMapper`
- **pub.xml**: `src/main/resources/mapper/pub.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
