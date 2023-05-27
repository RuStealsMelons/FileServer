import { createRouter, createWebHistory } from "vue-router";

import login from '../views/login.vue'
import index from '../views/index.vue'
import file from '../views/file.vue'
import encrypt from '../views/encrypt.vue'
import formatter from '../views/formatter.vue'
import jsonFormatter from '../components/formatter/jsonFormatter.vue'
import xmlFormatter from '../components/formatter/xmlFormatter.vue'
import DES3 from '../components/encrypt/3DES.vue'
import AES from '../components/encrypt/AES.vue'
import Base64 from '../components/encrypt/Base64.vue'
import DES from '../components/encrypt/DES.vue'
import ECC from '../components/encrypt/ECC.vue'
import HMAC from '../components/encrypt/HMAC.vue'
import MD5 from '../components/encrypt/MD5.vue'
import RSA from '../components/encrypt/RSA.vue'
import SM2 from '../components/encrypt/SM2.vue'
import SM3 from '../components/encrypt/SM3.vue'
import SM4 from '../components/encrypt/SM4.vue'
import UrlEncode from '../components/encrypt/UrlEncode.vue'
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
          },
          {
            path: '/index/formatter/xml',
            component: xmlFormatter,
          }
        ]
      },
      {
        path: '/index/encrypt',
        component: encrypt,
        children: [
          {
            path: '/index/encrypt/3DES',
            component: DES3,
          },
          {
            path: '/index/encrypt/AES',
            component: AES,
          },
          {
            path: '/index/encrypt/Base64',
            component: Base64,
          },
          {
            path: '/index/encrypt/DES',
            component: DES,
          },
          {
            path: '/index/encrypt/ECC',
            component: ECC,
          },
          {
            path: '/index/encrypt/HMAC',
            component: HMAC,
          },
          {
            path: '/index/encrypt/MD5',
            component: MD5,
          },
          {
            path: '/index/encrypt/RSA',
            component: RSA,
          },
          {
            path: '/index/encrypt/SM2',
            component: SM2,
          },
          {
            path: '/index/encrypt/SM3',
            component: SM3,
          },
          {
            path: '/index/encrypt/SM4',
            component: SM4,
          } ,
          {
            path: '/index/encrypt/UrlEncode',
            component: UrlEncode,
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
