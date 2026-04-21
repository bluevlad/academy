# Menu (메뉴) 패키지

시스템 메뉴 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
menu/
├── MenuApi.java            # 메뉴 REST API 컨트롤러
└── service/
    ├── MenuService.java    # 메뉴 비즈니스 로직 서비스 (@Service)
    └── MenuVO.java         # 메뉴 Value Object
```

## 주요 기능

### MenuApi
- **GET** `/api/menu/list` - 메뉴 목록 조회
- **GET** `/api/menu/tree` - 메뉴 트리 구조 조회
- **GET** `/api/menu/view` - 메뉴 상세 조회
- **POST** `/api/menu/save` - 메뉴 등록
- **PUT** `/api/menu/update` - 메뉴 수정
- **DELETE** `/api/menu/delete` - 메뉴 삭제
- **GET** `/api/menu/userMenuList` - 사용자별 메뉴 조회

### MenuService
- 메뉴 CRUD 작업 처리
- 계층형 메뉴 구조 관리
- 권한별 메뉴 필터링
- MyBatis MenuMapper를 통한 데이터베이스 연동

### MenuVO
- CommonVO를 상속하여 공통 필드 사용
- 메뉴 관련 데이터 전달 객체
- 계층 구조 정보 포함

## 아키텍처

```
[Client]
   ↓
[MenuApi] - REST API Layer
   ↓
[MenuService] - Business Logic Layer
   ↓
[MenuMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[menuSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 메뉴 트리 조회
GET /api/menu/tree

// 사용자별 메뉴 조회
GET /api/menu/userMenuList?USER_ID=admin

// 메뉴 등록
POST /api/menu/save
{
  "menuNm": "교재 관리",
  "menuUrl": "/book/list",
  "upperMenuId": "MENU001",
  "menuOrder": 1,
  "useYn": "Y"
}
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 메뉴는 계층형 구조로 관리됩니다
- 사용자 권한에 따라 메뉴가 필터링됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
