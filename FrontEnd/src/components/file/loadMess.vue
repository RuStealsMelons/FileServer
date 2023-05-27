<script setup>
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
const { uploadMess } = defineProps(['uploadMess'])
const imgDialog = ref(false)

const copy = () => {
    const input = document.createElement('input');
    // input.style.display = 'none';
    input.setAttribute('value', uploadMess.content.download_url);
    document.body.appendChild(input);
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);
    ElMessage.success("复制成功！")
}
</script>

<template>
    <div class="box">
        <div class="page">

            <div>文件大小：{{ uploadMess.content.size }}</div>
            <div>文件服务器：https://gitee.com/KeepHeartbeat/file/raw/master/</div>
            <div>文件路径：<el-link type="primary" target="_blank" :href="uploadMess.content.download_url">{{
                uploadMess.content.path }}</el-link></div>
            <div>SHA结果：{{ uploadMess.content.sha }}</div>
            <!-- <div>文件地址：<el-link type="primary"  :href="uploadMess.content.download_url"   style="font-size: 12px;">{{ uploadMess.content.download_url }}</el-link></div> -->
            <div>提交信息：{{ uploadMess.commit.message }}</div>
            <div style="display: flex; justify-content: space-evenly;">
                <el-button @click="imgDialog = true">预览图片</el-button>
                <el-button @click="copy">复制链接</el-button>
            </div>
        </div>
    </div>

    <el-dialog v-model="imgDialog" title="图片预览">
        <el-image :src="uploadMess.content.download_url" />
    </el-dialog>

</template>
  
<style scoped>
.box {
    background-color: pink;
    width: 700px;
    height: 300px;
    display: flex;
    justify-content: center;
    align-items: center;
    animation: fadeInDown 1s forwards;
}

@keyframes fadeInDown {
    from {
        opacity: 0;
        transform: translateY(-50px);
    }

    to {
        opacity: 1;
        transform: none;
    }
}

.page {
    position: relative;
    width: 95%;
    height: 90%;
    background: linear-gradient(225deg,
            transparent 35px,
            sienna 35px) no-repeat top right/50px 50px,
        linear-gradient(225deg, transparent 35px, bisque 35px);
    padding-left: 10px;
    white-space: nowrap;
    display: flex;
    flex-direction: column;
    justify-content: space-around;
}
</style>