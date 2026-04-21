# Banner (배너) 패키지

배너 및 배너 아이템 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
banner/
├── BannerApi.java              # 배너 관리 REST API 컨트롤러
└── service/
    ├── BannerService.java      # 배너 비즈니스 로직 서비스 (@Service)
    ├── BannerVO.java           # 배너 마스터 Value Object
    └── BannerItemVO.java       # 배너 아이템 Value Object
```

## 주요 기능

### BannerApi (배너 마스터)
- **GET** `/api/banner/getBannerList` - 배너 목록 조회
- **GET** `/api/banner/getBannerDetail` - 배너 상세 조회
- **POST** `/api/banner/insertBanner` - 배너 등록 (아이템 자동 생성)
- **POST** `/api/banner/updateBanner` - 배너 수정
- **POST** `/api/banner/deleteBanner` - 배너 삭제 (연결된 아이템 함께 삭제)

### BannerApi (배너 아이템)
- **GET** `/api/banner/getBannerItemList` - 배너 아이템 목록 조회
- **GET** `/api/banner/getBannerItemDetail` - 배너 아이템 상세 조회
- **POST** `/api/banner/insertBannerItem` - 배너 아이템 등록
- **POST** `/api/banner/updateBannerItem` - 배너 아이템 수정
- **POST** `/api/banner/updateBannerItemFlag` - 배너 아이템 사용여부 수정
- **POST** `/api/banner/updateBannerItemReset` - 배너 아이템 초기화
- **POST** `/api/banner/deleteBannerItem` - 배너 아이템 삭제

## 아키텍처

```
[Client]
   ↓
[BannerApi] - REST API Layer
   ↓
[BannerService] - Business Logic Layer
   ↓
[BannerMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[bannerSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
   - TB_BANNER (배너 마스터)
   - TB_BANNER_ITEM (배너 아이템)
```

## 사용 예시

```java
// 배너 등록 (아이템 개수 지정시 자동 생성)
POST /api/banner/insertBanner
{
  "bannerNm": "메인 배너",
  "bannerCount": 5,
  "startNum": 1,
  "endNum": 5,
  "regId": "admin"
}

// 배너 아이템 사용여부 변경
POST /api/banner/updateBannerItemFlag
{
  "bannerCd": "BN001",
  "bannerNum": 1,
  "bannerFlag": "Y"
}
```

## 참고사항

- 배너 등록 시 bannerCount, startNum, endNum을 지정하면 아이템이 자동 생성됩니다
- 사용 중인 아이템이 있는 배너는 삭제할 수 없습니다
- 사용 중인 아이템이 있는 경우 기본 정보만 수정됩니다
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
