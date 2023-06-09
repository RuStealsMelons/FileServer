import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  // base: '/file/',
  plugins: [vue()],
  // base:'/file/',
  server: {
    https: false, // 是否开启 https
    open: false, // 是否自动在浏览器打开
    cors: true, // 允许跨域
    port: 10241, // 端口号
    host: "0.0.0.0",
    // proxy: {
    //   "/api": {
    //     target: "http://localhost:10240", // 后台接口
    //     changeOrigin: true,
    //     secure: false, // 如果是https接口，需要配置这个参数
    //     // ws: true, //websocket支持
    //     rewrite: (path) => path.replace(/^\/api/, ""),
    //   },
    // },
  }
})
