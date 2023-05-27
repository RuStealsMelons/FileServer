<template>
    <el-input v-model="textarea1" autosize type="textarea" placeholder="Please input" />

    <div>{{ formatXml(textarea1) }}</div>
</template>
  
<script setup>
import { ref } from 'vue'
const textarea1 = ref(`<root><child>Text</child><child>More text</child></root>`)

function formatXml(xmlString) {
  const parser = new DOMParser();
  const xmlDoc = parser.parseFromString(xmlString, "text/xml");
  const root = xmlDoc.documentElement;
  let result = "";

  function format(node, indent) {
    const childIndent = indent + "  ";
    if (node.nodeType === Node.ELEMENT_NODE) {
      result += `${indent}<${node.tagName}`;
      if (node.hasAttributes()) {
        Array.from(node.attributes).forEach(attr => {
          result += ` ${attr.name}="${attr.value}"`;
        });
      }
      result += ">\n";
      Array.from(node.childNodes).forEach(childNode => {
        format(childNode, childIndent);
      });
      result += `${indent}</${node.tagName}>\n`;
    } else if (node.nodeType === Node.TEXT_NODE) {
      result += `${indent}${node.textContent}\n`;
    }
  }

  format(root, "");
  return result;
}
</script>