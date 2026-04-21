# Login Package

인증(Authentication) 및 로그인 관리 패키지입니다.

## 패키지 구조

```
login/
├── LoginApi.java           # REST API Controller
└── service/
    ├── LoginService.java   # Service class (@Service)
    └── MemberVO.java       # Member Value Object
```

## 주요 컴포넌트

### LoginApi

인증 및 로그인을 위한 REST API 컨트롤러입니다.

**Base URL**: `/api/auth`

**Endpoints**:

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| POST | `/login` | 일반(세션) 로그인 처리 | userInfo, token |
| POST | `/userInfo` | 사용자 정보 조회 | userInfo |
| POST | `/insertUser` | 회원가입 | retMsg |

### LoginService

인증 비즈니스 로직을 처리하는 서비스 클래스입니다.

**Methods**:

```java
// 사용자 조회
JSONObject getUser(MemberVO memberVO)

// 회원가입
void insertUser(MemberVO memberVO)

// 토큰 업데이트
void updateToken(MemberVO memberVO)
```

## 주요 기능

### 1. 로그인

JWT 토큰 기반 로그인을 처리합니다.

```bash
POST /api/auth/login
Content-Type: application/x-www-form-urlencoded

userId=user001&password=pass123
```

**처리 프로세스**:
1. userId 검증 (null 방지)
2. 사용자 정보 조회
3. JWT 토큰 생성
4. 토큰 DB 업데이트
5. 사용자 정보와 토큰 반환

**Response**:
```json
{
  "userInfo": {
    "userId": "user001",
    "userName": "홍길동",
    "email": "hong@example.com",
    "userType": "student"
  },
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Error Response**:
```json
{
  "retMsg": "FAIL"
}
```

### 2. 사용자 정보 조회

```bash
POST /api/auth/userInfo
Content-Type: application/x-www-form-urlencoded

userId=user001
```

**Response**:
```json
{
  "userInfo": {
    "userId": "user001",
    "userName": "홍길동",
    "email": "hong@example.com"
  }
}
```

### 3. 회원가입

```bash
POST /api/auth/insertUser
Content-Type: application/x-www-form-urlencoded

userId=user002&password=pass123&userName=김철수&email=kim@example.com
```

**Response**:
```json
{
  "retMsg": "OK"
}
```

## JWT 토큰

### JwtUtil 사용

```java
@Autowired
private JwtUtil jwtUtil;

// 토큰 생성
String token = jwtUtil.generateToken(userId);

// 토큰 검증
boolean isValid = jwtUtil.validateToken(token);

// 사용자 ID 추출
String userId = jwtUtil.getUserIdFromToken(token);
```

### 토큰 저장

생성된 JWT 토큰은 DB에 저장됩니다:

```java
memberVO.setToken(token);
loginService.updateToken(memberVO);
```

## 데이터베이스

### 관련 테이블

- `TB_MEMBER` - 회원 정보 (토큰 포함)

### Mapper

- **Interface**: `com.academy.mapper.LoginMapper`
- **XML**: `src/main/resources/mapper/LoginMapper.xml`

## 보안

### userId 검증

Optional을 사용하여 null 안전성을 보장합니다:

```java
String userId = Optional.ofNullable(memberVO.getUserId())
    .map(Object::toString)
    .orElseThrow(() -> new IllegalArgumentException("User ID not found"));
```

### 에러 처리

모든 작업은 try-catch로 보호됩니다:

```java
try {
    // 로그인 처리
    loginService.getUser(memberVO);
} catch (Exception e) {
    e.printStackTrace();
    jsonObject.put("retMsg", "FAIL");
}
```

## 세션 vs JWT

이 패키지는 **JWT 토큰 기반** 인증을 사용합니다:
- 로그인 시 JWT 토큰 생성 및 반환
- 토큰을 DB에 저장
- 클라이언트는 토큰을 저장하고 API 요청 시 사용

## 의존성

- `com.academy.common.CORSFilter` - CORS 설정
- `com.academy.common.JwtUtil` - JWT 토큰 유틸리티
- `com.academy.mapper.LoginMapper` - 데이터 접근

## 참고사항

1. **JWT 토큰**: JJWT 0.11.5 라이브러리 사용
2. **세션 관리**: 다른 API들은 세션 기반 인증 사용 (`AdmUserInfo`)
3. **비밀번호**: 암호화 처리 권장 (현재 평문)
4. **토큰 만료**: JWT 토큰 만료 시간 설정 권장

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**