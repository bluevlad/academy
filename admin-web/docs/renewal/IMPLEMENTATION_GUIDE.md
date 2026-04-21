# 초기 구현 가이드

> Phase 1 인프라 현대화 단계의 상세 구현 방법을 기술한다.

---

## 1. CRA → Vite 전환

### 1.1 왜 Vite인가
- CRA(react-scripts)는 2023년부터 사실상 유지보수 중단
- Vite는 ESBuild 기반 사전 번들링으로 HMR 0.5초 이내 반영
- 현재 프로젝트의 `config-overrides.js`(source-map 무시)는 Vite에서 기본 지원

### 1.2 전환 절차

#### Step 1: 패키지 교체
```bash
# 제거
npm uninstall react-scripts react-app-rewired

# 설치
npm install -D vite @vitejs/plugin-react
```

#### Step 2: vite.config.ts 생성
```typescript
import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: {
      // jsconfig.json의 baseUrl: "src" 대체
      '@': path.resolve(__dirname, './src'),
    },
  },
  server: {
    port: 3000,
    host: true, // Docker 환경 대응
    watch: {
      usePolling: true, // Windows/Docker 호환 (CHOKIDAR_USEPOLLING 대체)
    },
  },
  build: {
    sourcemap: false, // GENERATE_SOURCEMAP=false 대체
  },
});
```

#### Step 3: index.html 이동
```
public/index.html → ./index.html (프로젝트 루트)
```
- `%PUBLIC_URL%` 참조를 `/`로 변경
- `<script type="module" src="/src/index.js"></script>` 추가

#### Step 4: 환경변수 접두사 변경
```
REACT_APP_*  →  VITE_*
```
- 코드 내 `process.env.REACT_APP_*` → `import.meta.env.VITE_*`

#### Step 5: package.json 스크립트 변경
```json
{
  "scripts": {
    "dev": "vite",
    "build": "vite build",
    "preview": "vite preview"
  }
}
```

#### Step 6: config-overrides.js 제거
- Vite에서는 불필요 (source-map 설정은 vite.config.ts에서 처리)

### 1.3 주의사항
- `jsconfig.json`의 경로 alias → `vite.config.ts`의 `resolve.alias`로 이전
- CRA 전용 환경변수 (`ESLINT_NO_DEV_ERRORS`, `DISABLE_ESLINT_PLUGIN`) 제거
- `react-app-rewired` 관련 코드 전량 제거

### 1.4 Docker 연동
```dockerfile
# Dockerfile 수정
FROM node:18-alpine
WORKDIR /app
COPY package.json package-lock.json ./
RUN npm install --legacy-peer-deps
COPY . .
EXPOSE 3000
CMD ["npm", "run", "dev"]
```

---

## 2. TypeScript 점진 도입

### 2.1 설정 전략
`strict: false`로 시작하여 기존 JS 파일과 공존시킨다.

#### tsconfig.json
```json
{
  "compilerOptions": {
    "target": "ES2020",
    "lib": ["ES2020", "DOM", "DOM.Iterable"],
    "module": "ESNext",
    "moduleResolution": "bundler",
    "jsx": "react-jsx",
    "strict": false,
    "allowJs": true,
    "checkJs": false,
    "noEmit": true,
    "isolatedModules": true,
    "esModuleInterop": true,
    "skipLibCheck": true,
    "forceConsistentCasingInFileNames": true,
    "resolveJsonModule": true,
    "baseUrl": "src",
    "paths": {
      "@/*": ["./*"]
    }
  },
  "include": ["src"],
  "exclude": ["node_modules"]
}
```

### 2.2 적용 순서
1. **API 응답 타입** 먼저 정의 (백엔드 VO 기반)
2. **공용 유틸/훅** 타입 전환
3. **신규 작성 파일**은 `.tsx`로 생성
4. **기존 파일 수정 시** `.js` → `.tsx` 전환

### 2.3 백엔드 API 응답 타입 예시
```typescript
// src/types/api.ts
export interface ApiResponse<T> {
  retMsg: 'OK' | 'FAIL' | 'DUPLICATE';
  message?: string;
  data?: T;
  list?: T[];
  totalCount?: number;
  paginationInfo?: PaginationInfo;
}

export interface PaginationInfo {
  currentPageNo: number;
  totalPageCount: number;
  totalRecordCount: number;
  recordCountPerPage: number;
}

// src/features/board/types.ts
export interface BoardVO {
  boardSeq: number;
  boardTitle: string;
  boardContent: string;
  boardCatCd: string;
  openYn: 'Y' | 'N';
  mainYn: 'Y' | 'N';
  hits: number;
  regId: string;
  regDt: string;
  modId?: string;
  modDt?: string;
}

export interface BoardListParams {
  currentPageNo: number;
  recordCountPerPage: number;
  searchType?: string;
  searchValue?: string;
  boardCatCd?: string;
}
```

