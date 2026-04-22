# syntax=docker/dockerfile:1.7
# Build context 는 monorepo 루트 — workspaces 와 @academy/ui-core 동시 필요
FROM node:20-alpine AS build
WORKDIR /repo

# workspaces 해석을 위해 루트 + 관련 package 정의 복사
COPY package.json package-lock.json* ./
COPY packages/ui-core/package.json packages/ui-core/
COPY apps/admin-web/package.json   apps/admin-web/

RUN npm install --workspaces --include-workspace-root

# 소스 복사 후 admin-web 만 빌드
COPY packages/ui-core packages/ui-core
COPY apps/admin-web   apps/admin-web
ARG VITE_API_BASE_URL=/api
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL
RUN npm -w @academy/admin-web run build

# ===== serve =====
FROM nginx:1.27-alpine
COPY apps/admin-web/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /repo/apps/admin-web/dist /usr/share/nginx/html
EXPOSE 80
