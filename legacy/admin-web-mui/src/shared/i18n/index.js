/**
 * i18next 설정
 *
 * Usage:
 *   // App.js에서 import
 *   import "shared/i18n";
 *
 *   // 컴포넌트에서 사용
 *   import { useTranslation } from "react-i18next";
 *   const { t } = useTranslation();
 *   <span>{t("common.save")}</span>
 */
import i18n from "i18next";
import { initReactI18next } from "react-i18next";
import ko from "./locales/ko.json";
import en from "./locales/en.json";

i18n.use(initReactI18next).init({
  resources: {
    ko: { translation: ko },
    en: { translation: en },
  },
  lng: "ko",
  fallbackLng: "ko",
  interpolation: {
    escapeValue: false,
  },
});

export default i18n;
