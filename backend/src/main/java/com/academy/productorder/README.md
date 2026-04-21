# ProductOrder (상품 주문) 패키지

상품 주문 및 쿠폰 관리를 위한 API를 제공합니다.

## 패키지 구조

```
productorder/
├── ProductOrderApi.java        # 상품 주문 REST API 컨트롤러
├── CouponMngApi.java           # 쿠폰 관리 REST API 컨트롤러
└── service/
    ├── ProductOrderService.java   # 상품 주문 비즈니스 로직 서비스 (@Service)
    ├── ProductOrderVO.java        # 상품 주문 Value Object
    ├── CouponMngService.java      # 쿠폰 관리 비즈니스 로직 서비스 (@Service)
    ├── CouponVO.java              # 쿠폰 Value Object
    └── ProductEventVO.java        # 상품 이벤트 Value Object
```

## 주요 기능

### ProductOrderApi
- **GET** `/api/productOrder/list` - 상품 주문 목록 조회
- **GET** `/api/productOrder/view` - 상품 주문 상세 조회
- **POST** `/api/productOrder/save` - 상품 주문 등록
- **PUT** `/api/productOrder/update` - 상품 주문 수정
- **DELETE** `/api/productOrder/delete` - 상품 주문 삭제

### CouponMngApi
- **GET** `/api/coupon/list` - 쿠폰 목록 조회
- **GET** `/api/coupon/view` - 쿠폰 상세 조회
- **POST** `/api/coupon/insert` - 쿠폰 등록
- **PUT** `/api/coupon/update` - 쿠폰 수정
- **GET** `/api/coupon/userList` - 쿠폰 발급 수강생 리스트
- **GET** `/api/coupon/coopLectureList` - 제휴사 수강권/쿠폰 리스트
- **GET** `/api/coupon/coopCouponList` - 제휴사 쿠폰 발급 리스트
- **POST** `/api/coupon/insertCoopCoupon` - 제휴사 쿠폰 등록
- **DELETE** `/api/coupon/deleteCoopCoupon` - 제휴사 쿠폰 삭제
- **GET** `/api/coupon/couponOrderList` - 공무원 쿠폰 사용 현황

### ProductOrderService / CouponMngService
- 상품 주문 및 쿠폰 CRUD 작업 처리
- 주문 상태 관리
- 결제 정보 처리
- MyBatis Mapper를 통한 데이터베이스 연동

## 아키텍처

```
[Client]
   ↓
[ProductOrderApi / CouponMngApi] - REST API Layer
   ↓
[ProductOrderService / CouponMngService] - Business Logic Layer
   ↓
[ProductOrderMapper / CouponMngMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[productOrderSQL.xml / couponMngSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 상품 주문 목록 조회
GET /api/productOrder/list?currentPage=1&pageRow=10&SEARCHTYPE=ORDER_STATUS&SEARCHTEXT=완료

// 상품 주문 등록
POST /api/productOrder/save
{
  "productId": "PROD001",
  "memberId": "USER001",
  "quantity": 2,
  "totalPrice": "50000",
  "orderStatus": "대기"
}

// 쿠폰 목록 조회
GET /api/coupon/list?currentPage=1&pageRow=10
```

## 참고사항

- Modern 아키텍처 패턴을 사용합니다 (Api → Service → Mapper)
- 모든 API는 세션 인증이 필요합니다
- 주문 상태는 단계별로 관리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- 결제 정보는 암호화되어 저장됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
