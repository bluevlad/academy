# Popup (팝업) 패키지

웹사이트 팝업 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
popup/
├── PopupApi.java               # 팝업 관리 REST API 컨트롤러
└── service/
    ├── PopupService.java       # 팝업 비즈니스 로직 서비스 (@Service)
    └── PopupVO.java            # 팝업 Value Object
```

## 주요 기능

### PopupApi
- **GET** `/api/popup/getPopupList` - 팝업 목록 조회
- **GET** `/api/popup/getPopupDetail` - 팝업 상세 조회
- **POST** `/api/popup/insertPopup` - 팝업 등록
- **POST** `/api/popup/updatePopup` - 팝업 수정
- **POST** `/api/popup/deletePopup` - 팝업 삭제
- **POST** `/api/popup/updatePopupOpenYn` - 팝업 공개여부 변경
- **POST** `/api/popup/updatePopupHit` - 팝업 조회수 증가

## 아키텍처

```
[Client]
   ↓
[PopupApi] - REST API Layer
   ↓
[PopupService] - Business Logic Layer
   ↓
[PopupMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[popupSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 팝업 목록 조회
GET /api/popup/getPopupList?pageIndex=1&pageUnit=10

// 팝업 등록
POST /api/popup/insertPopup
{
  "popupTitle": "공지사항 팝업",
  "popupContent": "<p>내용</p>",
  "startDate": "2024-01-01",
  "endDate": "2024-12-31",
  "openYn": "Y"
}

// 팝업 공개여부 변경
POST /api/popup/updatePopupOpenYn
{
  "popupId": "POP001",
  "openYn": "N"
}
```

## 참고사항

- 팝업은 시작일/종료일 기반으로 노출됩니다
- openYn: 'Y' (공개), 'N' (비공개)
- 조회수는 사용자가 팝업을 볼 때마다 증가합니다
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