---

## 3. HTTP 클라이언트 통합

### 3.1 현재 문제
- `src/api/` 하위 16개 모듈이 각각 Superagent를 직접 import
- `src/utils/request.js`에 Axios 인스턴스가 별도 존재
- 인터셉터(토큰 갱신, 헤더 주입)가 Axios에만 구현 → Superagent 호출은 미적용

### 3.2 통합 방향
```
[Before]
API 모듈 → Superagent (직접 호출, 인터셉터 없음)
일부 코드 → Axios (인터셉터 있음)

[After]
API 모듈 → 커스텀 훅 → Axios 인스턴스 (인터셉터 통합) → React Query
```

### 3.3 Axios 인스턴스 정리
```typescript
// src/shared/api/client.ts
import axios from 'axios';

const BASE_API = import.meta.env.VITE_API_URL || 'http://localhost:9001/api';

export const apiClient = axios.create({
  baseURL: BASE_API,
  timeout: 30000,
  headers: { 'Content-Type': 'application/json' },
});

// 요청 인터셉터
apiClient.interceptors.request.use((config) => {
  const token = sessionStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  // 기존 headersConfig.js의 헤더들 통합
  config.headers['X-User-Id'] = localStorage.getItem('x-user-id') || '';
  config.headers['X-Company-Code'] = localStorage.getItem('x-company-code') || '';
  config.headers['X-User-Role'] = localStorage.getItem('x-user-role') || '';
  config.headers['X-Timezone'] = Intl.DateTimeFormat().resolvedOptions().timeZone;
  return config;
});

// 응답 인터셉터 (토큰 갱신)
apiClient.interceptors.response.use(
  (response) => response,
  async (error) => {
    // 기존 request.js의 토큰 갱신 로직 이전
    if (error.response?.status === 401) {
      // refresh token 처리
    }
    return Promise.reject(error);
  }
);
```

### 3.4 API 커스텀 훅 패턴
```typescript
// src/features/board/api.ts
import { apiClient } from '@/shared/api/client';
import type { ApiResponse, BoardVO, BoardListParams } from './types';

export const boardApi = {
  getList: (params: BoardListParams) =>
    apiClient.get<ApiResponse<BoardVO[]>>('/board/getBoardList', { params }),

  getDetail: (boardSeq: number) =>
    apiClient.get<ApiResponse<BoardVO>>('/board/getBoardDetail', { params: { boardSeq } }),

  create: (data: Partial<BoardVO>) =>
    apiClient.post<ApiResponse<null>>('/board/insertBoard', data),

  update: (data: Partial<BoardVO>) =>
    apiClient.post<ApiResponse<null>>('/board/updateBoard', data),

  delete: (boardSeq: number) =>
    apiClient.post<ApiResponse<null>>('/board/deleteBoard', { boardSeq }),
};

// src/features/board/hooks.ts
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { boardApi } from './api';

export const useBoardList = (params: BoardListParams) =>
  useQuery({
    queryKey: ['board', 'list', params],
    queryFn: () => boardApi.getList(params).then((res) => res.data),
  });

export const useBoardDetail = (boardSeq: number) =>
  useQuery({
    queryKey: ['board', 'detail', boardSeq],
    queryFn: () => boardApi.getDetail(boardSeq).then((res) => res.data),
    enabled: !!boardSeq,
  });

export const useBoardCreate = () => {
  const queryClient = useQueryClient();
  return useMutation({
    mutationFn: boardApi.create,
    onSuccess: () => queryClient.invalidateQueries({ queryKey: ['board', 'list'] }),
  });
};
```

### 3.5 마이그레이션 순서
1. `src/shared/api/client.ts` 생성 (기존 `request.js` + `headersConfig.js` 통합)
2. feature 모듈 하나(board)를 먼저 전환하여 패턴 검증
3. 나머지 모듈 순차 전환 (lecture → member → order → ...)
4. 전체 전환 완료 후 `superagent` 패키지 제거

---

## 4. 상태관리 정리

### 4.1 MobX 제거
```bash
npm uninstall mobx mobx-react
```
- `src/stores/index.js`의 14개 빈 Store 클래스 삭제
- `App.js`의 `MobXProvider` 래핑 제거

### 4.2 Zustand 도입
```bash
npm install zustand
```

