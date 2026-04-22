import type { ThemeConfig } from 'antd';

/**
 * academy 공통 Ant Design 테마 (ADR-007).
 *
 * ConfigProvider 에 주입:
 * ```tsx
 * <ConfigProvider theme={academyTheme} locale={koKR}>
 *   <App />
 * </ConfigProvider>
 * ```
 */
export const academyTheme: ThemeConfig = {
  token: {
    colorPrimary: '#3b82f6',
    borderRadius: 6,
    fontFamily:
      "'Pretendard', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif",
    fontSize: 14,
  },
  components: {
    Layout: {
      headerBg: '#ffffff',
      siderBg: '#1f2937',
      bodyBg: '#f3f4f6',
    },
    Menu: {
      darkItemBg: '#1f2937',
      darkSubMenuItemBg: '#111827',
    },
    Table: {
      headerBg: '#f9fafb',
    },
  },
};

export const BRAND = {
  name: 'academy',
  primaryColor: '#3b82f6',
} as const;
