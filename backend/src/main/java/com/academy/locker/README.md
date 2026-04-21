# Locker Package

사물함(Locker) 대여 및 관리 패키지입니다.

## 패키지 구조

```
locker/
├── LockerApi.java           # REST API Controller
└── service/
    ├── LockerService.java   # Service class (@Service)
    ├── LockerVO.java        # Locker Value Object
    └── OrdersVO.java        # Orders Value Object
```

## 주요 컴포넌트

### LockerApi

사물함 대여 및 관리를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/locker`

**Endpoints**:

| Method | Endpoint | Description | Transaction |
|--------|----------|-------------|-------------|
| GET | `/getLockerList` | 사물함 목록 조회 (페이징) | - |
| GET | `/getLocker` | 사물함 상세 조회 | - |
| POST | `/insertLockerRent` | 사물함 대여 신청 등록 | @Transactional |
| GET | `/UpdateLockerFlag` | 사물함 상태값 업데이트 | - |
| GET | `/lockerExtendOrder` | 사물함 대여 연장 처리 | @Transactional |
| GET | `/lockerRentOrder` | 사물함 대여 신청/수정 저장 | @Transactional |
| GET | `/lockerChange.pop` | 사물함 변경 팝업 | - |
| GET | `/lockerChangePop` | 사물함 변경 처리 | - |
| GET | `/academy/box/OrderView.do` | 사물함 대여 결제 및 환불 내역 보기 | - |
| GET | `/lockerDeleteOrder` | 사물함 대여 주문정보 삭제 | @Transactional |
| POST | `/lockerRefund` | 사물함 대여 환불 처리 | @Transactional |

### LockerService

사물함 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Key Methods**:

```java
// 조회
ArrayList<JSONObject> selectLockerList(LockerVO lockerVO)
int selectLockerListTotCnt(LockerVO lockerVO)
JSONObject getLocker(LockerVO lockerVO)
ArrayList<JSONObject> selectLockerNumList(LockerVO lockerVO)
JSONObject selectLockerNumRentDetail(LockerVO lockerVO)
JSONObject selectLockerOrderDetail(LockerVO lockerVO)

// 사물함 대여 관리
void insertLockerRent(LockerVO lockerVO)
void updateLockerNum(LockerVO lockerVO)
void updateLockerFlag(LockerVO lockerVO)
int getLockerRentSeq(LockerVO lockerVO)

// 사물함 변경
void updateLockerNumChange(LockerVO lockerVO)
void updateLockerNumReset(LockerVO lockerVO)
void updateLockerRentChange(LockerVO lockerVO)

// 환불 처리
void updateLockerNumRentRefund(LockerVO lockerVO)
void insertOrderRefund(OrdersVO ordersVO)

// 주문 관리
void insertOrders(OrdersVO ordersVO)
void insertOrderPay(OrdersVO ordersVO)
void insertOrderItem(OrdersVO ordersVO)
void getOrderNo(OrdersVO ordersVO)
void deleteLockerOrder(LockerVO lockerVO)
void deleteLockerRentByOrderId(LockerVO lockerVO)
```

## 주요 기능

### 1. 사물함 목록 조회

```bash
GET /api/locker/getLockerList?pageIndex=1&pageUnit=10&pageSize=10
```

**Response**:
```json
{
  "lockers": [
    {
      "boxCd": "A",
      "boxNum": 1,
      "userId": "user001",
      "boxFlag": "Y",
      "rentStart": "2024-01-01",
      "rentEnd": "2024-03-01"
    }
  ],
  "paginationInfo": {
    "currentPageNo": 1,
    "totalRecordCount": 100,
    "recordCountPerPage": 10
  }
}
```

### 2. 사물함 상세 조회

```bash
GET /api/locker/getLocker?boxCd=A&boxNum=1
```

**Response**:
```json
{
  "lockerDetail": {...},
  "lockerNumList": [...]
}
```

### 3. 사물함 온라인 대여 신청

복잡한 트랜잭션 처리를 수행합니다.

```bash
POST /api/locker/insertLockerRent
Content-Type: application/x-www-form-urlencoded

userId=user001&boxCd=A&boxNum=1&boxPrice=50000
```

**처리 프로세스**:

1. **주문 정보 생성**
   - 주문번호 자동 생성
   - 상품구분: 'B' (사물함)
   - 주문자 정보 설정

2. **주문 등록** (`acm_orders`)
   - 주문번호, 사용자 ID, 등록자/수정자 설정

3. **주문 결제 정보 등록** (`acm_order_pay`)
   - 결제금액, 결제구분 (PAY110: 카드)

4. **주문 상세 정보 등록** (`acm_order_item`)
   - 아이템번호: B+사물함코드+사물함번호 (예: BA1)
   - 주문수량: 1
   - 상태코드: 105 (주문완료)
   - 상품구분: B (사물함)

5. **사물함 대여 정보 등록** (`acm_box_rent`)
   - 대여번호(rentSeq) 자동 생성
   - 예치금: 10000원
   - 예치금 결제여부: Y
   - 대여구분: ON (온라인)

6. **사물함 상태 업데이트** (`acm_box_num`)
   - boxFlag: Y (사용중)

**Response**:
```json
{
  "retMsg": "대여완료"
}
```

**Error Response**:
```json
{
  "retMsg": "대여실패"
}
```

### 4. 사물함 대여 연장

2개월 단위로 대여 기간을 연장합니다.

```bash
GET /api/locker/lockerExtendOrder?boxCd=A&boxNum=1&rentSeq=1&rentEnd=2024-03-01
```

**처리 로직**:
1. 기존 종료일 + 1일 = 연장 시작일
2. 기존 종료일 + 2개월 = 연장 종료일
3. 새로운 대여번호(rentSeq) 생성
4. 대여 정보 등록 (extendYn: Y, keyYn: N, rentType: OF)
5. 사물함 정보 업데이트

### 5. 사물함 변경

다른 사물함으로 변경하는 기능입니다.

```bash
GET /api/locker/lockerChangePop?usedBoxCd=A&usedBoxNum=1&boxCd=B&boxNum=2&usedRentSeq=1
```

**처리 프로세스**:
1. 신규 선택한 사물함에 기존 자료 업데이트 (`acm_box_num`)
2. 기존 사물함 공간 초기화 (`acm_box_num`)
3. 대여 정보에 신규 사물함번호 저장 (`acm_box_rent`)

### 6. 사물함 환불 처리

```bash
POST /api/locker/lockerRefund
Content-Type: application/x-www-form-urlencoded

