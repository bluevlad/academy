# 무료 수강신청 관리 패키지

무료 수강신청 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
freeOrder/
├── FreeOrderApi.java               # REST Controller
├── README.md
└── service/
    ├── FreeOrderService.java       # 서비스 클래스
    └── FreeOrderVO.java            # Value Object
```

## 기능 설명

관리자가 회원에게 무료 수강신청을 등록하거나 수강변경을 처리하는 기능을 제공합니다.

### 주요 기능

#### 수강신청 관리
- 수강신청 가능 회원 목록 조회
- 강의 마스터 정보 조회
- 수강신청 등록 (단과/종합반/연회원패키지)
- 다중 수강신청 등록

#### 강의선택 팝업
- 카테고리 목록 조회
- 학습형태 목록 조회
- 과목 목록 조회
- 강의 목록 조회 (검색 조건 지원)

#### 수강변경 관리
- 수강변경 목록 조회
- 수강변경 상세 조회
- 수강변경 처리 (취소 + 신규등록)

## API 엔드포인트

### 수강신청
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/freeOrder/getMemberFreeOrderList` | 수강신청 회원 목록 조회 |
| GET | `/api/freeOrder/getLectureMstInfo` | 강의 마스터 정보 조회 |
| POST | `/api/freeOrder/insertFreeOrder` | 수강신청 등록 |
| POST | `/api/freeOrder/insertFreeOrderMultiple` | 다중 수강신청 등록 |

### 강의선택 팝업
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/freeOrder/getCategoryList` | 카테고리 목록 조회 |
| GET | `/api/freeOrder/getLearningFormList` | 학습형태 목록 조회 |
| GET | `/api/freeOrder/getSubjectList` | 과목 목록 조회 |
| GET | `/api/freeOrder/getLectureListForFreeOrder` | 강의 목록 조회 |

### 수강변경
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/freeOrder/getChangeLectureList` | 수강변경 목록 조회 |
| GET | `/api/freeOrder/getChangeViewDetail` | 수강변경 상세 조회 |
| POST | `/api/freeOrder/updateChangeLecture` | 수강변경 처리 |

## 관련 테이블

### 주문 관련
| 테이블명 | 설명 |
|---------|------|
| TB_ORDERS | 주문 마스터 테이블 |
| TB_ORDER_MGNT_NO | 주문 관리번호 테이블 |
| TB_APPROVALS | 결제승인 테이블 |
| TB_MYLECTURE | 수강신청 테이블 |
| TB_ORDER_YEARPACKAGE | 연회원패키지 주문 테이블 |
| TB_RENEW_HISTORY | 수강변경 이력 테이블 |
| TB_DELIVERS | 배송정보 테이블 |

### 강의 관련
| 테이블명 | 설명 |
|---------|------|
| TB_LEC_MST | 강의 마스터 테이블 |
| TB_LEC_JONG | 종합반 강좌 목록 테이블 |
| TB_LEC_BRIDGE | 강의 브릿지 테이블 |
| TB_CATEGORY_INFO | 카테고리 정보 테이블 |
| TB_LEARNING_FORM_INFO | 학습형태 정보 테이블 |
| TB_SUBJECT_INFO | 과목 정보 테이블 |
| TB_MA_MEMBER | 회원 테이블 |
| TB_CA_BOOK | 도서 테이블 |

### TB_ORDER_MGNT_NO 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| ORDERNO | VARCHAR | 주문번호 |
| MGNTNO | VARCHAR | 관리번호 (강의코드) |
| CNT | INT | 수량 |
| ISCANCEL | VARCHAR | 취소여부 |
| PRICE | INT | 가격 |
| STATUSCODE | VARCHAR | 상태코드 |
| CONFIRMDATE | DATETIME | 확인일시 |
| SDATE | VARCHAR | 시작일 |
| WMV_PMP | VARCHAR | WMV/PMP 구분 |
| OPEN_ADMIN_ID | VARCHAR | 등록 관리자 ID |
| PTYPE | VARCHAR | 상품타입 (D:단과, J:종합반 등) |
| MEMO | TEXT | 메모 |

### TB_MYLECTURE 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| ORDERNO | VARCHAR | 주문번호 |
| USERID | VARCHAR | 사용자 ID |
| PACKAGE_NO | VARCHAR | 패키지번호 |
| LECTURE_NO | VARCHAR | 강의번호 |
| START_DATE | DATE | 시작일 |
| END_DATE | DATE | 종료일 |
| PERIOD | INT | 수강기간 |
| STUDY_PERCENT | INT | 학습진도율 |
| PLAYYN | VARCHAR | 재생여부 |
| REG_DT | DATETIME | 등록일시 |

## 강의 타입 구분

| 코드 | 설명 |
|------|------|
| D | 단과 강의 |
| J | 종합반 |
| N | N종합반 |
| P | P패키지 |
| Y | 연회원패키지 |

## 주문 상태 코드

| 코드 | 설명 |
|------|------|
| DLV100 | 입금확인중 |
| DLV105 | 입금완료 |
| DLV110 | 배송준비중 |
| DLV120 | 배송중 |
| DLV130 | 배송완료 |
| DLV140 | 취소요청 |
| DLV150 | 취소완료 |
| DLV160 | 교환요청 |
| DLV170 | 교환배송중 |
| DLV180 | 교환완료 |
| DLV220 | 환불요청 |
| DLV230 | 환불완료 |
| DLV240 | 결제에러 |

## 검색 조건

### 회원 목록
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| keyword | String | 회원명/아이디 검색 |

### 강의 목록
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| sCatCd | String | 카테고리 코드 |
| sMenuId | String | 학습형태 코드 |
| sSjtCd | String | 과목 코드 |
| searchType | String | 검색 타입 (1:강사명, 2:강의코드, 3:강의제목) |
| searchKeyword | String | 검색어 |

### 수강변경 목록
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| keyword | String | 회원명/아이디 검색 |

## 사용 예시

### 수강신청 회원 목록 조회
```
GET /api/freeOrder/getMemberFreeOrderList?pageIndex=1&keyword=홍길동
```

### 강의 목록 조회
```
GET /api/freeOrder/getLectureListForFreeOrder?pageIndex=1&sCatCd=GOSI&searchType=3&searchKeyword=행정법
```

### 수강신청 등록
```
POST /api/freeOrder/insertFreeOrder
Content-Type: application/x-www-form-urlencoded

userid2=user001,user002&leccode=D202400001&startDate=20240115&subjectPeriod=30&statuscode=DLV105&subjectPrice=0
```

### 다중 수강신청 등록
```
POST /api/freeOrder/insertFreeOrderMultiple
Content-Type: application/x-www-form-urlencoded

userid2=user001&leccode=D202400001/D202400002/D202400003/&startDate=20240115&subjectPeriod=30&statuscode=DLV105
```

### 수강변경 처리
```
POST /api/freeOrder/updateChangeLecture
Content-Type: application/x-www-form-urlencoded

orderno=M20240115000001&userid=user001&leccode2=D202400001&leccode=D202400002&startDate=20240115&subjectPeriod=30&statuscode=DLV105
```

## 관련 Mapper

- **FreeOrderMapper.java**: `com.academy.mapper.FreeOrderMapper`
- **freeOrder.xml**: `src/main/resources/mapper/freeOrder.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
