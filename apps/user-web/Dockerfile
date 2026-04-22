# syntax=docker/dockerfile:1.7
FROM node:20-alpine AS build
WORKDIR /repo

COPY package.json package-lock.json* ./
COPY packages/ui-core/package.json packages/ui-core/
COPY apps/user-web/package.json    apps/user-web/

RUN npm install --workspaces --include-workspace-root

COPY packages/ui-core packages/ui-core
COPY apps/user-web    apps/user-web
ARG VITE_API_BASE_URL=/api
ENV VITE_API_BASE_URL=$VITE_API_BASE_URL
RUN npm -w @academy/user-web run build

FROM nginx:1.27-alpine
COPY apps/user-web/nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=build /repo/apps/user-web/dist /usr/share/nginx/html
EXPOSE 80
