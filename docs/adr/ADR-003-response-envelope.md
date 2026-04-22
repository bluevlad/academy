# ADR-003 — 공통 Response Envelope

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-001](./ADR-001-integration-strategy.md)

## 문맥

현재 두 저장소의 응답 구조가 다름:
- **academy-admin**: `{ data: ..., retMsg: "OK"|"FAIL" }` (`JSONObject`) — 400+ 엔드포인트가 이 포맷
- **academy-user**: 개별 endpoint 마다 상이 (일부 raw DTO, 일부 envelope)
- 통합 이후 `/api/admin/**`·`/api/user/**`·`/api/shared/**` 가 하나의 계약으로 통일 필요

## 결정

### 단일 envelope: `ApiResponse<T>`

```json
{
  "success": true,
  "data": { ... },
  "error": null,
  "retMsg": "OK",
  "timestamp": "2026-04-22T10:30:00Z"
}
```

실패:
```json
{
  "success": false,
  "data": null,
  "error": { "code": "AUTH_001", "message": "로그인이 만료되었습니다." },
  "retMsg": "FAIL",
  "timestamp": "2026-04-22T10:30:00Z"
}
```

- `retMsg` 는 **후방 호환성** 용도로 Sprint 5 까지 유지 (admin-web 의 기존 체크 로직 0 리그레션)
- `success` / `error` 는 신규 필드, user-web Sprint 2 부터 이 필드 기준으로 분기
- `timestamp` 는 Asia/Seoul ISO-8601

### 구현

```
com.academy.shared.common/
├── ApiResponse.java       # record ApiResponse<T>(boolean success, T data, ApiError error, String retMsg, Instant timestamp)
├── ApiError.java          # record ApiError(String code, String message, Map<String,Object> details)
├── ErrorCode.java         # enum (AUTH_001, VALIDATION_001, NOT_FOUND_001 ...)
└── GlobalExceptionHandler.java  # @RestControllerAdvice — 모든 예외를 ApiResponse 로 포장
```

- Controller 는 `ApiResponse.ok(data)` 또는 `ApiResponse.fail(code, message)` 로 반환
- 예외는 `GlobalExceptionHandler` 가 catch → `ApiError` 로 변환 → HTTP status + envelope
- `throw new BusinessException(ErrorCode.AUTH_001)` 패턴

### HTTP 상태 코드

| 유형 | HTTP |
|---|---|
| 성공 | 200 (GET/POST/PUT) · 201 (create) · 204 (delete, body 없음) |
| 클라이언트 오류 | 400 (validation) · 401 (인증) · 403 (권한) · 404 (not found) · 409 (conflict) |
| 서버 오류 | 500 |

envelope 의 `success` 와 HTTP status 는 **같은 방향** (200 = success:true, 4xx/5xx = success:false).

### Pagination

```json
{
  "success": true,
  "data": {
    "items": [ ... ],
    "pagination": { "page": 1, "size": 20, "totalItems": 157, "totalPages": 8 }
  },
  ...
}
```

기존 `PaginationInfo` (pageUnit=10, pageSize=10) 는 admin legacy 조회에서만 사용, 신규는 위 구조.

### 적용 범위

- `/api/admin/**` 기존 엔드포인트: Sprint 5 까지 `retMsg` 유지, 내부적으로 GlobalExceptionHandler 로 래핑
- `/api/user/**` 신규 이관: 처음부터 `ApiResponse<T>` 직접 반환
- `/api/shared/**`: 처음부터 `ApiResponse<T>`
- Swagger 스키마에 `ApiResponse` 표기 — admin-web·user-web 이 자동 타입 생성 가능

## 결과

- 프론트엔드 공통 axios interceptor 1개로 admin-web·user-web 양쪽 에러 처리
- 기존 admin mapper/service/controller 는 즉시 변경 불필요 (ResponseAdvice 로 자동 포장)
- 신규 코드는 `ApiResponse.ok()` 관용구로 단일 경로

## 리스크

| 리스크 | 완화 |
|---|---|
| 기존 `JSONObject` 반환 Controller 가 `ApiResponse` 로 자동 포장되면서 이중 래핑 가능 | `ResponseBodyAdvice` 에서 `JSONObject`·`Map<String,Object>` 감지 → envelope `data` 안으로 이동 |
| admin-web 이 `retMsg` 외에 `data` 직접 의존 | envelope 의 `data` 가 기존 응답과 1:1 이므로 호환 (정상 케이스 0 변경) |
| 시리얼라이제이션 오버헤드 | Jackson `@JsonInclude(NON_NULL)` 로 null 필드 생략 |

## 후속 결정

- ErrorCode 카탈로그 표준화 (모듈별 prefix) — Sprint 1-1 구현 시 초안
- i18n 오류 메시지 — MVP 이후
