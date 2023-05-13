<template>
    <div>
        <JsonEditorVue v-model="jsonData"></JsonEditorVue>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import JsonEditorVue from 'json-editor-vue3'

const jsonData = ref({ name: 'John', age: 30 });
</script>
  
<script>
export default {
    components: {
        VueJsonEditor,
    },
};
</script>