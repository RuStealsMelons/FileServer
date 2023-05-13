<template>
    <div style="display: flex; box-sizing: border-box; padding: 20px 10px;">
        <div style="width: 47%;">
            <el-input v-model="jsonStr" type="textarea" rows="21" placeholder="{}" />
        </div>
        <div style="width: 6%; display: flex; justify-content: center; align-items: center;">
            <el-button type="primary" @click="todo">
                <el-icon>
                    <ArrowRight />
                </el-icon>
            </el-button>
        </div>
        <div style="width: 47%; overflow: auto; background-color: #f7f8f9; border-radius: 5px;">
            <vue-json-tree v-if="conf.doing == '格式化'" :data="res.ob" :options="jsonOptions"></vue-json-tree>
            <el-input disabled v-if="conf.doing == '压缩'" v-model="res.str" type="textarea" rows="21" />
        </div>
    </div>
    <div>
        严格模式:
        <el-switch v-model="conf.useScript" inline-prompt size="large" active-text="开" inactive-text="关" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        功能选择:
        <el-radio-group v-model="conf.doing" size="default">
            <el-radio-button label="格式化" />
            <el-radio-button label="压缩" />
        </el-radio-group>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <el-button type="primary" @click="copy">复制结果</el-button>
    </div>
</template>

<script setup>
import { ref, reactive, h } from 'vue'
import VueJsonTree from 'vue-json-tree'
import { ArrowRight } from '@element-plus/icons-vue'
import { ElMessage, ElNotification } from 'element-plus'

const conf = reactive({
    useScript: true,
    doing: "格式化"
})

const jsonStr = ref(`{"title":"json在线解析（简版） -JSON在线解析","json.url":"https://www.sojson.com/simple_json.html","keywords":"json在线解析","功能":["JSON美化","JSON数据类型显示","JSON数组显示角标","高亮显示","错误提示",{"备注":["www.sojson.com","json.la"]}],"加入我们":{"qq群":"259217951"}}`)

const res = reactive({ ob: {}, str: "" })

const jsonData = () => {
    if (conf.useScript) {
        return JSON.parse(jsonStr.value);
    } else {
        return eval('(' + jsonStr.value + ')')
    }
}

const todo = () => {
    try {
        if (conf.doing == "格式化") {
            res.ob = jsonData();
            res.str = JSON.stringify(res.ob, "\n", 4);
        } else {
            res.ob = jsonData();
            res.str = JSON.stringify(res.ob).replace(/\s/g, '');
        }
    } catch (err) {
        ElNotification({
            title: '错误',
            message: err,
        })
    }
}

const copy = () => {
    const input = document.createElement('input');
    input.setAttribute('value', res.str);
    document.body.appendChild(input);
    input.select();
    document.execCommand('copy');
    document.body.removeChild(input);
    ElMessage.success("复制成功！")
}

// 创建一个响应式的 jsonOptions 对象
const jsonOptions = ref({})


</script>