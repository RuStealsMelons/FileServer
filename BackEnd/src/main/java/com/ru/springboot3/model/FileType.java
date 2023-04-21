package com.ru.springboot3.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileType {
    Map<String, String> map = new HashMap<>();
    public FileType(){
        map.put("java", "programming");
        map.put("py", "programming");
        map.put("js", "programming");
        map.put("ts", "programming");

        map.put("xbm", "image");
        map.put("tif", "image");
        map.put("pjp", "image");
        map.put("svgz", "image");
        map.put("jpg", "image");
        map.put("jpeg", "image");
        map.put("ico", "image");
        map.put("tiff", "image");
        map.put("gif", "image");
        map.put("svg", "image");
        map.put("jfif", "image");
        map.put("webp", "image");
        map.put("png", "image");
        map.put("bmp", "image");
        map.put("pjpeg", "image");
        map.put("avif", "image");

        map.put("md", "markdown");
    }

    public Map<String, String> create(){
        return map;
    }
}
