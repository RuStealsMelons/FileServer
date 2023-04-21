package com.ru.springboot3.controller;

import com.ru.springboot3.model.File;
import com.ru.springboot3.model.FileType;
import com.ru.springboot3.model.Result;
import com.ru.springboot3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import com.ru.springboot3.utils.MD5;

@CrossOrigin(origins = "*")
@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private Environment env;

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public Result handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam(value = "owner", required = false) Integer owner) {
        try {
            // 获取文件存储路径
            String uploadDir = env.getProperty("file.upload-dir");
            String urlHead = env.getProperty("file.url");
            Map<String, String> map = new FileType().create();

            // 文件名不能为空，否则无法断定文件类型
            if(file.getOriginalFilename() == null){
                return Result.fail("文件名不能为空!");
            }

            // 获取传过来的文件名
            String oldFileName = file.getOriginalFilename();
            String[] split = oldFileName.split("\\.");
            String type = split[split.length - 1];

            String fileType = map.get(type);

            String fileName = MD5.decode(oldFileName) + Instant.now().toEpochMilli() + "." + type;
            Path path = Paths.get(uploadDir + "/" + fileType + "/" + fileName);
            Files.write(path, file.getBytes());

            String urlBody = String.format("/%s/##/%s", fileType,fileName);

            File f = new File();
            f.setFilename(oldFileName);
            f.setFilepath(urlBody);
            f.setCreatetime(new Date());
            f.setFiletype(fileType);
            f.setOwner(owner);

            if (owner == null){
                f.setOwner(-1);
            }

            fileService.insert(f);
            return Result.success(urlBody, fileName ,  urlHead + urlBody);

        } catch (Exception e) {
            return Result.fail("存储失败！");
        }
    }
}
