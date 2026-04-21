# Main Package

메인 페이지 관리 패키지입니다.

## 패키지 구조

```
main/
├── MainApi.java           # REST API Controller
└── service/
    ├── MainService.java   # Service class (@Service)
    └── MenuVO.java        # Menu Value Object
```

## 주요 컴포넌트

### MainApi

메인 페이지를 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/main`

### MainService

메인 페이지 비즈니스 로직을 처리하는 서비스 클래스입니다.

**주요 기능**:
- 메인 페이지 데이터 조회
- 최신 공지사항
- 인기 강의
- 추천 강의
- 배너 정보

## 데이터베이스

### 관련 테이블

- `TB_MAIN_BANNER` - 메인 배너
- `TB_BOARD` - 공지사항
- `TB_LECTURE` - 강의 정보

### Mapper

- **Interface**: `com.academy.mapper.MainMapper`
- **XML**: `src/main/resources/mapper/MainMapper.xml`

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.mapper.MainMapper` - 데이터 접근

## 참고사항

메인 페이지에 표시되는 다양한 정보를 통합 제공합니다.

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**