<script setup>
import Upload from '../components/upload.vue'
import LoadList from '../components/loadList.vue'
import { ref } from "vue";

const dialog = ref(false)
const nav = ref("image")

const getImg = (path) => {
    return new URL(path, import.meta.url).href;
}

const result = ref("")
const clickEven = (val) => {
    console.log(val);
    result.value = val
    if (result.value) {
        dialog.value = true
    }
}
</script>


<template>
    <el-menu :default-active="1" mode="horizontal" @select="handleSelect">
        <el-image style="width: 300px" :src="getImg('../assets/image/logo1.png')" fit="contain" />
        <el-menu-item index="1">text</el-menu-item>
        <el-menu-item index="2">office</el-menu-item>
        <el-menu-item index="3">image</el-menu-item>
        <el-menu-item index="4">audio</el-menu-item>
        <el-menu-item index="5">video</el-menu-item>
        <el-menu-item index="6">database</el-menu-item>
        <el-menu-item index="7">executable</el-menu-item>
        <el-menu-item index="8">compressed</el-menu-item>
        <el-menu-item index="9">web</el-menu-item>
        <el-menu-item index="10">script</el-menu-item>
        <el-menu-item index="11">other</el-menu-item>
    </el-menu>
    <div id="upload-box">
        <div id="upload-w">
            <Upload />
        </div>
    </div>
    <!-- <div id="upload-list">
        <div id="upload-w">
            <LoadList :fileType="nav" @clickChild="clickEven" />
        </div>
    </div> -->

    <el-dialog v-model="dialog" id="dialog">
        <div style="height: 100%;width:100%">
            <el-image :src="result" style="height: 100%;width: 100%"></el-image>
        </div>
    </el-dialog>
</template>

<style scoped>
#upload-box {
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}

#upload-w {
    width: 700px;
}

#upload-list {
    display: flex;
    justify-content: center;
    align-items: center;
    transform: translateX(-1%);
}

#dialog {
    position: fixed;
    left: 35%;
    top: -12%;
    width: 450px;
    background-color: transparent
}
</style>