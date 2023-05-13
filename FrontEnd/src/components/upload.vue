<script setup>
import { UploadFilled } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { ref, reactive, provide } from 'vue'
import { SHA256 } from 'crypto-js';
import axios from 'axios'
import apiConfig from '../config/apiConfig'

const emit = defineEmits(['upload'])
const { identity, mapping } = apiConfig;
const user = ref("Ru")
const fileInput = ref(null)

const fileFrom = reactive({
    fileName: "",
    fileBase64: "",
    fileType: "",
    uploadName: "",
    fileSha: "",
})

emit('upload', {"start": false})

const url = ref("")

const upload = async () => {
    const utf8Encoder = new TextEncoder();
    fileFrom.uploadName = window.btoa(utf8Encoder.encode(fileFrom.fileName).reduce((prev, curr) => prev + String.fromCharCode(curr), ''));

    fileFrom.uploadName = `${(new Date()).getTime()}${fileFrom.uploadName}.${fileFrom.fileType}`
    let path = apiConfig.mapping[fileFrom.fileType] == undefined ? "other" : apiConfig.mapping[fileFrom.fileType]
    path = `${path}/${user.value}/${fileFrom.uploadName}`

    const uploadUrl = `https://gitee.com/api/v5/repos/${identity.owner}/${identity.repo}/contents/${path}`
    const data = { message: "提交测试", content: fileFrom.fileBase64, branch: identity.branch, sha: fileFrom.fileSha }
    const headers = { 'Content-Type': 'application/json;charset=UTF-8', 'Authorization': `token ${identity.token}` };
    const res = await axios.post(uploadUrl, { ...data }, { headers })
    if (res.status) {
        ElMessage.success("上传成功");
        url.value = res.data.content.download_url
        emit('upload', {"start": true, ...res.data})
    } else {
        ElMessage.error("上传失败");
    }
}

const handleFileChange = (event) => {
    const file = event.target.files[0]
    if (file) {
        fileFrom.fileName = file.name
        fileFrom.fileType = file.name.split(".")[1]
        const reader = new FileReader()
        reader.onload = (e) => {
            fileFrom.fileBase64 = e.target.result.replace(/^data:\w+\/\w+;base64,/, '')
            fileFrom.fileSha = SHA256(reader.result).toString();
            upload()
        }
        reader.readAsDataURL(file)
    }
}

const click = () => {
    console.log(fileInput)
    fileInput.value.click()
}

</script>

<template>
    <div>
        <div>
            <input type="file" ref="fileInput" @change="handleFileChange">

            <div class="clickDiv" @click="click">
                <div class="update-icon">
                    <el-icon class="el-icon--upload" size="150"><upload-filled /></el-icon>
                </div>
                <div  style="height: 25%;  margin: auto;">
                    Click Upload<em> 点击上传</em>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
input[type="file"] {
    position: absolute;
    left: -9999px;
}

.clickDiv {
    height: 200px;
    width: 100%;
    background-color: rgb(255, 255, 255);
    border: 1px dashed rgb(192, 186, 186);
    border-radius: 10px;
    display: flex;
    flex-direction: column;
}
.update-icon{
    height: 75%; 
    display: flex;
    justify-content: center;
    align-items: flex-end;
    color: bisque;
}

</style>