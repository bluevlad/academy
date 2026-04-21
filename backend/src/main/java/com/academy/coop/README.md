# 제휴사 관리 패키지

제휴사 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
coop/
├── CoopApi.java                    # REST Controller
├── README.md
└── service/
    ├── CoopService.java            # 서비스 클래스
    ├── CoopVO.java                 # 제휴사 마스터 VO (COOP_MST)
    ├── CoopIpVO.java               # 제휴사 IP VO (COOP_USE_IP)
    ├── CoopBoardVO.java            # 제휴사 게시판 VO (TB_BOARD_MEMBERSHIP)
    └── CoopOrderVO.java            # 제휴사 주문 VO
```

## 기능 설명

제휴사 및 관련 정보(IP, 게시판, 주문)를 관리하는 기능을 제공합니다.

### 주요 기능

#### 제휴사 마스터 관리
- 제휴사 목록/상세 조회
- 제휴사 등록/수정/삭제
- 할인율 관리

#### 제휴사 IP 관리
- 제휴사 IP 목록 조회
- 제휴사 IP 등록/삭제

#### 제휴사 게시판 관리
- 게시판 목록/상세 조회
- 게시글 등록/수정/삭제
- 게시글 정렬순서 관리
- 첨부파일 관리

#### 제휴사 주문 관리
- 제휴사 주문 목록 조회
- 결제 상세 조회

## API 엔드포인트

### 제휴사 마스터
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/coop/getCoopList` | 제휴사 목록 조회 |
| GET | `/api/coop/getCoopDetail` | 제휴사 상세 조회 |
| POST | `/api/coop/insertCoop` | 제휴사 등록 |
| POST | `/api/coop/updateCoop` | 제휴사 수정 |
| POST | `/api/coop/deleteCoop` | 제휴사 삭제 |

### 제휴사 IP
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/coop/getCoopIpList` | 제휴사 IP 목록 조회 |
| POST | `/api/coop/insertCoopIp` | 제휴사 IP 등록 |
| POST | `/api/coop/deleteCoopIp` | 제휴사 IP 삭제 |

### 제휴사 게시판
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/coop/getCoopBoardList` | 제휴사 게시판 목록 조회 |
| GET | `/api/coop/getCoopBoardDetail` | 제휴사 게시판 상세 조회 |
| POST | `/api/coop/insertCoopBoard` | 제휴사 게시글 등록 |
| POST | `/api/coop/updateCoopBoard` | 제휴사 게시글 수정 |
| POST | `/api/coop/deleteCoopBoard` | 제휴사 게시글 삭제 |
| POST | `/api/coop/updateCoopBoardRank` | 제휴사 게시글 정렬순서 수정 |
| POST | `/api/coop/deleteCoopBoardFile` | 제휴사 게시글 파일 삭제 |

### 제휴사 주문
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/coop/getCoopOrderList` | 제휴사 주문 목록 조회 |
| GET | `/api/coop/getCoopPayDetailList` | 제휴사 결제 상세 조회 |

## 관련 테이블

### 제휴사 관련
| 테이블명 | 설명 |
|---------|------|
| COOP_MST | 제휴사 마스터 테이블 |
| COOP_USE_IP | 제휴사 IP 테이블 |
| TB_BOARD_MEMBERSHIP | 제휴사 게시판 테이블 |
| TB_CMM_CODE | 공통 코드 테이블 (지역, 카테고리 코드) |

### COOP_MST 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| COOP_CD | VARCHAR | 제휴사 코드 (PK) |
| COOP_NM | VARCHAR | 제휴사명 |
| DISCOUNT_PER | INT | 할인율 (%) |
| COOP_DESC | TEXT | 제휴사 설명 |

### COOP_USE_IP 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| COOP_CD | VARCHAR | 제휴사 코드 (FK) |
| USER_IP | VARCHAR | 허용 IP 주소 |

### TB_BOARD_MEMBERSHIP 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| COOP_BOARD_SEQ | INT | 게시판 순번 (PK, AUTO_INCREMENT) |
| OPEN_YN | VARCHAR | 공개 여부 (Y/N) |
| CREATE_NAME | VARCHAR | 작성자명 |
| SUBJECT | VARCHAR | 제목 |
| CONTENT | TEXT | 내용 |
| FILE_PATH | VARCHAR | 파일 경로 |
| FILE_NAME | VARCHAR | 파일명 |
| THUMBNAIL_FILE_PATH | VARCHAR | 썸네일 파일 경로 |
| COOP_TYPE | VARCHAR | 제휴 타입 |
| COOP_AREA | VARCHAR | 제휴 지역 코드 |
| COOP_CATE | VARCHAR | 제휴 카테고리 코드 |
| NOTICE_TOP_YN | VARCHAR | 상단공지 여부 (Y/N) |
| HITS | INT | 조회수 |
| COOP_RANK | INT | 정렬순서 |

## 검색 조건

### 제휴사 마스터
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchKeyword | String | 제휴사명 검색 |

### 제휴사 게시판
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| searchCoopArea | String | 제휴 지역 코드 검색 |
| searchCoopCate | String | 제휴 카테고리 코드 검색 |
| searchKeyword | String | 제목/내용 검색 |

### 제휴사 주문
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| coopCd | String | 제휴사 코드 |
| searchStartDate | String | 검색 시작일 (YYYYMMDD) |
| searchEndDate | String | 검색 종료일 (YYYYMMDD) |
| searchDateType | String | 검색일자 타입 |
| searchKey | String | 주문자 정보 검색 |

## 사용 예시

### 제휴사 목록 조회
```
GET /api/coop/getCoopList?pageIndex=1&searchKeyword=병원
```

### 제휴사 등록
```
POST /api/coop/insertCoop
Content-Type: application/x-www-form-urlencoded

coopCd=COOP001&coopNm=협력병원&discountPer=10&coopDesc=협력병원 제휴
```

### 제휴사 IP 등록
```
POST /api/coop/insertCoopIp
Content-Type: application/x-www-form-urlencoded

coopCd=COOP001&userIp=192.168.1.100
```

### 제휴사 게시판 목록 조회
```
GET /api/coop/getCoopBoardList?pageIndex=1&searchCoopArea=SEOUL&searchCoopCate=HSPT
```

### 제휴사 주문 목록 조회
```
GET /api/coop/getCoopOrderList?pageIndex=1&coopCd=COOP001&searchStartDate=20240101&searchEndDate=20241231
```

## 관련 Mapper

- **CoopMapper.java**: `com.academy.mapper.CoopMapper`
- **coop.xml**: `src/main/resources/mapper/coop.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
