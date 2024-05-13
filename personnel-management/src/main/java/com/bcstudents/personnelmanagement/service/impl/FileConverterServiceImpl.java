package com.xtu.hrms.service.impl;

import com.bcstudents.personnelmanagement.bean.FileConverter;
import com.bcstudents.personnelmanagement.mapper.FileConverterMapper;
import com.bcstudents.personnelmanagement.service.IFileConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileConverterServiceImpl implements IFileConverterService {

    @Autowired
    private FileConverterMapper baseMapper;

    @Override
    public FileConverter getById(Integer id) {
        return baseMapper.getById(id);
    }

    @Override
    public List<FileConverter> getAll() {
        return baseMapper.getAll();
    }

    @Override
    public int deleteById(Integer id) {
        return baseMapper.deleteById(id);
    }

    @Override
    public int add(FileConverter model) {
        return baseMapper.add(model);
    }

    @Override
    public int update(FileConverter model) {
        return baseMapper.update(model);
    }
}
