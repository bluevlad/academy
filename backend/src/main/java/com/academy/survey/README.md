# Survey (설문조사) 패키지

설문조사 기능을 관리하는 RESTful API를 제공합니다.

## 패키지 구조

```
survey/
├── SurveyApi.java              # 설문조사 관리 REST API 컨트롤러
└── service/
    ├── SurveyService.java      # 설문조사 비즈니스 로직 서비스 (@Service)
    └── SurveyVO.java           # 설문조사 Value Object
```

## 주요 기능

### SurveyApi (설문 문항 - Bank)
- **GET** `/api/survey/bank/getBankList` - 설문 문항 목록 조회
- **GET** `/api/survey/bank/getBankDetail` - 설문 문항 상세 조회
- **POST** `/api/survey/bank/insertBank` - 설문 문항 등록
- **POST** `/api/survey/bank/updateBank` - 설문 문항 수정
- **POST** `/api/survey/bank/deleteBank` - 설문 문항 삭제

### SurveyApi (설문 세트 - Set)
- **GET** `/api/survey/set/getSetList` - 설문 세트 목록 조회
- **GET** `/api/survey/set/getSetDetail` - 설문 세트 상세 조회
- **POST** `/api/survey/set/insertSet` - 설문 세트 등록
- **POST** `/api/survey/set/updateSet` - 설문 세트 수정
- **POST** `/api/survey/set/deleteSet` - 설문 세트 삭제

### SurveyApi (설문 세트 항목)
- **POST** `/api/survey/set/insertSetItem` - 설문 세트 항목 추가
- **POST** `/api/survey/set/updateSetItem` - 설문 세트 항목 순서 수정
- **POST** `/api/survey/set/deleteSetItem` - 설문 세트 항목 삭제

### SurveyApi (설문 - Survey)
- **GET** `/api/survey/getSurveyList` - 설문 목록 조회
- **GET** `/api/survey/getSurveyDetail` - 설문 상세 조회
- **POST** `/api/survey/insertSurvey` - 설문 등록
- **POST** `/api/survey/updateSurvey` - 설문 수정
- **POST** `/api/survey/deleteSurvey` - 설문 삭제

### SurveyApi (설문 결과)
- **GET** `/api/survey/getAnswerList` - 설문 응답자 목록 조회

## 아키텍처

```
[Client]
   ↓
[SurveyApi] - REST API Layer
   ↓
[SurveyService] - Business Logic Layer
   ↓
[SurveyMapper] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[surveySQL.xml] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 설문 구조

```
설문 문항 (Bank)
   ↓ (문항들을 조합)
설문 세트 (Set)
   ↓ (세트를 기반으로 설문 생성)
설문 (Survey)
   ↓ (사용자 응답)
설문 결과 (Answer)
```

## 사용 예시

```java
// 설문 문항 등록
POST /api/survey/bank/insertBank
{
  "questionTitle": "수업 만족도는 어떻습니까?",
  "questionType": "RADIO",
  "options": ["매우 불만족", "불만족", "보통", "만족", "매우 만족"]
}

// 설문 세트에 문항 추가
POST /api/survey/set/insertSetItem
{
  "setId": "SET001",
  "bankId": "BANK001",
  "sortOrder": 1
}

// 설문 등록
POST /api/survey/insertSurvey
{
  "surveyTitle": "2024년 1학기 수강 만족도 조사",
  "setId": "SET001",
  "startDate": "2024-06-01",
  "endDate": "2024-06-30"
}
```

## 참고사항

- 설문 문항은 재사용 가능하며 여러 설문 세트에 포함될 수 있습니다
- 설문 세트는 문항들의 조합으로 구성됩니다
- 설문은 설문 세트를 기반으로 기간을 설정하여 운영됩니다
- 페이징은 CommonVO를 통해 처리됩니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
