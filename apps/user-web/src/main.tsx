import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import { ConfigProvider } from 'antd';
import koKR from 'antd/locale/ko_KR';
import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import { academyTheme } from '@academy/ui-core/theme';
import { AuthProvider } from '@academy/ui-core/auth';
import { apiClient } from './api/client';
import { App } from './App';
import './styles/index.css';

const queryClient = new QueryClient({
  defaultOptions: {
    queries: {
      retry: 1,
      refetchOnWindowFocus: false,
      staleTime: 30_000,
    },
  },
});

createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ConfigProvider theme={academyTheme} locale={koKR}>
      <QueryClientProvider client={queryClient}>
        <BrowserRouter>
          <AuthProvider audience="user" apiClient={apiClient}>
            <App />
          </AuthProvider>
        </BrowserRouter>
      </QueryClientProvider>
    </ConfigProvider>
  </React.StrictMode>,
);
