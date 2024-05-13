package com.bcstudents.personnelmanagement.mapper;
import com.bcstudents.personnelmanagement.bean.FileConverter;

import java.util.List;
public interface FileConverterMapper {
    FileConverter getById(Integer id);

    List<FileConverter> getAll();

    int deleteById(Integer id);

    int add(FileConverter model);

    int update(FileConverter model);
}
