# ApiTest (API 테스트) 패키지

API 테스트 및 차트 데이터 조회를 위한 테스트용 RESTful API를 제공합니다.

## 패키지 구조

```
apitest/
└── Main.java                   # 테스트용 REST API 컨트롤러
```

## 주요 기능

### Main
- **GET** `/api/reportsBarChartData` - 막대 차트 데이터 조회
- **GET** `/api/reportsLineChartData` - 라인 차트 데이터 조회
- **POST** `/api/reqBoay` - RequestBody 테스트
- **POST** `/api/reqParam` - RequestParam 테스트

## 참고사항

- 개발 및 테스트 목적으로 사용되는 패키지입니다
- 차트 데이터는 로컬 파일 시스템에서 JSON 파일을 읽어 반환합니다
- 운영 환경에서는 사용하지 않는 것을 권장합니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
