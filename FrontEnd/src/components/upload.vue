<script setup>
import { UploadFilled } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'

const uploadResponse = (uploadFile, uploadFiles) => {
    if (uploadFile.response == null || uploadFile.response.errno !== 0){

        return;
    }
    ElMessage.success("上传成功！")
}

const uploadSuccess = (response) => {
    if(response.errno !== 0){
        ElMessage.error(response.message);
        return
    }
    ElMessage.success("上传成功！")
}

const uploadError = (response) => {
    ElMessage.success("网络出现问题！")
}

</script>

<template>
    <el-upload
            drag
            action="http://localhost:10240/file/upload"
            multiple
            :on-success="uploadSuccess"
            :on-error="uploadError"
    >
        <el-icon class="el-icon--upload"><upload-filled /></el-icon>
        <div class="el-upload__text">
            拖拽到此处 或 <em>点击上传</em>
        </div>
        <template #tip>
            <div class="el-upload__tip">
                文件size小于10M
            </div>
        </template>
    </el-upload>
</template>

