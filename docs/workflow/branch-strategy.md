# 브랜치 전략 & 배포 표준 프로세스

> **확정일**: 2026-04-24
> **적용**: academy 모노레포 전체

## 브랜치 모델

| 브랜치 | 역할 | 직접 push | 수정 경로 |
|--------|------|----------|---------|
| `main` | **검증된 stable** — CI 통과 보장 | ❌ (PR 만) | feature 브랜치 → PR → squash merge |
| `prod` | **배포 트리거** — push 시 docker 재빌드 | ❌ (FF only) | `main` → `--ff-only` merge |
| `feat/*`, `fix/*`, `chore/*` | 작업 브랜치 | ✅ | 자유 작업 후 PR |

## 머지 정책

- **feature → main**: `squash merge` (히스토리 정리, 1 커밋 = 1 기능)
- **main → prod**: `--ff-only` 강제 (분기 차단, prod 는 main 의 부분집합 보장)
- **PR 머지 시 source branch 자동 삭제** (repo setting `deleteBranchOnMerge=true`)

## CI/CD 매트릭스

| 트리거 | 워크플로우 | 동작 |
|--------|----------|------|
| PR → `main` | `ci-main.yml` | backend `mvn test` + frontend `typecheck`/`build` |
| push → `main` | `ci-main.yml` | 동일 (머지 후 재검증) |
| push → `prod` | `deploy-prod.yml` | docker compose build & up + health check |

## 표준 프로세스 — "prod push" 요청 시 Claude 가 수행하는 5단계

사용자가 임시 브랜치에서 작업 후 **"prod push"** 를 요청하면, Claude 는 아래 단계를 순차 실행:

### Step 1. 사전 검증
- 현재 브랜치가 `main`/`prod` 가 **아닌** feature 브랜치인지
- 작업 트리 clean (모든 변경 커밋 완료)
- 미푸시 커밋이 1개 이상 존재
- (선택) 사용자에게 변경 요약 보고 후 "prod push 진행" 확인

### Step 2. 작업 브랜치 push + PR 생성
```bash
git push -u origin <branch>
gh pr create --base main --head <branch> \
  --title "<원 커밋 메시지 첫 줄>" \
  --body "$(생성된 변경 요약)"
```

### Step 3. PR CI 통과 대기
- `gh pr checks <PR번호> --watch` 또는 `gh run watch <run-id> --exit-status`
- ❌ 실패 시 즉시 중단, 사용자에게 실패 원인 보고. **prod 절대 push 금지.**

### Step 4. PR squash merge
```bash
gh pr merge <PR번호> --squash --delete-branch
```
- `--delete-branch` 로 remote 브랜치 자동 정리
- main 으로 push 트리거 → `ci-main.yml` 재검증 (잠시 대기)

### Step 5. main → prod 승격 + 배포 대기
```bash
git checkout prod && git pull --ff-only
git merge --ff-only origin/main
git push origin prod          # → deploy-prod.yml 트리거
gh run watch <deploy-id> --exit-status
```
- `--ff-only` 가 거부되면 prod 가 main 과 분기됨 → 즉시 중단, 수동 조사
- deploy 성공 후 `docker ps --filter "name=academy"` 로 컨테이너 상태 확인

### Step 6. 로컬 정리
```bash
git checkout main && git pull
git branch -D <feature-branch>      # 로컬 작업 브랜치 삭제
git remote prune origin             # remote 에서 삭제된 브랜치 참조 정리
```

## 롤백 절차 (prod 사고시)

1. 마지막 정상 prod 커밋 SHA 확인: `git log prod --oneline | head`
2. prod 를 정상 SHA 로 reset:
   ```bash
   git checkout prod
   git reset --hard <good-sha>
   git push --force-with-lease origin prod    # ⚠️ 사용자 승인 필수
   ```
3. main 도 동일 SHA 로 reset 필요시 동일 절차
4. 사고 원인 분석 후 fix 브랜치로 정상 절차 재진행

## 1회성 정비 (셋업 직후)

- [ ] `git push origin prod:main` — main 을 prod head 로 동기화 (FF, 10 커밋 격차 해소)
- [ ] `gh repo edit bluevlad/academy --delete-branch-on-merge` — 자동 삭제 활성화
- [ ] stale 브랜치 검토·삭제: `feat/fe-ant-design-rewrite`, `feat/user-web-vite`
- [ ] `main` 브랜치 보호 규칙 (선택) — Settings → Branches → Require PR + CI pass

## FAQ

**Q. 혼자 작업하는데 PR 까지 만들 필요 있나?**
A. PR 강제는 ① CI 게이트 자동 적용 ② 변경 이력 가독성 ③ 향후 팀 확장 시 자연스러운 전환 — 3가지 이유로 유지. 머지는 `gh pr merge --squash` 한 줄이라 오버헤드 적음.

**Q. main 직접 push 가능?**
A. 기술적으론 가능하지만 표준은 PR. 긴급 hotfix 도 PR (label `hotfix` 만 붙여 빠르게).

**Q. 임시 브랜치 명명 규칙?**
A. `feat/<topic>`, `fix/<topic>`, `chore/<topic>`, `docs/<topic>` 권장. 자유 형식 OK 단 `main`/`prod` 와 충돌 없게.

**Q. "main 빌드 정상" 의 정의?**
A. `ci-main.yml` 의 backend·frontend 두 잡 모두 ✅. 통과 안 된 main 은 prod 로 promote 금지.
