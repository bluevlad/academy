# academy-integrated — 개발 가이드 (Claude Code 용)

> 3-머신 작업 환경(MacBook 편집·운영 / Desktop 터미널·AutoQA / Notebook TIPAIP2 격리) 규칙: [WORKSTATION_GUIDE.md](https://github.com/bluevlad/Ai-Legacy-bluevlad/blob/main/infrastructure/environments/WORKSTATION_GUIDE.md) — 개인 서비스 편집은 MacBook 에서만, Desktop 은 pull-only

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
  (Ai-Legacy-bluevlad ENVIRONMENT_STANDARD §5.2 표준 — academy.unmong.com 게이트웨이 대응)
- dev DB: `jdbc:mariadb://localhost:3306/acm_basic` (운영은 172.30.1.72 또는 172.30.1.78)

## Sprint 현황

- **Sprint 0** ✅ monorepo 생성 · 통합 pom · docker-compose · Flyway baseline · shared/user 패키지 skeleton
- **Sprint 1** 🔜 academy-user 22 모듈 → `com.academy.user.*` 로 흡수 (HttpServletRequest → JWT 전환)
- **Sprint 2** 🔜 user-web CRA → Vite + MUI v6 현대화
- **Sprint 3~6** 🔜 MVP 11기능 완성 (`Ai-Legacy-bluevlad/services/academy/SPRINTS.md` 참고)

## 문서 위치

프로젝트 설계·운영 문서는 모두 **메타 repo** 로 이관되었습니다:
`/Users/rainend/GIT/Ai-Legacy-bluevlad/services/academy/`
(ADR / SPRINTS / DEPLOYMENT_CHECKLIST / CI_CD_SETUP / api/ / db/).
`./docs/` 폴더에는 포인터 [`README.md`](docs/README.md) 만 있습니다.

## 브랜치 전략 & 배포 (표준 프로세스)

> **상세**: [`docs/workflow/branch-strategy.md`](docs/workflow/branch-strategy.md)

- **브랜치**: `main` (검증 stable, PR 만) · `prod` (배포 트리거, FF only) · `feat/*`·`fix/*`·`chore/*` (작업)
- **머지**: feature → main 은 `squash`, main → prod 는 `--ff-only` 강제
- **CI**: `ci-main.yml` (PR/push 시 backend `mvn test` + frontend `typecheck`/`build`)
- **CD**: `deploy-prod.yml` (prod push 시 docker 재빌드)

### "prod push" 요청 시 Claude 가 수행할 5단계

1. **사전 검증** — 현재 브랜치가 feature 인지, 작업 트리 clean, 미푸시 커밋 존재 확인
2. **PR 생성** — `git push -u origin <branch>` → `gh pr create --base main`
3. **PR CI 통과 대기** — `gh pr checks --watch`. 실패 시 즉시 중단, prod push 금지
4. **squash merge** — `gh pr merge <#> --squash --delete-branch`
5. **main → prod 승격** — `git merge --ff-only origin/main` → `git push origin prod` → deploy watch
6. **로컬 정리** — `git branch -D <branch>` + `git remote prune origin`

⚠️ **prod 직접 push 금지** — 반드시 main 경유. 단계 실패 시 사용자에게 보고 후 중단.

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
