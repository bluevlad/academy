# Lecture (강의) 패키지

강의 및 강의 관련 메타데이터 관리를 위한 RESTful API를 제공합니다.

## 패키지 구조

```
lecture/
├── CategoryApi.java              # 카테고리 REST API 컨트롤러
├── FormApi.java                  # 학습형태 REST API 컨트롤러
├── KindApi.java                  # 종류 REST API 컨트롤러
├── LectureApi.java               # 강의 REST API 컨트롤러
├── LectureMstApi.java            # 강의 마스터 REST API 컨트롤러
├── MacAddressManagerApi.java     # MAC 주소 관리 REST API 컨트롤러
├── OpenLectureApi.java           # 오프라인 강의 REST API 컨트롤러
├── ProductEventApi.java          # 상품 이벤트 REST API 컨트롤러
├── SeriesApi.java                # 시리즈 REST API 컨트롤러
├── SubjectApi.java               # 과목 REST API 컨트롤러
├── TeacherApi.java               # 강사 REST API 컨트롤러
└── service/
    ├── CategoryService.java      # 카테고리 비즈니스 로직 서비스 (@Service)
    ├── CategoryVO.java           # 카테고리 Value Object
    ├── CatSeriesVO.java          # 카테고리 시리즈 Value Object
    ├── FormService.java          # 학습형태 비즈니스 로직 서비스 (@Service)
    ├── FormVO.java               # 학습형태 Value Object
    ├── KindService.java          # 종류 비즈니스 로직 서비스 (@Service)
    ├── KindVO.java               # 종류 Value Object
    ├── LectureService.java       # 강의 비즈니스 로직 서비스 (@Service)
    ├── LectureVO.java            # 강의 Value Object
    ├── LectureMstService.java    # 강의 마스터 비즈니스 로직 서비스 (@Service)
    ├── MacAddressManagerService.java # MAC 주소 관리 비즈니스 로직 서비스 (@Service)
    ├── MacAddressManagerVO.java  # MAC 주소 관리 Value Object
    ├── OpenLectureService.java   # 오프라인 강의 비즈니스 로직 서비스 (@Service)
    ├── OpenLectureVO.java        # 오프라인 강의 Value Object
    ├── ProductEventService.java  # 상품 이벤트 비즈니스 로직 서비스 (@Service)
    ├── ProductEventVO.java       # 상품 이벤트 Value Object
    ├── SeriesService.java        # 시리즈 비즈니스 로직 서비스 (@Service)
    ├── SeriesVO.java             # 시리즈 Value Object
    ├── SubjectService.java       # 과목 비즈니스 로직 서비스 (@Service)
    ├── SubjectVO.java            # 과목 Value Object
    ├── TeacherService.java       # 강사 비즈니스 로직 서비스 (@Service)
    └── TeacherVO.java            # 강사 Value Object
```

## 주요 기능

### 강의 관리 (LectureApi, LectureMstApi)
- **GET** `/api/lecture/list` - 강의 목록 조회
- **GET** `/api/lecture/view` - 강의 상세 조회
- **POST** `/api/lecture/save` - 강의 등록
- **PUT** `/api/lecture/update` - 강의 수정
- **DELETE** `/api/lecture/delete` - 강의 삭제

### 메타데이터 관리
- **CategoryApi** - 강의 카테고리 관리
- **FormApi** - 학습형태 관리 (온라인, 오프라인 등)
- **KindApi** - 강의 종류 관리
- **SubjectApi** - 과목 관리
- **TeacherApi** - 강사 정보 관리
- **SeriesApi** - 강의 시리즈 관리

### 특수 기능
- **MacAddressManagerApi** - MAC 주소 기반 강의 접근 제어
- **OpenLectureApi** - 오프라인 강의 관리
- **ProductEventApi** - 강의 상품 이벤트 관리

## 아키텍처

```
[Client]
   ↓
[LectureApi / LectureMstApi / ...] - REST API Layer
   ↓
[LectureService / LectureMstService / ...] - Business Logic Layer
   ↓
[LectureMapper / ...] - MyBatis Mapper Interface (com.academy.mapper)
   ↓
[lectureLectureSQL.xml / ...] - SQL Mapper (resources/mapper)
   ↓
[Database]
```

## 사용 예시

```java
// 강의 목록 조회
GET /api/lecture/list?currentPage=1&pageRow=10&SUBJECT_CD=MATH&TEACHER_CD=T001

// 강의 마스터 등록
POST /api/lecturemst/save
{
  "mstcode": "M202501",
  "subjectCd": "MATH",
  "teacherCd": "T001",
  "formCd": "ONLINE",
  "lecNm": "수학 기본 과정"
}

// 강사 목록 조회
GET /api/teacher/list?GUBUN=T&SEARCHTEXT=김

// 과목별 강사 목록
GET /api/subject/teacherList?SUBJECT_CD=MATH
```

## 참고사항

- 모든 API는 세션 인증이 필요합니다
- 페이징은 CommonVO를 통해 처리됩니다
- 트랜잭션은 @Transactional 애노테이션으로 관리됩니다
- 강의 코드는 자동 생성됩니다 (MYYYYNNNNN, RYYYYNNNNN 형식)
- 파일 업로드는 FileUtil을 통해 처리됩니다
- LectureVO는 LectureMstVO의 필드를 통합하여 사용합니다
- 강의와 교재(Book)는 연관 관계를 가집니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
