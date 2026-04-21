# Dashboard Package

대시보드(Dashboard) 관리 패키지입니다.

## 패키지 구조

```
dashboard/
├── DashBoardApi.java           # REST API Controller
└── service/
    └── DashBoardService.java   # Service class (@Service)
```

## 주요 컴포넌트

### DashBoardApi

관리자 대시보드를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/dashboard`

**Endpoints**:

| Method | Endpoint | Description | Parameters | Response |
|--------|----------|-------------|------------|----------|
| GET | `/myRentLocker` | 내 대여 사물함 조회 | userId | myRentLocker |

### DashBoardService

대시보드 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Methods**:

```java
// 내 대여 사물함 조회
JSONObject myRentLocker(LockerVO lockerVO)
```

## 사용 예시

### 1. 내 대여 사물함 조회

사용자가 현재 대여 중인 사물함 정보를 조회합니다.

```bash
GET /api/dashboard/myRentLocker?userId=user001
```

**Response**:
```json
{
  "myRentLocker": {
    "boxCd": "A",
    "boxNum": 15,
    "rentStart": "2024-01-01",
    "rentEnd": "2024-03-01",
    "deposit": 10000,
    "orderNo": "B202400001"
  }
}
```

**사용자가 대여 중인 사물함이 없는 경우**:
```json
{
  "myRentLocker": null
}
```

## 데이터베이스

### 관련 테이블

- `acm_box_rent` - 사물함 대여 정보
- `acm_box_num` - 사물함 상세 정보

### Mapper

- **Interface**: `com.academy.mapper.DashBoardMapper`
- **XML**: `src/main/resources/mapper/DashBoardMapper.xml`

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.locker.service.LockerVO` - 사물함 VO
- `com.academy.mapper.DashBoardMapper` - 데이터 접근

## 확장 가능성

대시보드 기능 확장 시 추가 가능한 기능:
- 전체 통계 조회 (회원 수, 강의 수, 주문 수 등)
- 최근 주문 내역
- 진행 중인 강의
- 최근 공지사항
- 학습 진도율
- 결제 내역
- 포인트 잔액
- 쿠폰 보유 현황

## 참고사항

1. **LockerVO 사용**: 사물함 패키지의 LockerVO를 재사용
2. **단순한 구조**: 현재는 사물함 조회만 제공
3. **향후 확장**: 다양한 대시보드 위젯 추가 가능

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**