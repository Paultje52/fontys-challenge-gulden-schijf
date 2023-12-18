import { sveltekit } from "@sveltejs/kit/vite";

/** @type {import("vite").UserConfig.Preview} */
const serverConfig = {
  host: "0.0.0.0",
  cors: {
    origin: "https://dev.paultje52.me"
  }
};

/** @type {import("vite").UserConfig} */
const config = {
  plugins: [sveltekit()],
  preview: serverConfig,
  server: serverConfig,
  build: {
    minify: false,
    target: "es2015"
  }
};

export default config;
