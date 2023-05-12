<script setup>
import { UploadFilled } from '@element-plus/icons-vue'
import { ElMessage } from 'element-plus'
import { ref,reactive } from 'vue'
import { SHA256 } from 'crypto-js';
import axios from 'axios'
import apiConfig from '../config/apiConfig'

const { identity, mapping } = apiConfig;
const user = ref("Ru")

const fileFrom = reactive({
    fileName: "",
    fileBase64: "",
    fileType: "",
    uploadName: "",
    fileSha: "",
})

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
    if(res.status){
        ElMessage.success("上传成功");
        url.value = res.data.content.download_url
    }else{
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
</script>

<template>
    <div>
        <div>
            <input type="file" @change="handleFileChange">
            {{ url }}
        </div>
    </div>
</template>

