# Config (설정) 패키지

Spring Boot 애플리케이션의 전역 설정을 관리합니다.

## 패키지 구조

```
config/
├── OpenApiConfig.java          # Swagger/OpenAPI 설정
└── WebMvcConfig.java           # Spring MVC 설정
```

## 주요 기능

### OpenApiConfig
Swagger UI 및 OpenAPI 문서 설정을 담당합니다.

- API 문서 제목, 설명, 버전 설정
- 개발 서버 URL 설정 (http://localhost:8080)
- 연락처 및 라이선스 정보 설정

**Swagger UI 접속**: `http://localhost:8080/swagger-ui.html`

### WebMvcConfig
Spring MVC 설정을 담당합니다.

- API 로깅 인터셉터 등록
- `/api/**` 경로에 로깅 인터셉터 적용
- Swagger 관련 경로 제외 처리
  - `/swagger-ui/**`
  - `/v3/api-docs/**`
  - `/swagger-resources/**`

## 설정 예시

```java
// OpenAPI 문서 정보
@Bean
public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Academy Backend API")
            .description("Small Online Academy Backend Service API Documentation")
            .version("1.0.0"));
}

// 인터셉터 등록
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(apiLoggingInterceptor)
        .addPathPatterns("/api/**");
}
```

## 참고사항

- OpenAPI 3.0 스펙을 사용합니다
- 모든 API 요청/응답은 인터셉터에 의해 로깅됩니다
- 추가 설정이 필요한 경우 이 패키지에 Configuration 클래스를 추가합니다

---

## Copyright

<img src="../../../../../../../../UM_CI.png" alt="UM Systems" width="10%">

**Copyright (c) 2021 운몽시스템즈(UM Systems). All rights reserved.**
