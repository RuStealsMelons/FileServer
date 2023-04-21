package com.ru.springboot3.controller;

import com.ru.springboot3.model.File;
import com.ru.springboot3.service.Imp.FileServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/image")
@RestController
public class ImageController {
    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private Environment env;

    @GetMapping("/list")
    private List<File> getList(@RequestParam(value = "owner", required = false) Integer owner){
        if(owner == null){
            owner = -1;
        }
        return fileServiceImp.selectAll("image", owner);
    }

    @GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
        String uploadDir = env.getProperty("file.upload-dir");

        // 加载文件作为Resource对象
        Resource resource = new FileSystemResource(uploadDir + "/image/" + filename);

        // 检查文件是否存在
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        // 返回响应对象
        return ResponseEntity.ok().headers(headers).body(resource);
    }


    @GetMapping("/preview/{filename:.+}")
    public ResponseEntity<Resource> previewFile(@PathVariable String filename) {
        String uploadDir = env.getProperty("file.upload-dir");

        // 加载文件作为Resource对象
        Resource resource = new FileSystemResource(uploadDir + "/image/" + filename);

        // 检查文件是否存在
        if (!resource.exists()) {
            return ResponseEntity.notFound().build();
        }

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        String[] split = filename.split("\\.");
        String fileType = split[split.length - 1];
        switch (fileType) {
            case "jpg", "jpge" -> headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
            case "png" -> headers.add(HttpHeaders.CONTENT_TYPE, "image/png");
            case "gif" -> headers.add(HttpHeaders.CONTENT_TYPE, "image/gif");
            case "svg" -> headers.add(HttpHeaders.CONTENT_TYPE, "image/svg+xml");
            case "icon" -> headers.add(HttpHeaders.CONTENT_TYPE, "image/x-icon");
            default -> headers.add(HttpHeaders.CONTENT_TYPE, "image/jpeg");
        }
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"");

        // 返回响应对象
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
