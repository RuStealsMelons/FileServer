<script setup>
import { reactive, ref, computed } from 'vue'
import {useRouter} from 'vue-router'

const nowActive = ref(null)
const router = useRouter()

const active = (event, path) => {
  if(nowActive.value != null) nowActive.value.classList.remove('active')
  event.target.classList.add('active');
  nowActive.value = event.target
  router.push('/index/encrypt/'+path)
}

</script>

<template>
  <div style="display: flex; justify-content: center;">
    <div class="box">
      <div class="navs">
        <!--编码-->
        <div class="nav" @click="active($event, 'Base64');">Base64</div>
        <div class="nav" @click="active($event, 'UrlEncode')">UrlEncode</div>
        <!--摘要算法-->
        <div class="nav" @click="active($event, 'MD5');">MD5</div>
        <div class="nav" @click="active($event, 'HMAC')">HMAC</div>
        <!--对称加密-->
        <div class="nav" @click="active($event, 'AES')">AES</div>
        <div class="nav" @click="active($event, 'DES')">DES</div>
        <div class="nav" @click="active($event, '3DES')">3DES</div>
        <!--非对称加密-->
        <div class="nav" @click="active($event, 'RSA')">RSA</div>
        <div class="nav" @click="active($event, 'ECC')">ECC</div>
        <!--国密加密-->
        <div class="nav" @click="active($event, 'SM2')">SM2</div>
        <div class="nav" @click="active($event, 'SM3')">SM3</div>
        <div class="nav" @click="active($event, 'SM4')">SM4</div>
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
  width: 1300px;
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