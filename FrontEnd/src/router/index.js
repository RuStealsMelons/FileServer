import { createRouter, createWebHistory } from "vue-router";

import login from '../views/login.vue'
import index from '../views/index.vue'
import file from '../views/file.vue'
import formatter from '../views/formatter.vue'
import jsonFormatter from '../components/jsonFormatter.vue'
// jsonFormatter
const routes = [
  {
    path: "/",
    redirect: "/index",
  },
  {
    path: "/index",
    name: "index",
    component: index,
    redirect: "/index/file",
    children: [
      {
        path: '/index/file',
        component: file,
      },
      {
        path: '/index/formatter',
        component: formatter,
        children: [
          {
            path: '/index/formatter/json',
            component: jsonFormatter,
          }
        ]
      },
    ]
  },
  {
    path: "/login",
    name: "login",
    component: login,
  }
];

export const router = createRouter({
  history: createWebHistory(),
  routes: routes,
});

// 注册一个全局前置守卫
// router.beforeEach((to, from, next) => {
//   if (to.path == "/login" || to.path == "/register") {
//     next();
//   } else {
//     if (localStorage.getItem("user") != null) {
//       next();
//     } else {
//       router.replace("/login");
//     }
//   }
// });
