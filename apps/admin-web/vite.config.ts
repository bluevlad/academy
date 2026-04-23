import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'node:path';

export default defineConfig({
  // 게이트웨이에서 /admin/ prefix 를 strip 해 컨테이너에 `/` 로 도착하지만,
  // 브라우저 URL 은 여전히 /admin/ 이므로 번들이 생성하는 asset 경로도 /admin/ 기준이어야
  // 게이트웨이가 다시 admin-web 으로 라우팅한다. base 는 곧 publicPath.
  base: '/admin/',
  plugins: [react()],
  server: {
    port: 4001,
    host: true,
    proxy: {
      '/api': {
        target: 'http://localhost:9001',
        changeOrigin: true,
      },
    },
  },
  resolve: {
    alias: {
      '@': path.resolve(__dirname, 'src'),
    },
  },
  build: {
    outDir: 'dist',
    sourcemap: false,
    target: 'es2022',
  },
});
