<script setup>
import { Picture as IconPicture, View as IconView, Download, Delete, DocumentCopy } from '@element-plus/icons-vue'
import {ref, toRefs,defineEmits } from "vue";
import axios from "axios";

const cards = ref([])

const loading = ref(false)

const dialog = ref(true)
const dialogUrl = ref("")

const props = defineProps({
    //子组件接收父组件传递过来的值
    fileType: String,
})

const {fileType} = toRefs(props)

const reload = async () => {
    loading.value = true
    const { data } = await axios.get(`${fileType.value}/list`);
    cards.value = data
    loading.value = false
}

const formatDate = (str) => {
    const d = new Date(str);
    return d.getFullYear()+ "/" + (d.getMonth()+1) + "/" + d.getDate();
}


const emit = defineEmits(['preview'])
const preview = (img) => {
    dialogUrl.value = previewUrl(img.filepath);
    dialog.value = true;
    emit('clickChild',dialogUrl.value)
}

const previewUrl = (url) => {
    return window._BaseUrl + url.replace("##", "preview")
}
const downloadUrl = (url) => {
    return window._BaseUrl + url.replace("##", "download")
}
reload()

</script>

<!---->
<template>
    <el-timeline v-loading="loading" v-if="cards.length !== 0">
        <el-timeline-item :timestamp="formatDate(img.createtime)" :key="i" v-for="(img,i) in cards" placement="top">
            <el-card>
                <div id="card" >
                    <div id="img">
                      <el-image :src="previewUrl(img.filepath)" >
                          <template #error>
                              <div>
                                  <el-icon><icon-picture /></el-icon>
                              </div>
                          </template>
                      </el-image>
                    </div>
                    <div id="context">
                        <el-link @click="preview(img)">
                            预览<el-icon ><IconView /></el-icon>
                        </el-link>
                        <el-link :href="downloadUrl(img.filepath)">
                            下载<el-icon><Download /></el-icon>
                        </el-link>
                        <el-link href="#">
                            删除<el-icon><Delete /></el-icon>
                        </el-link>
                        <el-link href="#">
                            复制<el-icon><DocumentCopy /></el-icon>
                        </el-link>
                    </div>
                </div>
            </el-card>
        </el-timeline-item>
    </el-timeline>
    <el-empty v-loading="loading" v-if="cards.length === 0" :image-size="200" />

<!--    <el-dialog v-model="dialog" style="height: 100px;width: 100px">-->
<!--        <el-image :src="dialogUrl" ></el-image>-->
<!--    </el-dialog>-->

</template>

<style scoped>
#card{
    height: 200px;
    display: flex;
    justify-content: space-around;
}
#img{
    height: 200px;
    width: 150px;
    display: flex;
    justify-content: center;
    align-items: center;
}
#context{
    width: 530px;
    height: 200px;
    border: #333333 1px solid;
}
.el-link {
    margin-right: 8px;
}
</style>