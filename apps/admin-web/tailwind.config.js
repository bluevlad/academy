/** @type {import('tailwindcss').Config} */
export default {
  content: [
    './index.html',
    './src/**/*.{ts,tsx}',
    '../../packages/ui-core/src/**/*.{ts,tsx}',
  ],
  corePlugins: {
    // Ant Design 과 preflight 충돌 방지
    preflight: false,
  },
  theme: {
    extend: {
      colors: {
        primary: '#3b82f6',
      },
    },
  },
  plugins: [],
};
