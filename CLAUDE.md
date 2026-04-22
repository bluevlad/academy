# academy-integrated — 개발 가이드 (Claude Code 용)

## 목표
기존 academy-admin (Spring Boot + MyBatis + React/Vite) 를 **base** 로 삼고,
academy-user 의 수강생 기능을 **user 모듈** 로 흡수하여 단일 배포 가능한 통합 학원 운영 시스템을 구축.

## 핵심 원칙

1. **admin 은 기존 구조 유지** — `com.academy.*` 루트 아래의 flat 모듈(board/book/exam/...) 는 그대로.
2. **user 는 `com.academy.user.*` 로 이관** — academy-user 의 기존 `com.academy.*` 모듈을 `com.academy.user.{board, book, ...}` 로 rename 후 흡수.
3. **공통은 `com.academy.shared.*`** — JWT·OAuth·Response envelope·Pagination 등 양쪽에서 쓰는 코드.
4. **URL prefix 로 경계 강제** — `/api/admin/**`, `/api/user/**`, `/api/shared/**`.
5. **Bean 이름 충돌 금지** — 같은 이름 @Service/@Controller 있으면 반드시 `@Service("adminXxx")`, `@Service("userXxx")` 로 구분.
6. **MyBatis namespace 는 FQN** — `com.academy.admin.board.mapper.BoardMapper` 식으로 완전 명시.
7. **DB 는 MariaDB `acm_basic` 단일** — 기존 운영 DB 그대로. 스키마 변경은 Flyway V2+ 로.

## Do / Don't

**Do**:
- `./mvnw` 로 빌드 (Wrapper 우선). `mvn` 은 환경 의존.
- DTO / Request / Response 는 record + Bean Validation.
- Service 는 `@Transactional(readOnly=true)` 기본, 변경 메서드에만 `@Transactional`.
- 로그는 `log.debug/info/warn/error` — `System.out.println` 금지.

**Don't**:
- `.env`, `.env.local` 커밋 금지.
- `legacy/` 내용 수정 금지 (원본 repo 에서 관리).
- admin → user 역의존 금지 (user 가 admin import 하면 안 됨). 공통이 필요하면 `shared` 로 승격.

## 포트 / 환경

- backend: 9001 / admin-web: 4001 / user-web: 4002 / redis: 6379
  (Claude-Opus-bluevlad ENVIRONMENT_STANDARD §5.2 표준 — academy.unmong.com 게이트웨이 대응)
- dev DB: `jdbc:mariadb://localhost:3306/acm_basic` (운영은 172.30.1.72 또는 172.30.1.78)

## Sprint 현황

- **Sprint 0** ✅ monorepo 생성 · 통합 pom · docker-compose · Flyway baseline · shared/user 패키지 skeleton
- **Sprint 1** 🔜 academy-user 22 모듈 → `com.academy.user.*` 로 흡수 (HttpServletRequest → JWT 전환)
- **Sprint 2** 🔜 user-web CRA → Vite + MUI v6 현대화
- **Sprint 3~6** 🔜 MVP 11기능 완성 (docs/SPRINTS.md 참고)

## 유용 명령

```bash
# 빌드 (test 포함)
cd backend && ./mvnw clean verify

# 빌드 (test skip)
cd backend && ./mvnw clean package -DskipTests

# 단일 테스트
cd backend && ./mvnw test -Dtest=HealthControllerTest

# 로컬 실행
cd backend && ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
