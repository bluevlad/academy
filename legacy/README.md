# legacy/

academy-integrated 로 흡수·재구성되기 전의 원본 repo 들.

**이 디렉토리는 로컬 안전망 사본**이며 git 이력에는 포함되지 않는다 (`.gitignore`).
수정이 필요하면 아래 원본 repo 에서 한다.

## 원본 출처

| 로컬 경로 | 원본 remote | 마지막 알려진 브랜치/커밋 | 흡수 상태 |
|---|---|---|---|
| `legacy/academy-admin.old/backend/` | github.com/bluevlad/academy-admin-back-end-JavaSpring | main · `4051e18` | Sprint 0 에 `backend/` 로 이전 (유지) |
| `legacy/academy-admin.old/frontend/` | github.com/bluevlad/academy-admin-front-end-React | main | Sprint 0 에 `admin-web/` 로 이전 |
| `legacy/academy-user.old/backend/` | github.com/bluevlad/academy-user-back-end-JavaSpring | main | Sprint 1 에 `backend/src/main/java/com/academy/user/*` 로 흡수 예정 |
| `legacy/academy-user.old/frontend/` | github.com/bluevlad/academy-user-front-end-React-material-dashboard | master | Sprint 2 에 `user-web/` Vite 현대화와 함께 재작성 |

## 흡수 진행률

- [x] admin backend → `backend/` (그대로 유지, pom.xml 만 `academy-integrated` 로 교체)
- [x] admin frontend → `admin-web/`
- [x] user frontend → `user-web/` (Vite 현대화는 Sprint 2)
- [ ] user backend → `backend/src/main/java/com/academy/user/*` (Sprint 1)

## 복구/조회 방법

원본 이력 필요 시:
```bash
cd legacy/academy-admin.old/backend
git log --oneline   # .git 포함 사본이라 동작함
```

또는 원본 github 에서 clone.
