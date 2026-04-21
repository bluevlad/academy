# Branch Naming Convention

## 브랜치 명명 규칙

### 형식
```
<type>/<description>
```

### 허용되는 Type

| Type | 용도 | 예시 |
|------|------|------|
| `feature/` | 새로운 기능 개발 | `feature/login-page` |
| `fix/` | 버그 수정 | `fix/header-typo` |
| `hotfix/` | 긴급한 서버 오류 수정 | `hotfix/payment-error` |
| `docs/` | 문서 수정 (README 등) | `docs/update-guide` |
| `refactor/` | 코드 리팩토링 | `refactor/cleanup-api` |
| `chore/` | 빌드, 설정 변경 (코드 변경 X) | `chore/update-dependencies` |
| `test/` | 테스트 코드 추가/수정 | `test/add-login-tests` |
| `style/` | 코드 포맷팅, 세미콜론 등 | `style/apply-prettier` |
| `perf/` | 성능 개선 | `perf/optimize-query` |
| `release/` | 릴리스 준비 | `release/v1.2.0` |

### 규칙

1. **소문자만 사용** - 대문자 사용 금지
2. **숫자 허용** - `feature/v2-login`
3. **하이픈(-) 사용** - 단어 구분에 사용
4. **언더스코어(_) 금지** - 하이픈만 사용
5. **슬래시(/) 한 번만** - `type/` 뒤에 추가 슬래시 금지

### 보호 브랜치

아래 브랜치는 직접 push 금지 (PR 필수):
- `master`
- `main`
- `develop`

### 브랜치 생성 예시

```bash
# 새 기능 개발
git checkout -b feature/pagination-admin-auth

# 버그 수정
git checkout -b fix/jwt-token-expire

# 긴급 수정
git checkout -b hotfix/payment-crash

# 문서 수정
git checkout -b docs/api-guide
```

### 브랜치명 변경

```bash
# 현재 브랜치명 변경
git branch -m <new-branch-name>

# 예시
git branch -m feature/new-feature-name
```

## Git Hook

`pre-push` hook이 설정되어 있어 규칙에 맞지 않는 브랜치는 push가 차단됩니다.
