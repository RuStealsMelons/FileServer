package com.ru.springboot3.service;

import com.ru.springboot3.model.File;

import java.util.List;

public interface FileService {
    public void insert(File file);

    public List<File> selectAll(String fileType, Integer owner);
}
