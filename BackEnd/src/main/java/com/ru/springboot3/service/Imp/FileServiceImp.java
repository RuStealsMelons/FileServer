package com.ru.springboot3.service.Imp;

import com.ru.springboot3.mapper.FileMapper;
import com.ru.springboot3.model.File;
import com.ru.springboot3.model.FileExample;
import com.ru.springboot3.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImp implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public void insert(File file) {
        fileMapper.insert(file);
    }

    @Override
    public List<File> selectAll(String fileType, Integer owner){
        FileExample fileExample = new FileExample();
        FileExample.Criteria criteria = fileExample.createCriteria();
        criteria.andFiletypeEqualTo(fileType);
        criteria.andOwnerEqualTo(owner);
        fileExample.setOrderByClause("createTime DESC");
        return fileMapper.selectByExample(fileExample);
    }
}
