package com.ru.springboot3.service.Imp;

import com.ru.springboot3.mapper.FileMapper;
import com.ru.springboot3.model.File;
import com.ru.springboot3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImp implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public void insert(File file) {
        fileMapper.insert(file);
    }
}
