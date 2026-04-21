// Environment-based API configuration
// Values are loaded from .env files:
//   - .env.development (npm start)
//   - .env.production (npm run build)
//   - .env.local (local overrides, git-ignored)

export const BASE_API = process.env.REACT_APP_API_URL || "http://localhost:8080/api";
export const ENV = process.env.REACT_APP_ENV || "development";
export const IS_PRODUCTION = ENV === "production";
export const IS_DEVELOPMENT = ENV === "development";
