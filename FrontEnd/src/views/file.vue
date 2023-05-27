<script setup>
import {reactive} from 'vue'
import Upload from '../components/file/upload.vue'
import LoadMess from '../components/file/loadMess.vue'
const uploadMess = reactive({})

const upload = (mess) => {
    Object.keys(mess).forEach(v=> {
        uploadMess[v] = mess[v]
    })
}

</script>

<template>
    <div id="upload-box">
        <div id="upload-w">
            <Upload @upload="upload"/>
        </div>
    </div>
    <div id="upload-m" v-if="uploadMess.start">
        <LoadMess :uploadMess="uploadMess"/>
    </div>
    
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
#upload-m{
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>