<script setup>
import {useRouter} from 'vue-router'
import { ref } from "vue";

const router = useRouter()

const dialog = ref(false)
const nav = ref("image")

const getImg = (path) => {
    return new URL(path, import.meta.url).href;
}

const result = ref("")


</script>


<template>
    <el-menu  default-active="0" mode="horizontal" >
        <el-image style="width: 300px" src="https://gitee.com/KeepHeartbeat/file/raw/master/image/Ru/1685151350658a2VlcGhlYXJ0LnBuZw==.png" fit="contain" />
        <el-menu-item index="0" @click="router.push('/index/file')">文件服务</el-menu-item>
        <el-menu-item index="1" @click="router.push('/index/formatter')">文本格式化</el-menu-item>
        <el-menu-item index="3" @click="router.push('/index/encrypt')">加密/解密</el-menu-item>
    </el-menu>
    <div style="width: 100%;">
        <router-view></router-view>
    </div>
</template>

<style scoped>
</style>