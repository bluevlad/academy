# ADR-002 — 인증 통합 포맷 (JWT + Redis Refresh + Stateless + 필터 분리)

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-001](./ADR-001-integration-strategy.md)

## 문맥

현재 `SecurityConfig.java`:
- `InMemoryUserDetailsManager` 에 `admin / dnflskfk` **하드코딩** (단일 관리자)
- `SessionCreationPolicy.IF_REQUIRED` → 세션 기반
- `formLogin`, `httpBasic`, `logout` 모두 disable — 로그아웃 무효화 경로 없음
- `JwtUtil` 은 존재하지만 access 만 (1h), refresh 없음
- user BE 의 36/38 API 는 레거시 HttpServletRequest 인증 — Sprint 1 대상

상위 목표: admin-web·user-web 두 SPA 가 동일한 인증 프레임을 공유하고, 관리자·수강생 role 로 `/api/admin/**`·`/api/user/**` 경계를 필터 수준에서 강제.

## 결정

### 1. Stateless JWT + Redis Refresh

- `SessionCreationPolicy.STATELESS` 로 전환
- Access Token: JWT, **30분** 만료, claims = `{ sub: userId, role: ADMIN|USER, iat, exp, aud: "admin"|"user" }`
- Refresh Token: **14일** 만료, Redis `refresh:{role}:{userId}` 에 token hash 저장 (TTL=14d)
  - 서버가 선택적 무효화 가능 (로그아웃·비밀번호 변경 시 삭제)
- JWT 서명: HMAC-SHA256 + `JWT_SECRET` env (최소 32B)

### 2. 필터 분리

```
shared.security/
├── JwtTokenProvider.java          # access/refresh 발급·검증
├── RefreshTokenStore.java         # Redis 어댑터
├── AdminJwtFilter.java            # /api/admin/** 만 검증, role=ADMIN 필요
├── UserJwtFilter.java             # /api/user/** 만 검증, role=USER 필요
└── SecurityConfig.java            # permitAll + chain 구성
```

- `/api/shared/**`, `/actuator/health/**`, `/api/auth/**`, `/swagger-ui/**` 는 permitAll
- `/api/admin/**` → AdminJwtFilter 통과 + `hasRole('ADMIN')`
- `/api/user/**` → UserJwtFilter 통과 + `hasRole('USER')`
- `/api/auth/**` 에서 로그인·리프레시·로그아웃 제공 (미인증 상태로 접근 가능)

### 3. 엔드포인트

| Method | Path | 용도 |
|---|---|---|
| POST | `/api/auth/login` | body `{email, password, audience:"admin"\|"user"}` → access+refresh |
| POST | `/api/auth/refresh` | body `{refresh}` → 새 access (refresh 회전 선택) |
| POST | `/api/auth/logout` | Redis refresh 삭제, 클라이언트가 access 폐기 |
| GET  | `/api/auth/me` | 현재 토큰의 subject 정보 |
| GET  | `/api/user/oauth/google/callback` | OAuth2 성공 후 JWT 발급 + user-web 302 |

### 4. 저장소

| 대상 | 저장 위치 | 비고 |
|---|---|---|
| 관리자 | **신규 `id_admin`** (BCrypt) | In-Memory 폐기 |
| 슈퍼관리자 | `SUPER_ADMIN_EMAILS` env → bootstrap 시 `id_admin` 에 upsert | `rainend00@gmail.com` 등 |
| 수강생 | **기존 `TB_MA_MEMBER`** (하이브리드 원칙, ADR-005 참고) | 비번 컬럼에 BCrypt hash 저장 |

### 5. Bean 충돌 방지

- `@Service("adminAuthService")`, `@Service("userAuthService")` 로 명시
- `AdminAuthApi` (`/api/admin-auth`) 와 `UserAuthApi` (`/api/user-auth`) 모두 `/api/auth/**` 공통 외에 role 별 관리 엔드포인트 소유

## 결과

- 관리자·수강생이 하나의 인증 프레임에서 분리된 필터로 관리됨
- Redis refresh store 로 로그아웃·강제 무효화 가능
- JWT claims 만으로 role 식별 → Controller 는 `@AuthenticationPrincipal` 에 집중
- 30분 access + 자동 refresh 로 세션 단절 체감 없음
- Sprint 1-1 작업 범위 확정

## 리스크

| 리스크 | 완화 |
|---|---|
| JWT secret 유출 시 전체 토큰 위조 | env 에서만 로드 (`.env` gitignore), 정기 rotate + 모든 refresh 무효화 scripted |
| Redis 다운 시 refresh 불가 → access 만료 후 전면 재로그인 | compose 에 healthcheck + 운영은 redis HA (후순위) |
| 관리자 In-Memory 제거로 설정 한 번 실수 시 모든 admin 로그인 불가 | bootstrap 시 `SUPER_ADMIN_EMAILS` 로 최소 1명 upsert 보장 |
| 두 필터가 같은 요청을 중복 검증 | URL prefix 기반 `shouldNotFilter` 로 한 쪽만 통과 |
| 기존 admin-web 의 세션 쿠키 로그인 흐름이 한 번에 깨짐 | Sprint 1-1a 에 admin-web axios interceptor 를 JWT 헤더로 동시 전환 |

## 후속 결정

- Refresh 회전(rotation) 정책: 매 refresh 마다 새 refresh 발급 vs 만료까지 재사용 → Sprint 1-1b 구현 시 결정
- OAuth 로 가입된 계정의 비밀번호 null 처리 규칙 → Sprint 1-3 signup 이관 시
- Bearer 외 HttpOnly Cookie 전달 방식 채택 여부 (XSS 방어 관점) → 후순위
