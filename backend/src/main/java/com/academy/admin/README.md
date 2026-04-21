# Admin (관리자) 패키지

관리자 권한, 메뉴, 공통코드 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
admin/
├── AdminAuthApi.java           # 권한 관리 REST API 컨트롤러
├── AdminCodeApi.java           # 공통코드 관리 REST API 컨트롤러
├── AdminMenuApi.java           # 메뉴 관리 REST API 컨트롤러
└── service/
    ├── AdminAuthService.java   # 권한 관리 비즈니스 로직 서비스 (@Service)
    ├── AdminCodeService.java   # 공통코드 관리 비즈니스 로직 서비스 (@Service)
    ├── AdminCodeVO.java        # 공통코드 Value Object
    ├── AdminMenuService.java   # 메뉴 관리 비즈니스 로직 서비스 (@Service)
    ├── AdminMenuVO.java        # 메뉴 Value Object
    ├── AdminSiteMenuVO.java    # 사이트별 메뉴 Value Object
    └── AdminSiteVO.java        # 사이트/권한 Value Object
```

## 주요 기능

### AdminAuthApi (권한 관리)
- **GET** `/api/admin/auth/getAuthList` - 권한 목록 조회
- **GET** `/api/admin/auth/getAuthDetail` - 권한 상세 조회
- **POST** `/api/admin/auth/insertAuth` - 권한 등록
- **POST** `/api/admin/auth/updateAuth` - 권한 수정
- **POST** `/api/admin/auth/deleteAuth` - 권한 삭제
- **POST** `/api/admin/auth/deleteAuthBatch` - 권한 일괄 삭제
- **GET** `/api/admin/auth/getOnlineMenuList` - 온라인 메뉴 목록 조회
- **GET** `/api/admin/auth/getOfflineMenuList` - 오프라인 메뉴 목록 조회
- **GET** `/api/admin/auth/getTestMenuList` - 모의고사 메뉴 목록 조회
- **POST** `/api/admin/auth/insertAuthMenu` - 권한별 메뉴 등록
- **GET** `/api/admin/auth/getAuthMenuList` - 권한별 메뉴 목록 조회

### AdminCodeApi (공통코드 관리)
- **GET** `/api/admin/code/getCodeList` - 공통코드 목록 조회
- **GET** `/api/admin/code/getCodeDetail` - 공통코드 상세 조회
- **POST** `/api/admin/code/insertCode` - 공통코드 등록
- **POST** `/api/admin/code/updateCode` - 공통코드 수정
- **POST** `/api/admin/code/deleteCode` - 공통코드 삭제
- **POST** `/api/admin/code/deleteCodeBatch` - 공통코드 일괄 삭제
- **GET** `/api/admin/code/getPassCodeTree` - 설정코드 트리 조회
- **GET** `/api/admin/code/getPassCodeDetail` - 설정코드 상세 조회
- **POST** `/api/admin/code/insertPassCode` - 설정코드 등록
- **POST** `/api/admin/code/updatePassCode` - 설정코드 수정
- **POST** `/api/admin/code/deletePassCode` - 설정코드 삭제
- **GET** `/api/admin/code/getPassMaxCodeId` - 설정코드 MAX ID 조회
- **GET** `/api/admin/code/getBaConfigCodeList` - BA CONFIG 코드 목록 조회

### AdminMenuApi (메뉴 관리)
- **GET** `/api/admin/menu/getMenuTree` - 관리자 메뉴 트리 조회
- **GET** `/api/admin/menu/getPassMenuTree` - 사용자 메뉴 트리 조회
- **GET** `/api/admin/menu/getMenuDetail` - 관리자 메뉴 상세 조회
- **GET** `/api/admin/menu/getPassMenuDetail` - 사용자 메뉴 상세 조회
- **POST** `/api/admin/menu/insertMenu` - 관리자 메뉴 등록
- **POST** `/api/admin/menu/insertPassMenu` - 사용자 메뉴 등록
- **POST** `/api/admin/menu/updateMenu` - 관리자 메뉴 수정
- **POST** `/api/admin/menu/updatePassMenu` - 사용자 메뉴 수정
- **POST** `/api/admin/menu/deleteMenu` - 관리자 메뉴 삭제
- **POST** `/api/admin/menu/deletePassMenu` - 사용자 메뉴 삭제
- **GET** `/api/admin/menu/checkMenuIdDuplicate` - 메뉴 ID 중복 체크
- **GET** `/api/admin/menu/getMaxMenuId` - 관리자 메뉴 MAX ID 조회
- **GET** `/api/admin/menu/getPassMaxMenuId` - 사용자 메뉴 MAX ID 조회

## 아키텍처

```
[Client]
   ↓
[Admin*Api] - REST API Layer
   ↓
[Admin*Service] - Business Logic Layer
   ↓
[Admin*Mapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[admin*.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 참고사항

- 모든 API는 관리자 권한 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 메뉴는 트리 구조로 관리됩니다
- 권한별로 접근 가능한 메뉴를 설정할 수 있습니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
