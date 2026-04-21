# BookOrder (교재 주문) 패키지

교재 주문 관리를 위한 API를 제공합니다.

## 패키지 구조

```
bookOrder/
├── BookOrderApi.java           # 교재 주문 REST API 컨트롤러
└── service/
    ├── BookOrderService.java   # 교재 주문 비즈니스 로직 서비스 (@Service)
    └── BookOrderVO.java        # 교재 주문 Value Object
```

## 주요 기능

### BookOrderApi
- **GET** `/api/bookOrder/list` - 교재 주문 목록 조회
- **GET** `/api/bookOrder/view` - 교재 주문 상세 조회
- **POST** `/api/bookOrder/save` - 교재 주문 등록
- **PUT** `/api/bookOrder/update` - 교재 주문 수정
- **DELETE** `/api/bookOrder/delete` - 교재 주문 삭제

### BookOrderService
- 교재 주문 CRUD 작업 처리
- 주문 상태 관리
- MyBatis BookOrderMapper를 통한 데이터베이스 연동

## 아키텍처

```
[Client]
   ↓
[BookOrderApi] - REST API Layer
   ↓
[BookOrderService] - Business Logic Layer
   ↓
[BookOrderMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[bookOrderSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 참고사항

- Modern 아키텍처 패턴을 사용합니다 (Api → Service → Mapper)
- 모든 API는 세션 인증이 필요합니다
- 파일 다운로드 기능을 지원합니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
