<script setup>
import { reactive, ref, computed } from 'vue'
import {useRouter} from 'vue-router'

const nowActive = ref(null)
const router = useRouter()

const active = (event, path) => {
    if(nowActive.value != null) nowActive.value.classList.remove('active')
    event.target.classList.add('active');
    nowActive.value = event.target
    router.push('/index/formatter/'+path)
}

</script>

<template>
    <div style="display: flex; justify-content: center;">
        <div class="box">
            <div class="navs">
                <div class="nav" @click="active($event, 'json');">JSON格式化</div>
                <div class="nav" @click="active($event)">XML格式化</div>
                <div class="nav" @click="active($event)">JSON格式化</div>
            </div>
            <div class="formatterPage">
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<style scoped>
.box {
    display: flex;
    width: 1100px;
}
.navs{
    display: flex;
    flex-direction: column;
    width: 20%;
}
.nav{
    /* #946ce6 */
    height: 40px;
    background-color: white;
    border: 1px solid rgb(210, 202, 202);
    border-radius: 5px;
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
}

.nav:hover{
    /* background-color: transparent; */
    background-color: rgba(255, 255, 255, 0.5);;
}
.active{
    border: 1px solid #946ce6;
    color: #946ce6;
}
.formatterPage{
    width: 80%;
}
</style>