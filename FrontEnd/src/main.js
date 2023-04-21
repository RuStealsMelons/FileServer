import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import {router} from "./router/index.js";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from "axios";
// http://192.168.10.142/
axios.defaults.baseURL="http://192.168.10.142:10240";

const app = createApp(App)
app.use(router)
app.use(ElementPlus)
app.mount('#app')