rainend=user001&box_cd=A&box_num=1&rent_seq=1
```

**처리 프로세스**:
1. 환불 테이블에 환불 정보 삽입 (`acm_order_refund`)
   - 환불금액: 0
   - 환불일시: 현재시간
   - 환불메모: "본인이 직접 환불"

2. 사물함 대여 정보 업데이트 (`acm_box_rent`)
   - 환불 처리

3. 사물함 상세정보 초기화 (`acm_box_num`)
   - boxFlag: N (미사용)
   - userId: "" (초기화)

**Response**:
```json
{
  "retMsg": "환불완료"
}
```

### 7. 사물함 대여 주문 삭제

```bash
GET /api/locker/lockerDeleteOrder?orderNo=B20240001&boxCd=A&boxNum=1
```

**처리 프로세스**:
1. 사물함 정보 초기화
2. 사물함 대여정보 삭제 (주문번호 이용)
3. 사물함 주문정보 삭제 (주문번호 이용)

## 데이터베이스

### 관련 테이블

- `acm_box_num` - 사물함 상세 정보
- `acm_box_rent` - 사물함 대여 정보
- `acm_orders` - 주문 정보
- `acm_order_item` - 주문 상세 정보
- `acm_order_pay` - 주문 결제 정보
- `acm_order_refund` - 주문 환불 정보

### Mapper

- **Interface**: `com.academy.mapper.LockerMapper`
- **XML**: `src/main/resources/mapper/LockerMapper.xml`

## Value Objects

### LockerVO

사물함 정보를 담는 VO입니다.

**주요 속성**:
```java
// 사물함 기본 정보
boxCd, boxNum, boxFlag
userId, rentSeq

// 대여 정보
rentStart, rentEnd, rentMemo, rentType
deposit, depositYn, keyYn, extendYn

// 주문 정보
orderNo, orderType, orderYear, itemNo

// 페이징
pageIndex, pageUnit, pageSize, firstIndex, lastIndex
```

### OrdersVO

주문 정보를 담는 VO입니다.

**주요 속성**:
```java
// 주문 기본 정보
orderNo, userId, prdType

// 결제 정보
payTotal, priceCard, payCd

// 주문 상세
itemNo, orderCnt, statusCd, memo
confirmId

// 환불 정보
refundPrice, refundDt, refundMemo
```

## 트랜잭션 처리

중요한 작업들은 모두 `@Transactional` 어노테이션으로 보호됩니다:

```java
@Transactional(rollbackFor = Exception.class)
```

예외 발생 시 자동으로 롤백되며, 다음과 같이 예외를 다시 던집니다:

```java
try {
    // 처리 로직
} catch (Exception e) {
    e.printStackTrace();
    throw e; // 예외를 다시 던져야 rollback이 적용됩니다
}
```

## 주요 상수 값

### 상품구분 (prdType)
- `B`: 사물함

### 결제구분 (payCd)
- `PAY110`: 카드 결제

### 주문상태 (statusCd)
- `105`: 주문완료

### 대여구분 (rentType)
- `ON`: 온라인 대여
- `OF`: 오프라인 대여

### 사용여부 (boxFlag)
- `Y`: 사용중
- `N`: 미사용

### 키반납여부 (keyYn)
- `Y`: 반납
- `N`: 미반납
- `R`: 수령

### 연장여부 (extendYn)
- `Y`: 연장
- `N`: 비연장

## 페이징 처리

```java
PaginationInfo paginationInfo = new PaginationInfo();
paginationInfo.setCurrentPageNo(lockerVO.getPageIndex());
paginationInfo.setRecordCountPerPage(lockerVO.getPageUnit());
paginationInfo.setPageSize(lockerVO.getPageSize());

lockerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
lockerVO.setLastIndex(paginationInfo.getLastRecordIndex());
```

## 날짜 계산

대여 연장 시 날짜 계산:

```java
DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date stdate = dateFormat.parse(rentEnd);   // 기존 종료일
Calendar cal = Calendar.getInstance();
cal.setTime(stdate);
cal.add(Calendar.DATE, 1);                 // +1일
String nRentStart = dateFormat.format(cal.getTime());

cal.setTime(endate);
cal.add(Calendar.MONTH, 2);                 // +2개월
String nRentEnd = dateFormat.format(cal.getTime());
```

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.PaginationInfo` - 페이징 처리
- `com.academy.common.CommonUtil` - 공통 유틸리티
- `com.academy.mapper.LockerMapper` - 데이터 접근

## 주의사항

1. **트랜잭션 관리**: 대여, 연장, 변경, 환불 등 모든 작업은 트랜잭션 내에서 처리
2. **예외 처리**: 트랜잭션 롤백을 위해 예외를 반드시 다시 던져야 함
3. **예치금**: 기본 예치금은 10,000원
4. **대여 기간**: 기본 2개월, 연장 시 2개월씩 추가
5. **주문번호**: 자동 생성 (예: B202400001)

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**