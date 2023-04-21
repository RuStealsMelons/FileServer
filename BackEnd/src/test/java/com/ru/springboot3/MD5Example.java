package com.ru.springboot3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Example {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String input = "Hello, world!"; // 输入字符串
        byte[] bytes = input.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5"); // 获取MD5实例
        byte[] hash = md.digest(bytes); // 计算MD5哈希值

        // 将哈希值转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b & 0xff));
        }
        String md5Hex = hexString.toString();

        System.out.println("MD5 of \"" + input + "\": " + md5Hex);
    }
}
