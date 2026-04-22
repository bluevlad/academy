# ADR-004 — 트랙 수렴 기준 (academy B vs hopenvision A)

- Status: Accepted · 2026-04-22
- Deciders: owner + Claude Code
- 상위: [ADR-001](./ADR-001-integration-strategy.md) · [IMPLEMENTATION_PLAN_v3](../../../DocumetsToAiPipeLine/docs/integration/IMPLEMENTATION_PLAN_v3.md)

## 문맥

현재 두 트랙 병행:
- **트랙 A**: hopenvision (Spring Boot 3.2 / PostgreSQL / JPA / React 19 + Ant Design) — "최종 단계" 방침으로 유예
- **트랙 B**: academy (Spring Boot 3.2 / MariaDB / MyBatis / MUI v6) — **현재 우선**

`feature/mvp-sprint-0` 브랜치가 hopenvision 에 push 된 상태로 병합 대기 중. Sprint 1+ 작업은 B 에만 집중.

트랙 수렴 기준이 없으면 두 가지 실패 시나리오:
1. MVP B 완성 후 hopenvision 재작성으로 또 수개월 (sunk cost 과소평가)
2. MVP 중간에 A 로 선회 — 이관 작업 절반 폐기

## 결정

**Sprint 6 E2E 검증 종료 시점에 아래 기준으로 트랙을 평가**. 그 전에는 A 로의 선회를 고려하지 않는다.

### Exit Gate — B 고정 조건 (All of)

| 지표 | 목표 | 측정 방법 |
|---|---|---|
| 관리자 6 + 사용자 5 골든 시나리오 | Playwright E2E ≥ 95% 통과 | Sprint 6-1 |
| 주요 조회 p95 | < 300ms (Redis 캐시 활용) | `/actuator/prometheus` + k6 |
| MariaDB 운영 안정성 | 10일간 에러율 < 0.1% / 배포 무중단 | Prod 모니터링 |
| MyBatis mapper 재사용률 | 기존 mapper 80% 이상 그대로 유지 | 코드 통계 |
| 통합 코드 coverage | 단위·통합 ≥ 70% | JaCoCo |
| ArchUnit 위반 | 0 건 | Sprint 6-3 |

**전부 충족** → **B 고정**. hopenvision `feature/mvp-sprint-0` 브랜치는 폐기. academy-user-back-end repo 아카이브.

### Exit Gate — A 재평가 트리거 (Any of)

| 트리거 | 설명 |
|---|---|
| p95 > 500ms · 조회당 쿼리 수 > 20 | MyBatis 기반 성능 한계 시사 |
| 신규 기능 추가 시 기존 `TB_*` 네이밍 부담이 주당 1일 이상 | 정규화 필요성 증대 |
| 팀 JPA 역량이 주류화 | MyBatis 유지 비용 > JPA 전환 비용 |
| 다중 DB (PostgreSQL 신규 요구) 또는 이벤트 소싱 요건 | JPA + pgvector 조합 유리 |

**하나라도 해당** → `ADR-00X hopenvision 수렴 계획` 을 별도로 수립 후 계획적 재평가.

### 재평가 시 원칙

1. 재평가 기간 자체는 **2주 box**. 이후 결정 미룸 금지.
2. 이전 비용 산출은 **하이브리드 신규 테이블(`id_*`·`ct_*` 등)** 만 우선 — `TB_*` 는 Adapter 로 격리해서 병행 운영.
3. admin-web 은 그대로 유지 (Ant Design 재작성 금지), user-web 만 수렴 시점에 맞춰 이전.
4. 데이터 이중 쓰기 기간 필요 시 CDC (Debezium) 검토.

## 결과

- Sprint 1~6 동안 트랙 스위칭 논의를 **중단** — 개발자·owner 모두 집중도 확보
- Sprint 6 종료 후 30분 회고에 위 지표 자동 리포트 제공
- 조건 미충족이 아니면 hopenvision 잔여 작업은 개인 실험용 브랜치로 이관

## 리스크

| 리스크 | 완화 |
|---|---|
| Exit Gate 측정이 Sprint 6 말에야 나옴 — 이미 돈/시간 투입됨 | Sprint 3 말에 Early Signal 확인: p95 > 1s 여부 + mapper 재작성율. 심각 시 ADR-004 업데이트 |
| hopenvision `feature/mvp-sprint-0` 폐기 거부감 | 코드 자산은 docs/legacy 문서로 정리 보존, 재사용 가능 모듈 (JWT 설정 등) 은 academy 로 흡수 |
| 운영 안정성 측정 10일이 짧음 | Sprint 6 중 · 후에 걸쳐 20일로 연장 허용 |

## 의사결정 기록

- 2026-04-21: hopenvision 직접 구현 유예, academy 우선 방침
- 2026-04-22: 본 ADR — Sprint 6 말까지 B 에 집중, Exit Gate 기준 확정

## 후속

- Sprint 6-4 배포 스위칭 체크리스트에 Exit Gate 평가표 포함
- 평가 결과에 따라 `ADR-006 트랙 수렴 결과` 작성
