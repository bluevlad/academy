# Box (사물함) 패키지

오프라인 학원의 사물함 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
box/
├── BoxApi.java                 # 사물함 관리 REST API 컨트롤러
└── service/
    ├── BoxService.java         # 사물함 비즈니스 로직 서비스 (@Service)
    ├── BoxVO.java              # 사물함 마스터 Value Object
    └── BoxNumVO.java           # 사물함 세부정보 Value Object
```

## 주요 기능

### BoxApi (사물함 마스터)
- **GET** `/api/box/getBoxList` - 사물함 목록 조회
- **GET** `/api/box/getBoxDetail` - 사물함 상세 조회
- **POST** `/api/box/insertBox` - 사물함 등록 (세부정보 자동 생성)
- **POST** `/api/box/updateBox` - 사물함 수정
- **POST** `/api/box/deleteBox` - 사물함 삭제 (연결된 세부정보 함께 삭제)

### BoxApi (사물함 세부정보)
- **GET** `/api/box/getBoxNumList` - 사물함 세부정보 목록 조회
- **GET** `/api/box/getBoxNumDetail` - 사물함 세부정보 상세 조회
- **POST** `/api/box/insertBoxNum` - 사물함 세부정보 등록
- **POST** `/api/box/updateBoxNum` - 사물함 세부정보 수정
- **POST** `/api/box/updateBoxNumFlag` - 사물함 사용상태 수정
- **POST** `/api/box/updateBoxNumReset` - 사물함 세부정보 초기화
- **POST** `/api/box/deleteBoxNum` - 사물함 세부정보 삭제

## 아키텍처

```
[Client]
   ↓
[BoxApi] - REST API Layer
   ↓
[BoxService] - Business Logic Layer
   ↓
[BoxMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[boxSQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
   - TB_OFF_BOX (사물함 마스터)
   - TB_OFF_BOX_NUM (사물함 세부정보)
```

## 사용 예시

```java
// 사물함 등록 (개수 지정시 세부정보 자동 생성)
POST /api/box/insertBox
{
  "boxNm": "1층 사물함",
  "boxCount": 100,
  "startNum": 1,
  "endNum": 100,
  "regId": "admin"
}

// 사물함 사용상태 변경
POST /api/box/updateBoxNumFlag
{
  "boxCd": "BOX001",
  "boxNum": 1,
  "boxFlag": "Y",
  "userId": "user001"
}
```

## 참고사항

- 사물함 등록 시 boxCount, startNum, endNum을 지정하면 세부정보가 자동 생성됩니다
- 사용 중인 사물함이 있으면 해당 사물함 그룹은 삭제할 수 없습니다
- 사용 중인 사물함이 있는 경우 기본 정보만 수정됩니다
- boxFlag: 'Y' (사용중), 'N' (미사용)
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