```typescript
// src/shared/stores/useUIStore.ts
import { create } from 'zustand';

interface UIState {
  miniSidenav: boolean;
  darkMode: boolean;
  sidenavColor: string;
  fixedNavbar: boolean;
  layout: string;
  direction: 'ltr' | 'rtl';
  toggleSidenav: () => void;
  setDarkMode: (value: boolean) => void;
  setSidenavColor: (color: string) => void;
  setFixedNavbar: (value: boolean) => void;
  setLayout: (layout: string) => void;
  setDirection: (dir: 'ltr' | 'rtl') => void;
}

export const useUIStore = create<UIState>((set) => ({
  miniSidenav: false,
  darkMode: false,
  sidenavColor: 'info',
  fixedNavbar: true,
  layout: 'dashboard',
  direction: 'ltr',
  toggleSidenav: () => set((state) => ({ miniSidenav: !state.miniSidenav })),
  setDarkMode: (value) => set({ darkMode: value }),
  setSidenavColor: (color) => set({ sidenavColor: color }),
  setFixedNavbar: (value) => set({ fixedNavbar: value }),
  setLayout: (layout) => set({ layout }),
  setDirection: (dir) => set({ direction: dir }),
}));
```

```typescript
// src/shared/stores/useAuthStore.ts
import { create } from 'zustand';

interface AuthState {
  token: string | null;
  user: UserProfile | null;
  isAuthenticated: boolean;
  setAuth: (token: string, user: UserProfile) => void;
  clearAuth: () => void;
}

export const useAuthStore = create<AuthState>((set) => ({
  token: sessionStorage.getItem('token'),
  user: JSON.parse(sessionStorage.getItem('userProfile') || 'null'),
  isAuthenticated: !!sessionStorage.getItem('token'),
  setAuth: (token, user) => {
    sessionStorage.setItem('token', token);
    sessionStorage.setItem('userProfile', JSON.stringify(user));
    set({ token, user, isAuthenticated: true });
  },
  clearAuth: () => {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('userProfile');
    set({ token: null, user: null, isAuthenticated: false });
  },
}));
```

### 4.3 전환 순서
1. Zustand Store 생성
2. 기존 `MaterialUIController` Context를 사용하는 컴포넌트를 점진적으로 Zustand로 전환
3. 모든 참조 전환 후 Context 제거
4. MobX 패키지 및 코드 삭제

---

## 5. 라우팅 개선 (lazy loading)

### 5.1 현재 구조의 문제
```javascript
// 현재 routes.js - 모든 컴포넌트를 즉시 import
import Dashboard from "layouts/dashboard";
import Board from "layouts/board";
import Lecture from "layouts/lecture/online";
// ... 20개+ import → 초기 번들에 모두 포함
```

### 5.2 개선 방향
```typescript
// src/app/routes.tsx
import { lazy, Suspense } from 'react';
import { createBrowserRouter } from 'react-router-dom';
import DashboardLayout from '@/shared/components/Layout/DashboardLayout';
import Loading from '@/shared/components/Feedback/Loading';

const Dashboard = lazy(() => import('@/features/dashboard/pages/Dashboard'));
const BoardList = lazy(() => import('@/features/board/pages/BoardList'));
const BoardDetail = lazy(() => import('@/features/board/pages/BoardDetail'));
const LectureList = lazy(() => import('@/features/lecture/pages/LectureList'));

export const router = createBrowserRouter([
  {
    path: '/',
    element: <DashboardLayout />,
    children: [
      { index: true, element: <Dashboard /> },
      {
        path: 'board',
        children: [
          { index: true, element: <BoardList /> },
          { path: ':id', element: <BoardDetail /> },
          { path: 'management', element: <BoardManagement /> },
        ],
      },
      {
        path: 'lecture',
        children: [
          { path: 'online', element: <LectureList /> },
          { path: 'online/:id', element: <LectureDetail /> },
          { path: 'offline', element: <OfflineLectureList /> },
        ],
      },
      // ... 나머지 feature routes
    ],
  },
  {
    path: '/authentication',
    children: [
      { path: 'sign-in', element: <SignIn /> },
      { path: 'sign-up', element: <SignUp /> },
    ],
  },
]);
```

---

## 6. 검증 체크리스트

각 단계 완료 시 아래 항목을 확인한다:

### Phase 1 완료 검증
- [ ] `npm run dev`로 Vite 개발 서버 정상 기동
- [ ] `npm run build`로 프로덕션 빌드 성공
- [ ] Docker 컨테이너에서 정상 동작
- [ ] 기존 모든 페이지 접근 가능
- [ ] 로그인/로그아웃 정상 동작
- [ ] API 호출 및 응답 정상 (인터셉터 포함)
- [ ] 토큰 갱신 정상 동작
- [ ] 환경변수 정상 주입 (VITE_*)
