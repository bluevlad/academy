# 인덱스/메뉴 관리 패키지

관리자 메뉴 구성 및 권한 관리 기능을 제공하는 패키지입니다.

## 패키지 구조

```
index/
├── IndexApi.java                   # REST Controller
├── README.md
└── service/
    ├── IndexService.java           # 서비스 클래스
    └── IndexVO.java                # VO
```

## 기능 설명

관리자 시스템의 TOP 메뉴, LEFT 메뉴 구성 및 메뉴 권한 관리 기능을 제공합니다.

### 주요 기능

#### 메뉴 조회
- TOP 메뉴 목록 조회 (사용자 권한별)
- LEFT 메뉴 목록 조회 (TOP 메뉴별)
- LEFT 메뉴 트리 조회 (재귀적 하위 메뉴 포함)

#### 메뉴 마스터 관리
- 메뉴 목록/상세 조회
- 메뉴 등록/수정/삭제

#### 사이트 메뉴 권한 관리
- 사이트별 메뉴 권한 목록 조회
- 사이트 메뉴 권한 등록/삭제

## API 엔드포인트

### 메뉴 조회
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/index/getTopMenuList` | TOP 메뉴 목록 조회 |
| GET | `/api/index/getLeftMenuList` | LEFT 메뉴 목록 조회 |
| GET | `/api/index/getLeftMenuTree` | LEFT 메뉴 트리 조회 |

### 메뉴 마스터 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/index/getMenuList` | 메뉴 목록 조회 |
| GET | `/api/index/getMenuDetail` | 메뉴 상세 조회 |
| POST | `/api/index/insertMenu` | 메뉴 등록 |
| POST | `/api/index/updateMenu` | 메뉴 수정 |
| POST | `/api/index/deleteMenu` | 메뉴 삭제 |

### 사이트 메뉴 권한 관리
| HTTP | URL | 설명 |
|------|-----|------|
| GET | `/api/index/getSiteMenuList` | 사이트 메뉴 권한 목록 조회 |
| POST | `/api/index/insertSiteMenu` | 사이트 메뉴 권한 등록 |
| POST | `/api/index/deleteSiteMenu` | 사이트 메뉴 권한 삭제 |

## 관련 테이블

| 테이블명 | 설명 |
|---------|------|
| TB_SG_MENU_MST | 메뉴 마스터 테이블 |
| TB_SG_SITE_MENU | 사이트별 메뉴 권한 테이블 |

### TB_SG_MENU_MST 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| MENU_ID | VARCHAR | 메뉴 ID (PK) |
| MENU_NM | VARCHAR | 메뉴명 |
| P_MENUID | VARCHAR | 상위 메뉴 ID |
| MENU_URL | VARCHAR | 메뉴 URL |
| MENU_SEQ | INT | 메뉴 순서 |
| TARGET | VARCHAR | 타겟 (_self, _blank 등) |
| ISUSE | VARCHAR | 사용여부 (Y/N) |

### TB_SG_SITE_MENU 테이블 주요 컬럼

| 컬럼명 | 타입 | 설명 |
|--------|------|------|
| SITE_ID | VARCHAR | 사이트 ID (권한 역할) |
| MENU_ID | VARCHAR | 메뉴 ID |

## 메뉴 타입

| 타입 | 설명 |
|------|------|
| OM_ROOT | 온라인 메뉴 루트 |
| FM_ROOT | 오프라인 메뉴 루트 |
| TM_ROOT | 테스트 메뉴 루트 |

## 온/오프라인 구분 (onoffDiv)

| 코드 | 설명 |
|------|------|
| A | 전체 (온라인 + 오프라인) |
| O | 온라인만 |
| F | 오프라인만 |
| T | 테스트 |

## 검색 조건

### 메뉴 조회
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| adminRole | String | 관리자 권한 (사이트 ID) |
| menuType | String | 메뉴 타입 (OM_ROOT, FM_ROOT, TM_ROOT) |
| topMenuId | String | TOP 메뉴 ID |

### 메뉴 마스터
| 파라미터 | 타입 | 설명 |
|---------|------|------|
| pMenuId | String | 상위 메뉴 ID |
| searchKeyword | String | 메뉴명 검색 |
| isuse | String | 사용여부 (Y/N) |

## 사용 예시

### TOP 메뉴 목록 조회
```
GET /api/index/getTopMenuList?adminRole=ADMIN&menuType=OM_ROOT
```

### LEFT 메뉴 트리 조회
```
GET /api/index/getLeftMenuTree?adminRole=ADMIN&topMenuId=MENU001
```

### 메뉴 등록
```
POST /api/index/insertMenu
Content-Type: application/x-www-form-urlencoded

menuId=MENU_NEW&menuNm=새 메뉴&pMenuId=OM_ROOT&menuUrl=/new/menu&menuSeq=10&target=_self&isuse=Y
```

### 사이트 메뉴 권한 등록
```
POST /api/index/insertSiteMenu
Content-Type: application/x-www-form-urlencoded

siteId=ADMIN&menuId=MENU_NEW
```

## 메뉴 트리 구조

메뉴는 계층적 구조로 구성됩니다:

```
ROOT (OM_ROOT / FM_ROOT / TM_ROOT)
├── TOP_MENU_1
│   ├── LEFT_MENU_1_1
│   │   ├── SUB_MENU_1_1_1
│   │   └── SUB_MENU_1_1_2
│   └── LEFT_MENU_1_2
├── TOP_MENU_2
│   └── LEFT_MENU_2_1
└── ...
```

`selectLeftMenuTree`는 MySQL의 재귀 CTE(Common Table Expression)를 사용하여 계층적 메뉴 구조를 조회합니다.

## 관련 Mapper

- **IndexMapper.java**: `com.academy.mapper.IndexMapper`
- **index.xml**: `src/main/resources/mapper/index.xml`

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
