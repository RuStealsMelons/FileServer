<script setup>
import Upload from '../components/upload.vue'
import LoadList from '../components/loadList.vue'
const getImg = (path) => {
    return new URL(path, import.meta.url).href;
}
</script>


<template>
    <el-menu
            :default-active="1"
            mode="horizontal"
            @select="handleSelect"
    >
        <el-image style="width: 300px"  :src="getImg('../assets/image/logo1.png')" fit="contain"/>
        <el-menu-item index="1">Image</el-menu-item>
        <el-sub-menu index="2">
            <template #title>Office</template>
            <el-menu-item index="2-1">Docx</el-menu-item>
            <el-menu-item index="2-2">Excel</el-menu-item>
            <el-menu-item index="2-3">PPT</el-menu-item>
            <el-menu-item index="2-3">PDF</el-menu-item>
        </el-sub-menu>
        <el-menu-item index="3">Markdown</el-menu-item>
    </el-menu>
    <div id="upload-box">
        <div id="upload-w">
            <Upload />
        </div>
    </div>
    <div id="upload-list">
        <div id="upload-w">
            <LoadList />
        </div>
    </div>

</template>

<style scoped>
#upload-box{
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
}
#upload-w{
    width: 700px;
}
#upload-list{
    display: flex;
    justify-content: center;
    align-items: center;
    transform:translateX(-1%);
}
</style>