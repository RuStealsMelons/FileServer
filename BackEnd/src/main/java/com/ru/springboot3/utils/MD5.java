package com.ru.springboot3.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static String decode(String input) throws NoSuchAlgorithmException {
        byte[] bytes = input.getBytes();

        MessageDigest md = MessageDigest.getInstance("MD5"); // 获取MD5实例
        byte[] hash = md.digest(bytes); // 计算MD5哈希值

        // 将哈希值转换为十六进制字符串
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        return hexString.toString();
    }
}
