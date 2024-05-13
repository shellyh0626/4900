package com.bcstudents.personnelmanagement.service;
import com.bcstudents.personnelmanagement.bean.FileConverter;

import java.util.List;
public interface IFileConverterService {
    FileConverter getById(Integer id);

    List<FileConverter> getAll();

    int deleteById(Integer id);

    int add(FileConverter model);

    int update(FileConverter model);
}
