<script setup>

import {ref} from "vue";

const cards = ref([1])

const loading = ref(false)
</script>

<!---->
<template>
    <el-timeline v-loading="loading" v-if="cards.length !== 0">
        <el-timeline-item timestamp="2018/4/12" placement="top">
            <el-card>
                <h4>Update Github template</h4>
                <p>Tom committed 2018/4/12 20:46</p>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/3" placement="top">
            <el-card>
                <h4>Update Github template</h4>
                <p>Tom committed 2018/4/3 20:46</p>
            </el-card>
        </el-timeline-item>
        <el-timeline-item timestamp="2018/4/2" placement="top">
            <el-card>
                <h4>Update Github template</h4>
                <p>Tom committed 2018/4/2 20:46</p>
            </el-card>
        </el-timeline-item>
    </el-timeline>
    <el-empty v-loading="loading" v-if="cards.length === 0" :image-size="200" />
</template>