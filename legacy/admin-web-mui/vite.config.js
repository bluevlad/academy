import { defineConfig, transformWithEsbuild } from "vite";
import react from "@vitejs/plugin-react";
import path from "path";

// CRA 호환: .js 파일에서 JSX 문법 허용
function jsxInJsPlugin() {
  return {
    name: "jsx-in-js",
    enforce: "pre",
    async transform(code, id) {
      if (/src\/.*\.js$/.test(id)) {
        return transformWithEsbuild(code, id, {
          loader: "jsx",
          jsx: "automatic",
        });
      }
    },
  };
}

export default defineConfig({
  base: "/admin/",
  plugins: [jsxInJsPlugin(), react()],
  resolve: {
    alias: {
      assets: path.resolve(__dirname, "./src/assets"),
      components: path.resolve(__dirname, "./src/components"),
      context: path.resolve(__dirname, "./src/context"),
      examples: path.resolve(__dirname, "./src/examples"),
      layouts: path.resolve(__dirname, "./src/layouts"),
      stores: path.resolve(__dirname, "./src/stores"),
      api: path.resolve(__dirname, "./src/api"),
      config: path.resolve(__dirname, "./src/config"),
      constants: path.resolve(__dirname, "./src/constants"),
      utils: path.resolve(__dirname, "./src/utils"),
      shared: path.resolve(__dirname, "./src/shared"),
      types: path.resolve(__dirname, "./src/types"),
      App: path.resolve(__dirname, "./src/App.js"),
      routes: path.resolve(__dirname, "./src/routes.js"),
    },
    extensions: [".js", ".jsx", ".ts", ".tsx", ".json"],
  },
  optimizeDeps: {
    esbuildOptions: {
      loader: {
        ".js": "jsx",
      },
    },
  },
  server: {
    port: 3000,
    host: true,
    allowedHosts: ["academy.unmong.com"],
    watch: {
      usePolling: true,
    },
  },
  build: {
    sourcemap: false,
    outDir: "build",
  },
});
